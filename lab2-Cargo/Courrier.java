import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Courrier {
//    See these code in another file: ShopItem.java
//
//    public static class ShopItem {
//        public String label;
//        public float quantity; // in kg
//        public float profitForQuantity;
//
//        public ShopItem(String label, float quantity, float profitForQuantity) {
//            this.label = label;
//            this.quantity = quantity;
//            this.profitForQuantity = profitForQuantity;
//        }
//
//        // Method to calculate profit per kg
//        public float profitPerKg() {
//            return profitForQuantity / quantity;
//        }
//    }

    public List<ShopItem> choice(List<ShopItem> wishes, float maxQuantity) {
        // Sort the wishes list based on profit per kg in descending order
        Collections.sort(wishes, new Comparator<ShopItem>() {
            @Override
            public int compare(ShopItem o1, ShopItem o2) {
                return Float.compare(o2.profitPerKg(), o1.profitPerKg());
            }
        });

        List<ShopItem> selectedItems = new ArrayList<>();
        float remainingCapacity = maxQuantity;

        // Select items based on the sorted order
        for (ShopItem item : wishes) {
            if (remainingCapacity == 0) {
                break;
            }
            float quantityTaken = Math.min(item.quantity, remainingCapacity);
            float profitForTakenQuantity = quantityTaken * item.profitPerKg();

            // Add to the list of selected items
            selectedItems.add(new ShopItem(item.label, quantityTaken, profitForTakenQuantity));
            remainingCapacity -= quantityTaken;
        }

        return selectedItems;
    }

    public static void main(String[] args) {
        List<ShopItem> wishes = new ArrayList<>();
        wishes.add(new ShopItem("Flour", 20.5f, 140));
        wishes.add(new ShopItem("Sugar", 10.0f, 80));

        Courrier courrier = new Courrier();
        List<ShopItem> result = courrier.choice(wishes, 25.0f);

        for (ShopItem item : result) {
            System.out.println("Label: " + item.label + ", Quantity: " + item.quantity + " kg, Profit: " + item.profitForQuantity + " RMB");
        }
    }
}
