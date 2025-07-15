package model;

import exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a shopping cart containing cart items selected by the user.
 * Provides methods to add, remove, update, and clear items, as well as to calculate the total value.
 */
public class Cart {
    private final List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(cartItems);
    }

    /**
     * Adds a product with the specified configuration options and quantity to the cart.
     * <p>
     * If an item with the same product and configuration already exists in the cart,
     * its quantity is increased by the given amount instead of adding a new entry.
     *
     * @param product         the product to add (must not be null)
     * @param selectedOptions the configuration options for the product (must not be null)
     * @param quantity        the quantity to add (must be greater than 0)
     * @return {@code true} if a new cart item was added, {@code false} if the quantity of an existing item was increased
     * @throws IllegalArgumentException if product or selectedOptions is null, or quantity is not positive
     */
    //TODO
    // 1. void?
    public boolean addProduct(Product product, List<ConfigurationOption> selectedOptions, int quantity) {
        if (product == null || selectedOptions == null) {
            throw new IllegalArgumentException("Produkt i opcje konfiguracji nie mogą być nullem");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Ilość musi być większa od 0");
        }
        CartItem newCartItem = new CartItem(product, List.copyOf(selectedOptions), quantity);

        for (CartItem cartItem : cartItems) {
            if (cartItem.equals(newCartItem)) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                return false; //TODO czy to jest ok? (nie dodano nowej pozycji, zwiększono ilość istniejącego CartItem)
            }
        }
        cartItems.add(newCartItem);
        return true; //TODO dodano nowy CartItem
    }

    /**
     * Removes the specified cart item from the cart.
     *
     * @param cartItem the cart item to remove
     * @return {@code true} if the item was removed, {@code false} if it was not found
     * @throws ProductNotFoundException if the item does not exist in the cart
     */
    public boolean removeItem(CartItem cartItem) {
        if (!cartItems.contains(cartItem)) {
            throw new ProductNotFoundException("Produkt nie istnieje w magazynie");
        }
        return cartItems.remove(cartItem);
    }

    /**
     * Updates the quantity of the specified cart item.
     * <p>
     * If the new quantity is less than or equal to zero, the item is removed from the cart.
     *
     * @param cartItem    the cart item to update
     * @param newQuantity the new quantity (if less than or equal to 0, the item is removed)
     * @return {@code true} if the item was found and updated or removed, {@code false} otherwise
     */
    public boolean updateQuantity(CartItem cartItem, int newQuantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().equals(cartItem.getProduct()) &&
                    item.getSelectedOptions().equals(cartItem.getSelectedOptions())) {
                if (newQuantity <= 0) {
                    cartItems.remove(item);
                } else {
                    item.setQuantity(newQuantity);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all items from the cart.
     */
    public void clear() {
        cartItems.clear();
    }

    /**
     * Calculates the total value of all items in the cart.
     *
     * @return the total value as a BigDecimal
     */
    public BigDecimal getTotalValue() {
        return cartItems.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}