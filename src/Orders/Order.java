package Orders;

import Products.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<Item> items;
    private OrderStatus status;

    public Order() {
        items = new ArrayList<>();
        status = OrderStatus.NEW;
    }

    public boolean addItem(Item item) {
        return items.add(item);
    }

    public boolean delItem(int index) {
        return items.remove(items.get(index));
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Item> getItems() {
        return items;
    }
}