import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        ShippingService shippingService = new ShippingService();
        Checkout checkoutProcessor = new Checkout(shippingService);

        ExpirableShippableProduct cheese = new ExpirableShippableProduct("Cheese", 100, 5, LocalDate.now().plus(30, ChronoUnit.DAYS), 200); // 200g
        ShippableProduct tv = new ShippableProduct("TV", 500, 2, 10000); // 10kg
        Product mobileScratchCard = new Product("Mobile Scratch Card", 50, 10);
        ExpirableShippableProduct biscuits = new ExpirableShippableProduct("Biscuits", 150, 3, LocalDate.now().plus(10, ChronoUnit.DAYS), 700); // 700g

        Customer customer = new Customer("Basmala Moustafa", 2000); // Increased balance for successful checkout

        Cart myCart = new Cart();

        System.out.println("Adding items to cart...");
        myCart.addItem(cheese, 2);
        myCart.addItem(tv, 1);
        myCart.addItem(mobileScratchCard, 1);
        myCart.addItem(biscuits, 1);

        System.out.println("\nCustomer " + customer.getName() + "'s balance before checkout: " + customer.getBalance());

        checkoutProcessor.processCheckout(customer, myCart);

        System.out.println("\nCustomer " + customer.getName() + "'s balance after checkout: " + customer.getBalance());
        System.out.println("Cheese quantity after checkout: " + cheese.getQuantity());
        System.out.println("TV quantity after checkout: " + tv.getQuantity());
        System.out.println("Mobile Scratch Card quantity after checkout: " + mobileScratchCard.getQuantity());
        System.out.println("Biscuits quantity after checkout: " + biscuits.getQuantity());
    }
}

