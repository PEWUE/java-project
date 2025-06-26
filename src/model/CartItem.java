package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Represents a single item in the shopping cart.
 * <p>
 * <p>
 * Stores information about the select product, list of configuration options and quantity.
 * Immutable except for the quantity.
 */
public class CartItem implements Serializable {
    private final Product product;
    private final List<ConfigurationOption> selectedOptions;
    private int quantity;

    /**
     * Creates a new cart item.
     *
     * @param product         the product being added to the cart (must not be null)
     * @param selectedOptions the list of selected configuration options (may be empty but not null)
     * @param quantity        the number of units of the product (must be greater than 0)
     * @throws IllegalArgumentException id product is null, selectedOptions is null or quantity is not positive
     */
    public CartItem(Product product, List<ConfigurationOption> selectedOptions, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Produkt nie może być nullem");
        }
        if (selectedOptions == null) {
            throw new IllegalArgumentException("Wybrane opcje nie mogą być nullem");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Ilość musi być większa od 0");
        }
        this.product = product;
        this.selectedOptions = List.copyOf(selectedOptions);
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public List<ConfigurationOption> getSelectedOptions() {
        return List.copyOf(selectedOptions);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price of one item with all selected options
     */
    public BigDecimal getSingleItemPrice() {
        BigDecimal optionsPrice = selectedOptions.stream()
                .map(ConfigurationOption::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return product.getBasePrice().add(optionsPrice);
    }

    /**
     * @return the total price for the given quantity of this cart item
     */
    public BigDecimal getTotalPrice() {
        BigDecimal singleItemPrice = getSingleItemPrice();
        return singleItemPrice.multiply(BigDecimal.valueOf(quantity));
    }

    /**
     * Compares this cart item to another object for equality.
     * <p>
     * Two cart items are considered equal if they have the same product and the same list of selected configuration options,
     * regardless of the quantity.
     *
     * @param o the object to compare with
     * @return {@code true} if the given object is a CartItem with the same product and options, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(product, cartItem.product) && Objects.equals(selectedOptions, cartItem.selectedOptions);
    }

    /**
     * Returns the hash code for this cart item, based only on the product and selected configuration options.
     * <p>
     * The quantity is not included to ensure consistency with the {@code equals} method.
     *
     * @return hash code based on product and selected options
     */
    @Override
    public int hashCode() {
        return Objects.hash(product, selectedOptions);
    }
}