import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<Product, Integer> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    public boolean addItem(Product product, int quantity) {
        if (product.getQuantity() < quantity) {
            System.out.println("Not enough stock for " + product.getName());
            return false;
        }
        items.put(product, items.getOrDefault(product, 0) + quantity);
        return true;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
}

