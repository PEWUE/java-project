package manager;

import exceptions.ProductAlreadyExistException;
import model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ProductManager {
    private final Map<UUID, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        if (products.containsKey(product.getId())) {
            throw new ProductAlreadyExistException("Produkt o podanym ID już istnieje");
        }
        products.put(product.getId(), product);
    }

    public boolean removeProduct(UUID productID) {
        return products.remove(productID) != null;
    }

    public boolean updateProduct(Product updatedProduct) {
        UUID id = updatedProduct.getId();
        if (!products.containsKey(id)) {
            return false;
        }
        products.put(id, updatedProduct);
        return true;
    }

    public Optional<Product> getProductById(UUID productID) {
        return Optional.ofNullable(products.get(productID));
    }

    public List<Product> getAllProducts() {
        return List.copyOf(products.values());
    }
}
