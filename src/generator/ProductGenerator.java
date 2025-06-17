package generator;

import model.Product;
import enums.ProductType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

public class ProductGenerator {

    public static List<Product> sampleProducts() {
        return List.of(
                new Product("Computer Model 1", new BigDecimal("2516"), 5, ProductType.COMPUTER, ConfigurationOptionGenerator.computerOptions1()),
                new Product("Computer Model 2", new BigDecimal("1408"), 1, ProductType.COMPUTER, ConfigurationOptionGenerator.computerOptions2()),
                new Product("Computer Model 3", new BigDecimal("1254"), 12, ProductType.COMPUTER, ConfigurationOptionGenerator.computerOptions3()),
                new Product("Computer Model 4", new BigDecimal("1388"), 11, ProductType.COMPUTER, ConfigurationOptionGenerator.computerOptions4()),
                new Product("Computer Model 5", new BigDecimal("2894"), 20, ProductType.COMPUTER, ConfigurationOptionGenerator.computerOptions1()),
                new Product("Computer Model 6", new BigDecimal("2131"), 5, ProductType.COMPUTER, ConfigurationOptionGenerator.computerOptions2()),
                new Product("Computer Model 7", new BigDecimal("2954"), 1, ProductType.COMPUTER, ConfigurationOptionGenerator.computerOptions3()),
                new Product("Computer Model 8", new BigDecimal("2649"), 8, ProductType.COMPUTER, ConfigurationOptionGenerator.computerOptions4()),
                new Product("Computer Model 9", new BigDecimal("1995"), 9, ProductType.COMPUTER, ConfigurationOptionGenerator.computerOptions1()),
                new Product("Computer Model 10", new BigDecimal("2033"), 14, ProductType.COMPUTER, ConfigurationOptionGenerator.computerOptions2()),

                new Product("Smartphone Model 1", new BigDecimal("807"), 16, ProductType.SMARTPHONE, ConfigurationOptionGenerator.smartphoneOptions1()),
                new Product("Smartphone Model 2", new BigDecimal("943"), 17, ProductType.SMARTPHONE, ConfigurationOptionGenerator.smartphoneOptions2()),
                new Product("Smartphone Model 3", new BigDecimal("1115"), 8, ProductType.SMARTPHONE, ConfigurationOptionGenerator.smartphoneOptions3()),
                new Product("Smartphone Model 4", new BigDecimal("1019"), 19, ProductType.SMARTPHONE, ConfigurationOptionGenerator.smartphoneOptions1()),
                new Product("Smartphone Model 5", new BigDecimal("716"), 28, ProductType.SMARTPHONE, ConfigurationOptionGenerator.smartphoneOptions2()),
                new Product("Smartphone Model 6", new BigDecimal("1437"), 9, ProductType.SMARTPHONE, ConfigurationOptionGenerator.smartphoneOptions3()),
                new Product("Smartphone Model 7", new BigDecimal("537"), 10, ProductType.SMARTPHONE, ConfigurationOptionGenerator.smartphoneOptions1()),
                new Product("Smartphone Model 8", new BigDecimal("852"), 10, ProductType.SMARTPHONE, ConfigurationOptionGenerator.smartphoneOptions2()),
                new Product("Smartphone Model 9", new BigDecimal("1035"), 15, ProductType.SMARTPHONE, ConfigurationOptionGenerator.smartphoneOptions3()),
                new Product("Smartphone Model 10", new BigDecimal("1409"), 17, ProductType.SMARTPHONE, ConfigurationOptionGenerator.smartphoneOptions1()),

                new Product("Electronics Model 1", new BigDecimal("609"), 32, ProductType.ELECTRONICS, ConfigurationOptionGenerator.noOptions()),
                new Product("Electronics Model 2", new BigDecimal("322"), 14, ProductType.ELECTRONICS, ConfigurationOptionGenerator.noOptions()),
                new Product("Electronics Model 3", new BigDecimal("274"), 50, ProductType.ELECTRONICS, ConfigurationOptionGenerator.noOptions()),
                new Product("Electronics Model 4", new BigDecimal("181"), 26, ProductType.ELECTRONICS, ConfigurationOptionGenerator.noOptions()),
                new Product("Electronics Model 5", new BigDecimal("365"), 40, ProductType.ELECTRONICS, ConfigurationOptionGenerator.noOptions()),
                new Product("Electronics Model 6", new BigDecimal("378"), 12, ProductType.ELECTRONICS, ConfigurationOptionGenerator.noOptions()),
                new Product("Electronics Model 7", new BigDecimal("555"), 13, ProductType.ELECTRONICS, ConfigurationOptionGenerator.noOptions()),
                new Product("Electronics Model 8", new BigDecimal("288"), 7, ProductType.ELECTRONICS, ConfigurationOptionGenerator.noOptions()),
                new Product("Electronics Model 9", new BigDecimal("265"), 39, ProductType.ELECTRONICS, ConfigurationOptionGenerator.noOptions()),
                new Product("Electronics Model 10", new BigDecimal("598"), 26, ProductType.ELECTRONICS, ConfigurationOptionGenerator.noOptions())
        );
    }

    public static Map<UUID, Product> sampleProductMap() {
        List<Product> products = sampleProducts();
        Map<UUID, Product> map = new HashMap<>();
        for (Product product : products) {
            map.put(product.getId(), product);
        }
        return map;
    }
}
