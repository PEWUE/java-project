package generator;

import model.ConfigurationOption;

import java.math.BigDecimal;
import java.util.List;

public class ConfigurationOptionGenerator {

    public static List<ConfigurationOption> computerOptions1() {
        return List.of(
                new ConfigurationOption("Processor 1", new BigDecimal("829")),
                new ConfigurationOption("Processor 2", new BigDecimal("651")),
                new ConfigurationOption("Processor 3", new BigDecimal("351")),
                new ConfigurationOption("RAM 1GB", new BigDecimal("477")),
                new ConfigurationOption("RAM 2GB", new BigDecimal("273")),
                new ConfigurationOption("RAM 3GB", new BigDecimal("411")),
                new ConfigurationOption("Storage 1GB SSD", new BigDecimal("133")),
                new ConfigurationOption("Storage 2GB SSD", new BigDecimal("153")),
                new ConfigurationOption("Storage 3GB SSD", new BigDecimal("272"))
        );
    }

    public static List<ConfigurationOption> computerOptions2() {
        return List.of(
                new ConfigurationOption("Processor 4", new BigDecimal("585")),
                new ConfigurationOption("Processor 5", new BigDecimal("674")),
                new ConfigurationOption("Processor 6", new BigDecimal("911")),
                new ConfigurationOption("RAM 4GB", new BigDecimal("99")),
                new ConfigurationOption("RAM 5GB", new BigDecimal("65")),
                new ConfigurationOption("RAM 6GB", new BigDecimal("386")),
                new ConfigurationOption("Storage 4GB SSD", new BigDecimal("542")),
                new ConfigurationOption("Storage 5GB SSD", new BigDecimal("128")),
                new ConfigurationOption("Storage 6GB SSD", new BigDecimal("252"))
        );
    }

    public static List<ConfigurationOption> computerOptions3() {
        return List.of(
                new ConfigurationOption("Processor 7", new BigDecimal("543")),
                new ConfigurationOption("Processor 8", new BigDecimal("265")),
                new ConfigurationOption("RAM 7GB", new BigDecimal("328")),
                new ConfigurationOption("RAM 8GB", new BigDecimal("327")),
                new ConfigurationOption("Storage 7GB SSD", new BigDecimal("300")),
                new ConfigurationOption("Storage 8GB SSD", new BigDecimal("306"))
        );
    }

    public static List<ConfigurationOption> computerOptions4() {
        return List.of(
                new ConfigurationOption("Processor 9", new BigDecimal("266")),
                new ConfigurationOption("Processor 10", new BigDecimal("507")),
                new ConfigurationOption("RAM 9GB", new BigDecimal("215")),
                new ConfigurationOption("RAM 10GB", new BigDecimal("132")),
                new ConfigurationOption("Storage 9GB SSD", new BigDecimal("102")),
                new ConfigurationOption("Storage 10GB SSD", new BigDecimal("148"))
        );
    }

    public static List<ConfigurationOption> smartphoneOptions1() {
        return List.of(
                new ConfigurationOption("Color Black", new BigDecimal("30")),
                new ConfigurationOption("Color White", new BigDecimal("20")),
                new ConfigurationOption("Battery 3000mAh", new BigDecimal("130")),
                new ConfigurationOption("Battery 3500mAh", new BigDecimal("128")),
                new ConfigurationOption("Accessory 1", new BigDecimal("42")),
                new ConfigurationOption("Accessory 2", new BigDecimal("29"))
        );
    }

    public static List<ConfigurationOption> smartphoneOptions2() {
        return List.of(
                new ConfigurationOption("Color Red", new BigDecimal("20")),
                new ConfigurationOption("Color Blue", new BigDecimal("0")),
                new ConfigurationOption("Color Green", new BigDecimal("30")),
                new ConfigurationOption("Battery 4000mAh", new BigDecimal("127")),
                new ConfigurationOption("Battery 4500mAh", new BigDecimal("119")),
                new ConfigurationOption("Battery 5000mAh", new BigDecimal("76")),
                new ConfigurationOption("Accessory 3", new BigDecimal("35")),
                new ConfigurationOption("Accessory 4", new BigDecimal("46")),
                new ConfigurationOption("Accessory 5", new BigDecimal("32"))
        );
    }

    public static List<ConfigurationOption> smartphoneOptions3() {
        return List.of(
                new ConfigurationOption("Color Yellow", new BigDecimal("30")),
                new ConfigurationOption("Color Purple", new BigDecimal("20")),
                new ConfigurationOption("Color Pink", new BigDecimal("30")),
                new ConfigurationOption("Color Gray", new BigDecimal("20")),
                new ConfigurationOption("Color Orange", new BigDecimal("0")),
                new ConfigurationOption("Battery 5500mAh", new BigDecimal("105")),
                new ConfigurationOption("Battery 6000mAh", new BigDecimal("80")),
                new ConfigurationOption("Battery 6500mAh", new BigDecimal("140")),
                new ConfigurationOption("Battery 7000mAh", new BigDecimal("62")),
                new ConfigurationOption("Battery 7500mAh", new BigDecimal("68")),
                new ConfigurationOption("Accessory 6", new BigDecimal("25")),
                new ConfigurationOption("Accessory 7", new BigDecimal("7")),
                new ConfigurationOption("Accessory 8", new BigDecimal("24")),
                new ConfigurationOption("Accessory 9", new BigDecimal("44")),
                new ConfigurationOption("Accessory 10", new BigDecimal("24"))
        );
    }

    public static List<ConfigurationOption> noOptions() {
        return List.of();
    }
}
