import cli.ShopCLI;
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
        ShopCLI cli = new ShopCLI(productManager, cart);
        cli.start();
    }
}