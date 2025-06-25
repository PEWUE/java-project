package model;

import enums.OrderStatus;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final ZonedDateTime orderDate;
    private final List<CartItem> orderItems;
    private final BigDecimal finalPrice;
    private final Customer customer;
    private OrderStatus status;

    public Order(List<CartItem> orderItems, Customer customer) {
        if (orderItems == null || orderItems.isEmpty()) {
            throw new IllegalArgumentException("Zamówienie musi zawierać minimum jeden produkt");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Klient nie może być nullem");
        }
        this.id = UUID.randomUUID();
        this.orderDate = ZonedDateTime.now(ZoneId.of("UTC"));
        this.orderItems = List.copyOf(orderItems);
        this.customer = customer;
        this.finalPrice = orderItems.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
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

    public void setStatus(OrderStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status zamówienia nie może być nullem");
        }
        this.status = status;
    }


    //używam tylko id do porównania
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    //używam tylko id w hashCode
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate.toLocalDateTime() +
                ", orderItemsCount=" + orderItems.size() +
                ", status=" + status +
                ", finalPrice=" + finalPrice +
                ", customer=" + customer +
                '}';
    }
}
