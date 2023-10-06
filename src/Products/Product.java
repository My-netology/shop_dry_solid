package Products;

public class Product {
    private final ProductType type;
    private final String manufacturer;
    private double quantity;
    private double price;
    private double rating;

    public Product(ProductType type, String manufacturer, double quantity, double price, double rating) {
        this.type = type;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.price = price;
        this.rating = rating;
    }

    public ProductType getType() {
        return type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void incQuantity(double quantity) {
        this.quantity = this.quantity + quantity;
    }

    public void decQuantity(double quantity) {
        this.quantity = this.quantity - quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Product: type = \"" +
                type +
                "\", manufacturer = \"" +
                manufacturer +
                "\", quantity = " +
                quantity +
                ", price = " +
                price +
                ", rating = " +
                rating;
    }

    public String getTitle() {
        return "Product: type = \"" +
                type +
                "\", manufacturer = \"" +
                manufacturer +
                "\", price = " +
                price;
    }
}