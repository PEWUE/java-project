package manager;

import exceptions.ProductAlreadyExistException;
import model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Manages the collection of products in the shop.
 * <p>
 * Provides methods to add, remove, update, and retrieve products by their unique identifier.
 */
public class ProductManager {
    private final Map<UUID, Product> products = new HashMap<>();

    /**
     * Adds a new product to the collection.
     *
     * @param product the product to add (must not be null)
     * @throws ProductAlreadyExistException if a product with the same ID already exists
     */
    public void addProduct(Product product) {
        if (products.containsKey(product.getId())) {
            throw new ProductAlreadyExistException("Produkt o podanym ID już istnieje");
        }
        products.put(product.getId(), product);
    }

    /**
     * Removes the product with the specified ID from the collection.
     *
     * @param productID the unique identifier of the product to remove
     * @return {@code true} if the product was removed, {@code false} if it was not found
     */
    public boolean removeProduct(UUID productID) {
        return products.remove(productID) != null;
    }

    /**
     * Updates the product with the specified ID in the collection.
     *
     * @param updatedProduct the product with updated information
     * @return {@code true} if the product was found and updated, {@code false} otherwise
     */
    public boolean updateProduct(Product updatedProduct) {
        UUID id = updatedProduct.getId();
        if (!products.containsKey(id)) {
            return false;
        }
        products.put(id, updatedProduct);
        return true;
    }

    /**
     * Retrieves the product with the specified ID.
     *
     * @param productID the unique identifier of the product
     * @return an {@code Optional} containing the product if found, or empty if not found
     */
    public Optional<Product> getProductById(UUID productID) {
        return Optional.ofNullable(products.get(productID));
    }

    /**
     * Returns a list of all products currently managed.
     *
     * @return an unmodifiable list of all products
     */
    public List<Product> getAllProducts() {
        return List.copyOf(products.values());
    }
}
