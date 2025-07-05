import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Checkout {
    private ShippingService shippingService;

    public Checkout(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public void processCheckout(Customer customer, Cart cart) {
        if (cart.getItems().isEmpty()) {
            System.out.println("Error: Cart is empty.");
            return;
        }

        double subtotal = cart.getTotalPrice();
        double shippingFees = 0;
        List<ShippableItem> shippableItemsForService = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product.getQuantity() < quantity) {
                System.out.println("Error: " + product.getName() + " is out of stock. Available: " + product.getQuantity() + ", Requested: " + quantity);
                return;
            }
            if (product instanceof ExpirableProduct) {
                ExpirableProduct expirableProduct = (ExpirableProduct) product;
                if (expirableProduct.isExpired()) {
                    System.out.println("Error: " + product.getName() + " is expired.");
                    return;
                }
            }
            if (product instanceof ShippableProduct) {
                ShippableProduct shippableProduct = (ShippableProduct) product;
                shippableItemsForService.add(new ShippableItem() {
                    @Override
                    public String getName() {
                        return quantity + "x " + shippableProduct.getName();
                    }

                    @Override
                    public double getWeight() {
                        return shippableProduct.getWeight() * quantity;
                    }
                });
                shippingFees += shippableProduct.getWeight() * quantity * 0.01;
            } else if (product instanceof ExpirableShippableProduct) {
                ExpirableShippableProduct expirableShippableProduct = (ExpirableShippableProduct) product;
                shippableItemsForService.add(new ShippableItem() {
                    @Override
                    public String getName() {
                        return quantity + "x " + expirableShippableProduct.getName();
                    }

                    @Override
                    public double getWeight() {
                        return expirableShippableProduct.getWeight() * quantity;
                    }
                });
                shippingFees += expirableShippableProduct.getWeight() * quantity * 0.01;
            }
        }

        double totalPaidAmount = subtotal + shippingFees;

        if (customer.getBalance() < totalPaidAmount) {
            System.out.println("Error: Insufficient balance. Your balance: " + customer.getBalance() + ", Required: " + totalPaidAmount);
            return;
        }

        customer.deductBalance(totalPaidAmount);
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.decreaseQuantity(quantity);
        }

        if (!shippableItemsForService.isEmpty()) {
            shippingService.sendForShipping(shippableItemsForService);
        }

        System.out.println("\nCheckout receipt");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(quantity + "x " + product.getName() + " " + (product.getPrice() * quantity));
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + subtotal);
        System.out.println("Shipping " + shippingFees);
        System.out.println("Amount " + totalPaidAmount);
        System.out.println("Customer current balance: " + customer.getBalance());
    }
}

