import java.util.List;

public class ShippingService {
    public void sendForShipping(List<ShippableItem> items) {
        System.out.println("Shipment notice");
        double totalWeight = 0;
        for (ShippableItem item : items) {
            System.out.println(item.getName() + " " + item.getWeight() + "g");
            totalWeight += item.getWeight();
        }
        System.out.println("Total package weight " + (totalWeight / 1000.0) + "kg");
    }
}
