import generator.CustomerGenerator;
import generator.ProductGenerator;
import manager.ProductManager;
import model.Cart;
import model.CartItem;
import model.Customer;
import model.Order;
import model.Product;
import processor.OrderProcessor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();
        Cart cart = new Cart();
        List<Product> products = ProductGenerator.sampleProducts();
        products.forEach(productManager::addProduct);

        cart.addProductToCart(products.get(1), List.of(
                products.get(1).getAvailableOptions().get(2),
                products.get(1).getAvailableOptions().get(5)
        ), 1);

        cart.addProductToCart(products.get(2), List.of(
                products.get(1).getAvailableOptions().get(1)
        ), 2);

        System.out.println("Zawartość koszyka: ");
        for (CartItem item : cart.getItems()) {
            System.out.println("Produkt: " + item.getProduct().getName() + ", Ilość: " + item.getQuantity() + ", Cena: " + item.getTotalPrice());
        }
        System.out.println(cart.getTotalValue());

        Customer customer = CustomerGenerator.sampleCustomers().getFirst();
        Order order = new Order(cart.getItems(), customer);

        System.out.println(productManager.getAllProducts().size());
        System.out.println(order.getStatus());
        OrderProcessor.processOrder(order, productManager);
        System.out.println(productManager.getAllProducts().size());
        System.out.println(order.getStatus());
        System.out.println(OrderProcessor.generateInvoice(order));
    }
}