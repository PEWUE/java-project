package processor;

import cli.ShopCLI;
import manager.OrderPersistenceManager;
import manager.ProductManager;
import model.Order;

import java.io.IOException;
import java.util.List;

/**
 * Runnable task for asynchronously processing an order.
 * <p>
 * Handles order processing, updates inventory, saves the order list to file,
 * and optionally generates and prints an invoice.
 */
public class OrderProcessingTask implements Runnable {
    private final Order order;
    private final ProductManager productManager;
    private final List<Order> orders;
    private final boolean generateInvoice;

    /**
     * Constructs a new order processing task.
     *
     * @param order           the order to process
     * @param productManager  the product manager (inventory)
     * @param orders          the list of all orders (shared resource)
     * @param generateInvoice whether to generate and print an invoice after processing
     */
    public OrderProcessingTask(Order order, ProductManager productManager, List<Order> orders, boolean generateInvoice) {
        this.order = order;
        this.productManager = productManager;
        this.orders = orders;
        this.generateInvoice = generateInvoice;
    }

    /**
     * Runs the order processing task in a separate thread.
     * <p>
     * Processes the order, updates inventory, saves the order list, and prints the invoice if requested.
     * Handles synchronization on shared resources.
     */
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
