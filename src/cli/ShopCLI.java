package cli;

import manager.ProductManager;
import model.Cart;
import model.CartItem;
import model.ConfigurationOption;
import model.Customer;
import model.Order;
import model.Product;
import processor.OrderProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShopCLI {
    private final Scanner scanner;
    private final ProductManager productManager;
    private final Cart cart;

    public ShopCLI(ProductManager productManager, Cart cart) {
        this.scanner = new Scanner(System.in);
        this.productManager = productManager;
        this.cart = cart;
    }

    public void start() {
        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = readInt("Wybierz opcję: ");
            switch (choice) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    addProductToCart();
                    break;
                case 3:
                    removeProductFromCart();
                    break;
                case 4:
                    displayCart();
                    break;
                case 5:
                    placeOrder();
                    break;
                case 0:
                    System.out.println("Do zobaczenia!");
                    running = false;
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja.");
            }
        }
    }

    private void showMainMenu() {
        System.out.println("\n------ MENU ------");
        System.out.println("1. Wyświetl produkty");
        System.out.println("2. Dodaj produkt do koszyka");
        System.out.println("3. Usuń produkt z koszyka");
        System.out.println("4. Wyświetl koszyk");
        System.out.println("5. Złóż zamówienie");
        System.out.println("0. Wyjdź");
    }

    private void displayProducts() {
        List<Product> products = productManager.getAllProducts();
        System.out.println("\nDostępne produkty:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).getName());
        }
    }

    private void addProductToCart() {
        List<Product> products = productManager.getAllProducts();
        displayProducts();
        int productIndex = readInt("Wybierz numer produktu (0 - anuluj): ");
        if (productIndex == 0) {
            System.out.println("Anulowano dodawanie produktu.");
            return;
        }
        if (productIndex < 1 || productIndex > products.size()) {
            System.out.println("Nieprawidłowy numer produktu.");
            return;
        }
        Product selectedProduct = products.get(productIndex - 1);

        // Wybór opcji
        List<ConfigurationOption> chosenOptions = chooseOptions(selectedProduct);

        // Wybór ilości
        int quantity = readInt("Podaj ilość (0 - anuluj): ");
        if (quantity == 0) {
            System.out.println("Anulowano dodawanie produktu.");
            return;
        }
        if (quantity < 0) {
            System.out.println("Ilość nie może być ujemna.");
            return;
        }

        cart.addProductToCart(selectedProduct, chosenOptions, quantity);
        System.out.println("Dodano do koszyka: " + selectedProduct.getName() + " w ilości: " + quantity);
    }

    private List<ConfigurationOption> chooseOptions(Product product) {
        List<ConfigurationOption> available = product.getAvailableOptions();
        List<ConfigurationOption> chosen = new ArrayList<>();
        if (available.isEmpty()) {
            System.out.println("Brak dodatkowych opcji dla tego produktu.");
            return chosen;
        }
        boolean selecting = true;
        while (selecting) {
            System.out.println("Dostępne opcje dla produktu:");
            for (int i = 0; i < available.size(); i++) {
                // Pokaż tylko opcje, które nie zostały już wybrane
                if (!chosen.contains(available.get(i))) {
                    System.out.println((i + 1) + ". " + available.get(i).getName());
                }
            }
            System.out.println("0. Przejdź dalej (bez kolejnych opcji)");
            int optionIndex = readInt("Wybierz numer opcji (0 - zakończ wybór): ");
            if (optionIndex == 0) {
                selecting = false;
            } else if (optionIndex > 0 && optionIndex <= available.size()) {
                ConfigurationOption selected = available.get(optionIndex - 1);
                if (!chosen.contains(selected)) {
                    chosen.add(selected);
                    System.out.println("Dodano opcję: " + selected.getName());
                } else {
                    System.out.println("Ta opcja została już wybrana.");
                }
            } else {
                System.out.println("Nieprawidłowy numer opcji.");
            }
        }
        return chosen;
    }

    private void displayCart() {
        List<CartItem> items = cart.getItems();
        if (items.isEmpty()) {
            System.out.println("\nKoszyk jest pusty.");
            return;
        }
        System.out.println("\n------ ZAWARTOŚĆ KOSZYKA ------");
        for (int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            System.out.println((i + 1) + ". " + item.getProduct().getName()
                    + " | Ilość: " + item.getQuantity()
                    + " | Cena jednostkowa: " + item.getSingleItemPrice()
                    + " | Wartość pozycji: " + item.getTotalPrice());
            if (!item.getSelectedOptions().isEmpty()) {
                System.out.print("   Opcje: ");
                item.getSelectedOptions().forEach(option -> System.out.print(option.getName() + " - " + option.getPrice() + " | "));
                System.out.println();
            }
        }
        System.out.println("Suma koszyka: " + cart.getTotalValue());
    }

    private void placeOrder() {
        if (cart.getItems().isEmpty()) {
            System.out.println("Koszyk jest pusty.");
            return;
        }
        System.out.println("\n--- Składanie zamówienia ---");
        System.out.println("Podaj dane osoby zamawiającej: ");

        String firstName = readString("Imię: ");
        String lastName = readString("Nazwisko: ");
        String email = readString("Email: ");
        String phoneNumber = readString("Nr telefonu: ");
        String street = readString("Ulica: ");
        String city = readString("Miasto: ");
        String postalCode = readString("Kod pocztowy: ");
        String country = readString("Kraj: ");

        Customer customer = new Customer(firstName, lastName, email, phoneNumber, street, city, postalCode, country);
        Order order = new Order(cart.getItems(), customer);

        try {
            OrderProcessor.processOrder(order, productManager);
            System.out.println("Zamówienie zostało złożone i opłacone");
            String choice = readString("Czy chcesz wygenerować fakturę? (t/n)");
            if (choice.equalsIgnoreCase("t") || choice.equalsIgnoreCase("tak")) {
                System.out.println("FAKTURA");
                System.out.println(OrderProcessor.generateInvoice(order));
            }
            cart.clear();
        } catch (Exception e) {
            System.err.println("Błąd podczas składania zamówienia: " + e.getMessage());
        }
    }

    private void removeProductFromCart() {
        List<CartItem> items = cart.getItems();
        if (items.isEmpty()) {
            System.out.println("Koszyk jest pusty.");
            return;
        }
        System.out.println("\n------ USUWANIE Z KOSZYKA ------");
        for (int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            System.out.println((i + 1) + ". " + item.getProduct().getName()
                    + " | Ilość: " + item.getQuantity()
                    + (item.getSelectedOptions().isEmpty() ? "" : " | Opcje: " + item.getSelectedOptions()));
        }
        int choice = readInt("Podaj numer produktu do usunięcia (0 - anuluj): ");
        if (choice == 0) {
            System.out.println("Anulowano usuwanie.");
            return;
        }
        if (choice < 1 || choice > items.size()) {
            System.out.println("Nieprawidłowy numer.");
            return;
        }
        CartItem toRemove = items.get(choice - 1);
        boolean removed = cart.removeItem(toRemove);
        if (removed) {
            System.out.println("Usunięto produkt z koszyka.");
        } else {
            System.out.println("Nie udało się usunąć produktu.");
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Podaj liczbę!");
            }
        }
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
