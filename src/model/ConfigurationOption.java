package model;

import java.math.BigDecimal;
import java.util.Objects;

public class ConfigurationOption {
    private final String name;
    private final BigDecimal price;

    public ConfigurationOption(String name, BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cena opcji nie może być ujemna");
        }
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ConfigurationOption that = (ConfigurationOption) o;
        return Objects.equals(name, that.name) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return name + " (" + price + ")";
    }
}
