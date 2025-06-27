package model;

import enums.ProductType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Product implements Serializable {
    private final UUID id;
    private String name;
    private BigDecimal basePrice;
    private int quantity;
    private ProductType type;
    private List<ConfigurationOption> availableOptions;

    public Product(String name, BigDecimal price, int quantity, ProductType type, List<ConfigurationOption> availableOptions) {
        this(UUID.randomUUID(), name, price, quantity, type, availableOptions);
    }

    public Product(UUID id, String name, BigDecimal price, int quantity, ProductType type, List<ConfigurationOption> availableOptions) {
        if (price.compareTo(BigDecimal.ZERO) < 0 || quantity < 0) {
            throw new IllegalStateException("Cena i ilość nie mogą być ujemne");
        }
        this.id = id;
        this.name = name;
        this.basePrice = price;
        this.quantity = quantity;
        this.type = type;
        this.availableOptions = availableOptions != null ? availableOptions : new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public List<ConfigurationOption> getAvailableOptions() {
        return List.copyOf(availableOptions);
    }

    public void setAvailableOptions(List<ConfigurationOption> availableOptions) {
        this.availableOptions = availableOptions != null ? availableOptions : new ArrayList<>();
    }

    //używam tylko id do porównania
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    //używam tylko id w hashCode
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return name + " basePrice: " + basePrice
                + " in magazine: " + quantity
                + " availableOptions: " + availableOptions;
    }
}
