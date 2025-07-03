import cli.ShopUserInterface;
import generator.ProductGenerator;
import manager.ProductManager;
import model.Cart;
import model.Product;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();
        List<Product> products = ProductGenerator.sampleProducts();
        products.forEach(productManager::addProduct);
        Cart cart = new Cart();
        ShopUserInterface shopUserInterface = new ShopUserInterface(productManager, cart);
        shopUserInterface.start();
    }
}