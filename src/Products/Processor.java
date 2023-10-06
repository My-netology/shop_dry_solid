package Products;

public class Processor extends Product {
    private static final String PRODUCT_NAME = "Процессор";
    private final String model;
    private final String socket;
    private final String frequency;

    public Processor(String manufacturer, String model, String socket, String frequency, double quantity, double price, double rating) {
        super(ProductType.PROCESSOR, manufacturer, quantity, price, rating);
        this.model = model;
        this.socket = socket;
        this.frequency = frequency;
    }

    public String getModel() {
        return model;
    }

    public String getSocket() {
        return socket;
    }

    public String getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return "Процессор: производитель: \"" +
                getManufacturer() +
                "\", модель: \"" +
                model +
                "\", сокет: \"" +
                socket +
                "\", частота: " +
                frequency +
                ", кол-во: " +
                getQuantity() +
                ", цена: " +
                getPrice() +
                ", рейтинг: " +
                getRating() +
                '.';
    }

    @Override
    public String getTitle() {
        return "Процессор: производитель: \"" +
                getManufacturer() +
                "\", модель: \"" +
                model +
                "\", сокет: \"" +
                socket +
                "\", частота: " +
                frequency +
                ", цена: " +
                getPrice();
    }
}