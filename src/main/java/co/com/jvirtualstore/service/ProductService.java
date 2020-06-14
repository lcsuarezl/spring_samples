package co.com.jvirtualstore.service;


import co.com.jvirtualstore.model.Product;
import co.com.jvirtualstore.repository.ProductRepository;
import co.com.jvirtualstore.search.ProductSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product readProduct(Long id){
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            return productOptional.get();
        }
        return null;
    }

    public Product save(Product product){
        return  productRepository.save(product);
    }

    public Boolean delete(Long id){
        productRepository.deleteById(id);
        return true;
    }

    public List<Product> getAllPageable(ProductSearchRequest productSearchRequest){
        Pageable pageable = PageRequest.of(productSearchRequest.getPage(), productSearchRequest.getSize(), productSearchRequest.getSort());
        if(productSearchRequest.getT().getName() != null && productSearchRequest.getT().getDescription()!=null) {
            return productRepository.findByNameStartsWithAndDescriptionStartsWith(productSearchRequest.getT().getName(), productSearchRequest.getT().getDescription(), pageable);
        }
        if(productSearchRequest.getT().getName() != null){
            return productRepository.findByNameStartsWith(productSearchRequest.getT().getName(), pageable);
        }
        if(productSearchRequest.getT().getDescription()!=null){
            return productRepository.findByDescriptionStartsWith(productSearchRequest.getT().getDescription(), pageable);
        }
        return productRepository.findAll(pageable).getContent();
    }

}
