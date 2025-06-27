package processor;

import enums.OrderStatus;
import exceptions.ProductNotFoundException;
import manager.ProductManager;
import model.CartItem;
import model.Customer;
import model.Order;
import model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Provides static methods for processing orders and generating invoices.
 * <p>
 * Handles order validation, stock management, and invoice formatting.
 */
public class OrderProcessor {
    private static final AtomicInteger invoiceCounter = new AtomicInteger(1);

    /**
     * Processes the given order: checks product availability, updates inventory,
     * removes products with zero stock, and updates the order status.
     *
     * @param order          the order to process (must not be null, status must be NEW)
     * @param productManager the product manager (inventory)
     * @throws IllegalArgumentException if the order is null
     * @throws IllegalStateException    if the order status is not NEW or if there is insufficient stock
     * @throws ProductNotFoundException if a product in the order does not exist in the inventory
     */
    public static void processOrder(Order order, ProductManager productManager) {
        if (order == null) {
            throw new IllegalArgumentException("Zamówienie nie może być nullem");
        }
        if (order.getStatus() != OrderStatus.NEW) {
            throw new IllegalStateException("Zamówienie nie może zostać przetworzone. Jego obecny status to: " + order.getStatus());
        }
        //TODO
        // czy jest sens tworzyć OrderItem, jeśli byłby praktycznie kopią CartItem?
        for (CartItem orderItem : order.getOrderItems()) {
            Product product = productManager.getProductById(orderItem.getProduct().getId())
                    .orElseThrow(() -> new ProductNotFoundException("Produkt nie istnieje w magazynie"));
            if (product.getQuantity() < orderItem.getQuantity()) {
                throw new IllegalStateException("Brak wystarczającej ilości: " + product.getName() + " Dostępna ilość: " + product.getQuantity());
            }
        }
        for (CartItem orderItem : order.getOrderItems()) {
            Optional<Product> product = productManager.getProductById(orderItem.getProduct().getId());
            product.ifPresent(prod -> {
                prod.setQuantity(prod.getQuantity() - orderItem.getQuantity());
                productManager.updateProduct(prod);
                if (prod.getQuantity() == 0) {
                    productManager.removeProduct(prod.getId());
                }
            });

        }
        order.setStatus(OrderStatus.PAID);
    }

    /**
     * Generates a formatted invoice for the given order.
     * <p>
     * The invoice includes customer details, a list of ordered products,
     * applied discounts, and the total amount due.
     * Can only be generated for orders with status PAID, SHIPPED, or COMPLETED.
     *
     * @param order the order for which to generate the invoice (must not be null)
     * @return the invoice as a formatted String
     * @throws IllegalArgumentException if the order is null
     * @throws IllegalStateException    if the order status does not allow invoice generation
     */
    public static String generateInvoice(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Zamówienie nie może być nullem");
        }
        //TODO ten switch jest ok czy lepiej if'ować?
        switch (order.getStatus()) {
            case PAID:
            case SHIPPED:
            case COMPLETED:
                break; // wyżej wymienione statusy pozwalają na wygenerowanie faktury
            default:
                throw new IllegalStateException("Fakturę można wygenerować tylko dla opłaconych zamówień. Obecny status: " + order.getStatus());
        }

        int invoiceNumber = invoiceCounter.getAndIncrement();
        Customer customer = order.getCustomer();
        ZoneId userZone = customer.getTimeZone();

        StringBuilder sb = new StringBuilder();
        sb.append("Faktura nr: ").append(invoiceNumber).append("\n");
        sb.append("Data zakupu: ").append(order.getOrderDate().withZoneSameInstant(userZone).format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))).append(" ").append(userZone).append("\n");
        sb.append("Dane klienta:\n");
        sb.append(customer.getFirstName()).append(" ").append(customer.getLastName()).append("\n");
        sb.append(customer.getStreet()).append("\n");
        sb.append(customer.getPostalCode()).append(" ").append(customer.getCity()).append("\n");
        sb.append(customer.getCountry()).append("\n");
        sb.append("Email: ").append(customer.getEmail()).append("\n");
        sb.append("Telefon: ").append(customer.getPhoneNumber()).append("\n\n");
        sb.append("Zamówione produkty:\n");
        sb.append(String.format("%-30s %-10s %-10s %-10s\n", "Nazwa", "Ilość", "Cena", "Wartość"));
        for (CartItem item : order.getOrderItems()) {
            sb.append(String.format("%-30s %-10d %-10.2f %-10.2f\n",
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getSingleItemPrice(),
                    item.getTotalPrice()));
        }
        BigDecimal basePrice = order.getOrderItems().stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (order.getDiscountPercent() > 0) {
            String formattedDiscount = String.format("%.2f", order.getDiscountPercent() * 100);
            BigDecimal discountAmount = basePrice.subtract(order.getFinalPrice());
            sb.append("Rabat: ").append(formattedDiscount).append("% (")
                    .append(discountAmount.setScale(2, RoundingMode.HALF_UP)).append("zł)");
        }
        sb.append("\nSuma do zapłaty: ").append(order.getFinalPrice()).append(" PLN\n");
        return sb.toString();
    }
}
