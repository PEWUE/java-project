package model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class CartItem {
    private final Product product;
    private final List<ConfigurationOption> selectedOptions;
    private int quantity;

    public CartItem(int quantity, List<ConfigurationOption> selectedOptions, Product product) {
        this.quantity = quantity;
        this.selectedOptions = selectedOptions;
        this.product = product;
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

    public BigDecimal getTotalPrice() {
        BigDecimal optionsPrice = selectedOptions.stream()
                .map(ConfigurationOption::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal singleItemPrice = product.getBasePrice().add(optionsPrice);
        return singleItemPrice.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return quantity == cartItem.quantity && Objects.equals(product, cartItem.product) && Objects.equals(selectedOptions, cartItem.selectedOptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, selectedOptions, quantity);
    }
}