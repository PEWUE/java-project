package generator;

import model.Customer;

import java.util.List;

public class CustomerGenerator {
    public static List<Customer> sampleCustomers() {
        return List.of(
                new Customer("Jan",
                        "Kowalski",
                        "jan.kowalski@gmail.com",
                        "667788990",
                        "Bratkowa 2",
                        "Kalisz",
                        "99-421",
                        "Polska"),
                new Customer("Marek",
                        "Nowak",
                        "marek.nowak@example.com",
                        "211336655",
                        "Kolejowa 22/21",
                        "Warszawa",
                        "11-321",
                        "Polska")
        );
    }
}
