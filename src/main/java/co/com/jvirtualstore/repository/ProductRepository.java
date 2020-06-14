package co.com.jvirtualstore.repository;

import co.com.jvirtualstore.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findByNameStartsWith(String name, Pageable pageable);

    List<Product> findByDescriptionStartsWith(String description, Pageable pageable);

    List<Product> findByNameStartsWithAndDescriptionStartsWith(String name, String description, Pageable pageable);

}
