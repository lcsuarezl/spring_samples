package co.com.jvirtualstore.search;

import co.com.jvirtualstore.model.Product;
import javafx.util.Pair;
import java.util.Arrays;

public class ProductSearchRequest extends SearchRequest<Product> {

    public ProductSearchRequest(Integer pageSize, Integer pageNumber, String name, String description, Direction nameSort, Direction descriptionSort){
        this.setSize(pageSize);
        this.setPage(pageNumber);
        this.setT(new Product());
        this.getT().setName(name);
        this.getT().setDescription(description);
        Pair<String, Direction> nameSortPair = new Pair<>("name",nameSort);
        Pair<String, Direction> descriptionSortPair = new Pair<>("description",descriptionSort);
        this.setSorting(Arrays.asList(nameSortPair, descriptionSortPair));
     }

}
