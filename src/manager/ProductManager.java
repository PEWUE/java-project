package manager;

import exceptions.ProductAlreadyExistException;
import exceptions.ProductNotFoundException;
import model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ProductManager {
    private final Map<UUID, Product> productMap = new HashMap<>();

    public void addProduct(Product product) {
        if (productMap.containsKey(product.getId())) {
            throw new ProductAlreadyExistException("Produkt o podanym ID już istnieje");
        }
        productMap.put(product.getId(), product);
    }

    public boolean removeProduct(UUID productID) {
        return productMap.remove(productID) != null;
    }

    public boolean updateProduct(Product updatedProduct) {
        UUID id = updatedProduct.getId();
        if (!productMap.containsKey(id)) {
            return false;
        }
        productMap.put(id, updatedProduct);
        return true;
    }

    public Optional<Product> getProductById(UUID productID) {
        return Optional.ofNullable(productMap.get(productID));
    }

    public List<Product> getAllProducts() {
        return List.copyOf(productMap.values());
    }
}
