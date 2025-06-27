package exceptions;

/**
 * Thrown to indicate that a product was not found in the inventory.
 */
public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException(String message) {
    super(message);
  }
}
