package model;

import exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private final List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(cartItems);
    }

    //TODO
    // 1. przekazywać CartItem czy Product + List<ConfigurationOption>?
    // 2. void?
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

    //TODO przekazywać CartItem czy Product + List<ConfigurationOption>?
    public boolean removeItem(CartItem cartItem) {
        if (!cartItems.contains(cartItem)) {
            throw new ProductNotFoundException("Produkt nie istnieje w magazynie");
        }
        return cartItems.remove(cartItem);
    }

    //TODO przekazywać CartItem czy Product + List<ConfigurationOption>?
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

    public void clear() {
        cartItems.clear();
    }

    public BigDecimal getTotalValue() {
        return cartItems.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}