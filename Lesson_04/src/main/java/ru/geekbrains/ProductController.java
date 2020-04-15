package ru.geekbrains;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.product.Product;
import ru.geekbrains.product.ProductRepository;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    @GetMapping ("")
//    public String allProducts(Model model) {
//        model.addAttribute("products", productRepository.getAllProducts());
//        return "products";
//    }

    @GetMapping(params = {"id"})
    public String findById(Model model,@RequestParam(name = "id") int id) {
        model.addAttribute("products", productRepository.findByID(id));
        return "products";
    }
    @GetMapping
    public String allProducts(Model model) {
        model.addAttribute("products", productRepository.getAllProducts());
        return "products";
    }


    @PostMapping("/form_add")
    public String newProduct(Product product) {
        productRepository.insert(product);
        return "redirect:/products";
    }
}