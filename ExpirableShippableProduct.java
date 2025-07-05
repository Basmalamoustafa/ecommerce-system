import java.time.LocalDate;

public class ExpirableShippableProduct extends ExpirableProduct implements ShippableItem {
    private final double weight;

    public ExpirableShippableProduct(String name, double price, int quantity, LocalDate expirationDate, double weight) {
        super(name, price, quantity, expirationDate);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
