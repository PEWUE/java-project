import generator.ConfigurationOptionGenerator;
import generator.ProductGenerator;
import manager.ProductManager;
import model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();

        List<Product> products = ProductGenerator.sampleProducts();

        products.forEach(manager::addProduct);

        // Wyświetlenie wszystkich produktów
        System.out.println("=== LISTA PRODUKTÓW ===");
        manager.getAllProducts().forEach(System.out::println);

        // Pobranie produktu po ID
        UUID productId = products.get(5).getId();
        Optional<Product> foundProduct = manager.getProductById(productId);
        foundProduct.ifPresent(product -> System.out.println("Znaleziono produkt: " + product.getName()));

        // Aktualizacja produktu
        Product computer = products.get(8); // wybrany komputer do edycji Computer Model 9
        Product updatedComputer = new Product(
                computer.getId(),
                computer.getName() + " PRO",
                computer.getBasePrice().add(new BigDecimal("500")),
                7,
                computer.getType(),
                computer.getAvailableOptions()
        );
        updatedComputer.addSelectedOption(ConfigurationOptionGenerator.computerOptions1().get(2)); // Dodajemy Processor 3
        manager.updateProduct(updatedComputer);

        System.out.println("Po aktualizacji komputer: " + manager.getProductById(computer.getId()).get().getName()
                + " - cena końcowa: " + manager.getProductById(computer.getId()).get().getFinalPrice());

        // Usuwanie produktu
        Product productToDelete = products.get(15); // wybrany produkt do usuniecia Smartphone Model 6
        manager.removeProduct(productToDelete.getId());
        System.out.println("Po usunięciu produktu, liczba produktów: " + manager.getAllProducts().size());

        // Wyświetlenie wszystkich produktów
        System.out.println("=== LISTA PRODUKTÓW ===");
        manager.getAllProducts().forEach(System.out::println);
    }
}