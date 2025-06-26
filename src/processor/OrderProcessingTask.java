package processor;

import cli.ShopCLI;
import manager.OrderPersistenceManager;
import manager.ProductManager;
import model.Order;

import java.io.IOException;
import java.util.List;

public class OrderProcessingTask implements Runnable {
    private final Order order;
    private final ProductManager productManager;
    private final List<Order> orders;
    private final boolean generateInvoice;

    public OrderProcessingTask(Order order, ProductManager productManager, List<Order> orders, boolean generateInvoice) {
        this.order = order;
        this.productManager = productManager;
        this.orders = orders;
        this.generateInvoice = generateInvoice;
    }

    @Override
    public void run() {
        try {
            synchronized (productManager) {
                OrderProcessor.processOrder(order, productManager);
            }
            synchronized (orders) {
                orders.add(order);
                OrderPersistenceManager.saveOrdersToFile(orders, ShopCLI.ORDERS_FILENAME);
            }
            if (generateInvoice) {
                System.out.println("FAKTURA");
                System.out.println(OrderProcessor.generateInvoice(order));
            }
        } catch (IOException e) {
            System.err.println("Błąd podczas przetwarzania zamówienia " + order.getId() + ": " + e.getMessage());
        }
    }
}
