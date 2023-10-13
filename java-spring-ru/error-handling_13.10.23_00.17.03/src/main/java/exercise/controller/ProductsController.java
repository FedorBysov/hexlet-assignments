package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "")
    public List<Product> index() {
        return productRepository.findAll();
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // BEGIN

    @GetMapping("/{id}")
    public Product show(@PathVariable long id){
        var product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product with id " + id + " not found"));
        return product;
    }



    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product){
        var maybeProduct = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product with id " + id + " not found"));
        maybeProduct.setPrice(product.getPrice());
        maybeProduct.setTitle(product.getTitle());

        productRepository.save(maybeProduct);
        return product;
    }

    // END

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}
