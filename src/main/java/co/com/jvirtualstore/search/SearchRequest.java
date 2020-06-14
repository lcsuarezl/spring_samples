package co.com.jvirtualstore.search;

import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class SearchRequest<T> {

    private static Logger LOG = LoggerFactory.getLogger(SearchRequest.class);

    private T t;

    private Integer page;

    private Integer size;

    private List<Pair<String, Direction>> sorting;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Pair<String, Direction>> getSorting() {
        return sorting;
    }

    public void setSorting(List<Pair<String, Direction>> sorting) {
        this.sorting = sorting;
    }

    public Sort.Direction getDirection(Direction direction){
        return Sort.Direction.valueOf(direction.name());
    }

    public Sort getSort(){
        List<Sort.Order> orders = new ArrayList<>();
        for (Pair<String, Direction> pair:this.getSorting()) {
            LOG.info("[{}] -> [{}]", pair.getKey(), pair.getValue());
            if(pair.getValue() != null) {
                Sort.Order order = new Sort.Order(this.getDirection(pair.getValue()), pair.getKey());
                orders.add(order);
            }
        }
        return Sort.by(orders);
    }

}
