package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class CartItem implements Serializable {
    private final Product product;
    private final List<ConfigurationOption> selectedOptions;
    private int quantity;

    public CartItem(Product product, List<ConfigurationOption> selectedOptions, int quantity) {
        this.product = product;
        this.selectedOptions = selectedOptions;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public List<ConfigurationOption> getSelectedOptions() {
        return selectedOptions;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSingleItemPrice() {
        BigDecimal optionsPrice = selectedOptions.stream()
                .map(ConfigurationOption::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return product.getBasePrice().add(optionsPrice);
    }

    public BigDecimal getTotalPrice() {
        BigDecimal singleItemPrice = getSingleItemPrice();
        return singleItemPrice.multiply(BigDecimal.valueOf(quantity));
    }

    // w equals nie porównuję quantity
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(product, cartItem.product) && Objects.equals(selectedOptions, cartItem.selectedOptions);
    }

    // w hashcode brak quantity
    @Override
    public int hashCode() {
        return Objects.hash(product, selectedOptions);
    }
}