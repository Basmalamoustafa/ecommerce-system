import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestEcommerce {

    static class MockShippingService extends ShippingService {
        @Override
        public void sendForShipping(List<ShippableItem> items) {
            System.out.println("Mock Shipment notice");
            double totalWeight = 0;
            for (ShippableItem item : items) {
                System.out.println(item.getName() + " " + item.getWeight() + "g");
                totalWeight += item.getWeight();
            }
            System.out.println("Total package weight " + (totalWeight / 1000.0) + "kg");
        }
    }

    public static void runTests() {
        System.out.println("--- Running E-commerce System Tests ---");

        MockShippingService mockShippingService = new MockShippingService();
        Checkout checkoutProcessor = new Checkout(mockShippingService);

        System.out.println("\n--- Test Case 1: Successful Checkout with Mixed Products ---");
        ExpirableShippableProduct cheese = new ExpirableShippableProduct("Cheese", 100, 5, LocalDate.now().plus(30, ChronoUnit.DAYS), 200);
        ShippableProduct tv = new ShippableProduct("TV", 500, 2, 10000);
        Product mobileScratchCard = new Product("Mobile Scratch Card", 50, 10);
        Customer customer1 = new Customer("Basmala", 1000);
        Cart cart1 = new Cart();

        cart1.addItem(cheese, 2);
        cart1.addItem(tv, 1);
        cart1.addItem(mobileScratchCard, 1);

        System.out.println("Customer Basmala's balance before checkout: " + customer1.getBalance());
        checkoutProcessor.processCheckout(customer1, cart1);
        System.out.println("Customer Basmala's balance after checkout: " + customer1.getBalance());
        System.out.println("Cheese quantity after checkout: " + cheese.getQuantity());
        System.out.println("TV quantity after checkout: " + tv.getQuantity());
        System.out.println("Mobile Scratch Card quantity after checkout: " + mobileScratchCard.getQuantity());

        System.out.println("\n--- Test Case 2: Insufficient Balance ---");
        Customer customer2 = new Customer("Mohamed", 50);
        Cart cart2 = new Cart();
        cart2.addItem(cheese, 1);
        System.out.println("Customer Mohamed's balance before checkout: " + customer2.getBalance());
        checkoutProcessor.processCheckout(customer2, cart2);
        System.out.println("Customer Mohamed's balance after checkout: " + customer2.getBalance());

        System.out.println("\n--- Test Case 3: Product Out of Stock ---");
        Customer customer3 = new Customer("Salma", 1000);
        Cart cart3 = new Cart();
        boolean success = cart3.addItem(tv, 5);
        if (!success) {
            System.out.println("Cart add item failed: Not enough stock");
        }
        System.out.println("Customer Salma's balance before checkout: " + customer3.getBalance());
        checkoutProcessor.processCheckout(customer3, cart3);
        System.out.println("Customer Salma's balance after checkout: " + customer3.getBalance());

        System.out.println("\n--- Test Case 4: Expired Product ---");
        ExpirableProduct expiredBiscuits = new ExpirableProduct("Expired Biscuits", 50, 10, LocalDate.now().minus(10, ChronoUnit.DAYS));
        Customer customer4 = new Customer("Rahma", 1000);
        Cart cart4 = new Cart();
        cart4.addItem(expiredBiscuits, 1);
        System.out.println("Customer Rahma's balance before checkout: " + customer4.getBalance());
        checkoutProcessor.processCheckout(customer4, cart4);
        System.out.println("Customer Rahma's balance after checkout: " + customer4.getBalance());

        System.out.println("\n--- Test Case 5: Empty Cart ---");
        Customer customer5 = new Customer("Sara", 1000);
        Cart cart5 = new Cart();
        System.out.println("Customer Sara's balance before checkout: " + customer5.getBalance());
        checkoutProcessor.processCheckout(customer5, cart5);
        System.out.println("Customer Sara's balance after checkout: " + customer5.getBalance());

        System.out.println("\n--- Test Case 6: Product with no shipping ---");
        Customer customer6 = new Customer("Salah", 1000);
        Cart cart6 = new Cart();
        cart6.addItem(mobileScratchCard, 2);
        System.out.println("Customer Salah's balance before checkout: " + customer6.getBalance());
        checkoutProcessor.processCheckout(customer6, cart6);
        System.out.println("Customer Salah's balance after checkout: " + customer6.getBalance());
    }

    public static void main(String[] args) {
        runTests();
    }
}

