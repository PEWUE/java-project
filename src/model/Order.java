package model;

import enums.OrderStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a single order in the shop system.
 * Stores information about purchased items, customer, discount, order status and final price.
 * Immutable except for the order status.
 */
public class Order implements Serializable {
    private final UUID id;
    private final ZonedDateTime orderDate;
    private final List<CartItem> orderItems;
    private final BigDecimal finalPrice;
    private final Customer customer;
    private final double discountPercent;
    private OrderStatus status;

    /**
     * Creates a new order.
     *
     * @param orderItems      list of items in the order (must not be null or empty)
     * @param customer        the customer placing the order (must not be null)
     * @param discountPercent discount as a fraction (0.0 - 1.0), e.g. 0.15 for 15% discount
     * @throws IllegalArgumentException if orderItems is null/empty, customer is null or discount is out of range
     */
    public Order(List<CartItem> orderItems, Customer customer, double discountPercent) {
        if (orderItems == null || orderItems.isEmpty()) {
            throw new IllegalArgumentException("Zamówienie musi zawierać minimum jeden produkt");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Klient nie może być nullem");
        }
        if (discountPercent < 0.0 || discountPercent > 1.0) {
            throw new IllegalArgumentException("Rabat musi być z przedziału 0–1");
        }
        this.id = UUID.randomUUID();
        this.orderDate = ZonedDateTime.now(ZoneId.of("UTC"));
        this.orderItems = List.copyOf(orderItems);
        this.customer = customer;
        this.finalPrice = orderItems.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .multiply(BigDecimal.valueOf(1 - discountPercent))
                .setScale(2, RoundingMode.HALF_UP);
        this.discountPercent = discountPercent;
        this.status = OrderStatus.NEW;
    }

    public UUID getId() {
        return id;
    }

    public ZonedDateTime getOrderDate() {
        return orderDate;
    }

    public List<CartItem> getOrderItems() {
        return List.copyOf(orderItems);
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setStatus(OrderStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status zamówienia nie może być nullem");
        }
        this.status = status;
    }

    /**
     * Compares this order to another object for equality.
     * <p>
     * Two orders are considered equal if and only if their {@code id} fields are equal,
     * regardless of other fields.
     *
     * @param o the object to compare with
     * @return {@code true} if the given object is an Order with the same id, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    /**
     * Returns hashcode for this order, based only on the unique {@code id} field.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        ZoneId userZone = customer.getTimeZone();
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate.withZoneSameInstant(userZone).toLocalDateTime() +
                ", orderItemsCount=" + orderItems.size() +
                ", status=" + status +
                ", discount=" + String.format("%.2f", discountPercent * 100) + "%" +
                ", finalPrice=" + finalPrice +
                ", customer=" + customer +
                '}';
    }
}
