package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private final List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getProducts() {
        return Collections.unmodifiableList(cartItems);
    }
}