package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a configuration option for a product (e.g., size, color).
 * Stores the option's name and additional price.
 */
public class ConfigurationOption implements Serializable {
    private final String name;
    private final BigDecimal price;

    /**
     * Constructs a configuration option.
     *
     * @param name  the name of the option (e.g., "Color: Red")
     * @param price the additional price for this option (may be zero)
     */
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
