package co.com.jvirtualstore.controller;


import co.com.jvirtualstore.model.Product;
import co.com.jvirtualstore.search.ProductSearchRequest;
import co.com.jvirtualstore.search.Direction;
import co.com.jvirtualstore.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product readProduct(@PathVariable Long id){
        return productService.readProduct(id);
    }

    @GetMapping("/search")
    public List<Product> findProduct(@RequestParam(value="name", required = false) String name,
                                     @RequestParam(value="description", required = false) String description,
                                     @RequestParam(value="nameSort", required = false) Direction nameSort,
                                     @RequestParam(value="descriptionSort", required = false) Direction descriptionSort,
                                     @RequestParam(value="pageNumber", required = false) Integer pageNumber,
                                     @RequestParam(value = "pageSize", required = false) Integer pageSize
                               ){
        ProductSearchRequest productSearchRequest = new ProductSearchRequest(pageSize,pageNumber, name, description, nameSort, descriptionSort);
        return productService.getAllPageable(productSearchRequest);
    }


    @PutMapping
    public Product editPorduct(@RequestBody Product product){
        return productService.save(product);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteProduct(@PathVariable Long id){
        return productService.delete(id);
    }



}
