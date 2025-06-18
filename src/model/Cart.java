package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private final List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(cartItems);
    }

    public void addProductToCart(Product product, List<ConfigurationOption> selectedOptions, int quantity) {
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
                return;
            }
        }
        cartItems.add(newCartItem);
    }
}