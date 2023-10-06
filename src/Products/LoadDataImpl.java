package Products;

import java.util.ArrayList;
import java.util.List;

public class LoadDataImpl implements LoadData {
    @Override
    public List<Product> load() {
        List<Product> products = new ArrayList<>();
        products.add(new MotherBoard("Manufacturer 1", "Model 1", "Socket 1", 100, 1000, 5));
        products.add(new MotherBoard("Manufacturer 2", "Model 2", "Socket 2", 50, 2000, 4.8));
        products.add(new Processor("Manufacturer 1", "Model 3", "Socket 1", "2,4", 125, 1500, 5));
        products.add(new Processor("Manufacturer 2", "Model 4", "Socket 2", "3,2", 25, 2250, 4.5));
        return products;
    }
}