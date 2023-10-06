package Products;

public class MotherBoard extends Product {
    private final String model;
    private final String socket;

    public MotherBoard(String manufacturer, String model, String socket, double quantity, double price, double rating) {
        super(ProductType.MOTHERBOARD, manufacturer, quantity, price, rating);
        this.model = model;
        this.socket = socket;
    }

    public String getModel() {
        return model;
    }

    public String getSocket() {
        return socket;
    }

    @Override
    public String toString() {
        return "Материнская плата: производитель: \"" +
                getManufacturer() +
                "\", модель: \"" +
                model +
                "\", сокет: \"" +
                socket +
                "\", кол-во: " +
                getQuantity() +
                ", цена: " +
                getPrice() +
                ", рейтинг: " +
                getRating() +
                '.';
    }

    @Override
    public String getTitle() {
        return "Материнская плата: производитель: \"" +
                getManufacturer() +
                "\", модель: \"" +
                model +
                "\", сокет: \"" +
                socket +
                "\", цена: " +
                getPrice();
    }
}