package ru.geekbrains;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import ru.geekbrains.persist.Person;
import ru.geekbrains.persist.Product;
import ru.geekbrains.service.ProductService;

import java.math.BigDecimal;
import java.util.Random;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping ("")
//    public String allProducts(Model model) {
//        model.addAttribute("products", productRepository.getAllProducts());
//        return "products";
//    }
    @GetMapping
    public String allProducts(Model model) {
        Random objGenerator = new Random();
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping(params = {"id"})
    public String findById(Model model,@RequestParam(name = "id") Long id) {
        model.addAttribute("products", productService.findById(id));
        return "products";
    }

    @GetMapping(params = {"minPrice","maxPrice"})
    public String findById(Model model,@RequestParam(name = "minPrice") BigDecimal minPrice, BigDecimal maxPrice) {
        model.addAttribute("products", productService.findByPrice(minPrice,maxPrice));
        return "products";
    }


    @GetMapping("/form")
    public String formProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping("/form_add")
    public String newProduct(Product product) {
        productService.insert(product);
        return "redirect:/products";
    }
}