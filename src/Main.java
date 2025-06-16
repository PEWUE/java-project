import enums.ProductType;
import model.ConfigurationOption;
import model.Product;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //COMPUTER
        List<ConfigurationOption> computerOptions = List.of(
                new ConfigurationOption("Intel i5", new BigDecimal("600")),
                new ConfigurationOption("16GB RAM", new BigDecimal("400")),
                new ConfigurationOption("512GB SSD", new BigDecimal("300"))
        );
        Product computer = new Product(
                "Komputer biurowy",
                new BigDecimal("2000"),
                5,
                ProductType.COMPUTER,
                computerOptions
        );
        computer.addSelectedOption(computerOptions.get(0)); // Intel i5 - 600
        computer.addSelectedOption(computerOptions.get(1)); // 16GB RAM - 400

        //SMARTPHONE
        List<ConfigurationOption> smartphoneOptions = List.of(
                new ConfigurationOption("Czarny", BigDecimal.ZERO),
                new ConfigurationOption("5000mAh bateria", new BigDecimal("100")),
                new ConfigurationOption("Etui silikonowe", new BigDecimal("30"))
        );
        Product smartphone = new Product(
                "Smartphone X",
                new BigDecimal("1500"),
                10,
                ProductType.SMARTPHONE,
                smartphoneOptions
        );
        smartphone.addSelectedOption(smartphoneOptions.get(0)); // Czarny - 0
        smartphone.addSelectedOption(smartphoneOptions.get(1)); // 5000mAh bateria - 100

        //ELECTRONICS
        List<ConfigurationOption> electronicsOptions = List.of(); // brak opcji
        Product headphones = new Product(
                "Słuchawki bezprzewodowe",
                new BigDecimal("300"),
                20,
                ProductType.ELECTRONICS,
                electronicsOptions
        );

        System.out.println("Komputer - cena końcowa: " + computer.getFinalPrice());
        System.out.println("Smartfon - cena końcowa: " + smartphone.getFinalPrice());
        System.out.println("Słuchawki - cena końcowa: " + headphones.getFinalPrice());
    }
}