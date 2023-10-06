package Products;

import java.util.List;

public class FilterDataImpl implements FilterData {
    @Override
    public List<Product> filter(List<Product> source, int filterType, String filterStr) {
        ProductType filterProductType;
        if (filterType == 2) {
            filterProductType =
                    switch (filterStr) {
                        case "Материнская плата" -> ProductType.MOTHERBOARD;
                        case "Процессор" -> ProductType.PROCESSOR;
                        default -> ProductType.NONE;
                    };
        } else {
            filterProductType = ProductType.NONE;
        }
        return source
                .stream()
                .filter(x -> (filterType != 1 || x.getManufacturer().equalsIgnoreCase(filterStr)))
                .filter(x -> (filterType != 2 || x.getType() == filterProductType))
                .toList();
    }
}