package processor;

import enums.OrderStatus;
import model.CartItem;
import model.Customer;
import model.Order;

import java.time.format.DateTimeFormatter;

public class OrderProcessor {
    private static int invoiceCounter = 1;

    public static void processOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Zamówienie nie może być nullem");
        }
        if (order.getStatus() != OrderStatus.NEW) {
            throw new IllegalStateException("Zamówienie nie może zostać przetworzone. Jego obecny status to: " + order.getStatus());
        }
        order.setStatus(OrderStatus.PAID);
    }

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

        int invoiceNumber = invoiceCounter++;
        Customer customer = order.getCustomer();
        StringBuilder sb = new StringBuilder();
        sb.append("Faktura nr: ").append(invoiceNumber).append("\n");
        sb.append("Data zakupu: ").append(order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).append("\n\n");
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
        sb.append("\nSuma do zapłaty: ").append(order.getFinalPrice()).append(" PLN\n");
        return sb.toString();
    }
}
