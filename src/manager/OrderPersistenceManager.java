package manager;

import model.Order;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Utility class for saving and loading orders to and from a file using Java serialization.
 * <p>
 * Provides static methods for persisting and retrieving lists of orders.
 */
//TODO zrobić generyczną klasę do zapisu np. klientów, produktów itd?
public class OrderPersistenceManager {
    /**
     * Saves the given list of orders to a file using Java object serialization.
     *
     * @param orders   the list of orders to save (must not be null)
     * @param filename the name of the file to which the orders will be saved
     * @throws IOException if an I/O error occurs during writing
     */
    public static void saveOrdersToFile(List<Order> orders, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(orders);
        }
    }

    /**
     * Loads a list of orders from a file using Java object deserialization.
     *
     * @param filename the name of the file from which to load orders
     * @return the list of orders loaded from the file
     * @throws IOException            if an I/O error occurs during reading
     * @throws ClassNotFoundException if the file does not contain valid order data
     */
    public static List<Order> loadOrdersFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Order>) ois.readObject();
        }
    }
}
