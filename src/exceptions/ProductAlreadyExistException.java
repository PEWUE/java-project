package exceptions;

/**
 * Thrown to indicate that a product with the given identifier already exists in the inventory.
 */
public class ProductAlreadyExistException extends RuntimeException {
    public ProductAlreadyExistException(String message) {
        super(message);
    }
}
