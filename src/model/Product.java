package model;

import enums.ProductType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a product available in the shop.
 * <p>
 * Stores information about the product's name, base price, available quantity,
 * type, and possible configuration options (such as size, color, etc.).
 */
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

    /**
     * Constructs a new product with a generated unique identifier.
     *
     * @param name             the product name
     * @param price            the base price (must not be negative)
     * @param quantity         the available quantity (must not be negative)
     * @param type             the product type
     * @param availableOptions the list of available configuration options (may be null or empty)
     * @throws IllegalStateException if price or quantity is negative
     */
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

    /**
     * Compares this product to another object for equality.
     * <p>
     * Two products are considered equal if they have the same unique identifier ({@code id}),
     * regardless of other fields.
     *
     * @param o the object to compare with
     * @return {@code true} if the given object is a Product with the same id, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    /**
     * Returns the hash code for this product, based solely on the unique {@code id} field.
     * This ensures consistency with the {@code equals} method.
     *
     * @return hash code based on product id
     */
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
