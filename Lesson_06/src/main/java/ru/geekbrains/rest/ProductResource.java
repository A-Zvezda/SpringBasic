package ru.geekbrains.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.Product;
import ru.geekbrains.service.ProductService;

import java.util.List;

@RequestMapping("/api/v1/product")
@RestController
public class  ProductResource{

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(path = "/{id}/id", produces = "application/json")
    public Product findById(@PathVariable("id") long id) {
          return productService.findById(id)
                .orElseThrow(() -> new NotFoundException ("Product not found"));
    }

    @PostMapping
    public void createProduct (@RequestBody Product product) {
        if (productService.findById(product.getId()).isPresent()) {
            throw new AlreadyExistException("Id field found in create request");
        }
        productService.insertOrUpdate(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        productService.insertOrUpdate(product);
    }

    @DeleteMapping(path = "/{id}/id", produces = "application/json")
    public void deleteProduct(@PathVariable("id") long id) {
        productService.deleteById(id);
    }

//    @ExceptionHandler
//    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException exception) {
//        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<String> illegalArgumentException(IllegalArgumentException exception) {
//        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
//    }
}