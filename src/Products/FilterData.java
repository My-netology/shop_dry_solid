package Products;

import java.util.List;

public interface FilterData {
    public List<Product> filter(List<Product> source, int filterType, String filterStr);
}