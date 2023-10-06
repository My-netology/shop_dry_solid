package Orders;

import Products.Product;

public class Item {
    private final int productId;
    private double quantity;

    public Item(int productId, double quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }
}
