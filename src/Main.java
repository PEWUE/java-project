import ui.ShopUserInterface;
import generator.ProductGenerator;
import manager.ProductManager;
import model.Product;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();
        List<Product> products = ProductGenerator.sampleProducts();
        products.forEach(productManager::addProduct);
        ShopUserInterface shopUserInterface = new ShopUserInterface(productManager);
        shopUserInterface.start();
    }
}