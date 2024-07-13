public class ShopItem {
    public String label;

    public float quantity; // in kg

    public float profitForQuantity;
    public ShopItem(String label, float quantity, float profitForQuantity) {
        this.label = label;
        this.quantity = quantity;
        this.profitForQuantity = profitForQuantity;
    }

    public float profitPerKg() {
        return profitForQuantity / quantity;
    }
}
