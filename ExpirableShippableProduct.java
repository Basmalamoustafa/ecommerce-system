import java.time.LocalDate;

public class ExpirableShippableProduct extends ExpirableProduct {
    private double weight;

    public ExpirableShippableProduct(String name, double price, int quantity, LocalDate expirationDate, double weight) {
        super(name, price, quantity, expirationDate);
        this.weight = weight;
    }
    public double getWeight() {
        return weight;
    }
}

