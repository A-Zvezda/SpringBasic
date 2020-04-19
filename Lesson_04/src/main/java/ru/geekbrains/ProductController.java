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
import java.util.List;
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
        List<Product> productList = productService.getAllProducts();
        if (productList.size() <= 20 ) {
            Random objGenerator = new Random();
            for (int i = productList.size(); i <=20 ; i++) {
                Product product = new Product("product" + i, new BigDecimal(objGenerator.nextInt(100)));
                productService.insert(product);
            }
        }
        model.addAttribute("products", productList);
        return "products";
    }

    @GetMapping(params = {"id"})
    public String findById(Model model,@RequestParam(name = "id") Long id) {
        model.addAttribute("products", productService.findById(id));
        return "products";
    }

    @GetMapping(params = {"minPrice","maxPrice"})
    public String findByMaxMinPrice(Model model,@RequestParam(name = "minPrice") BigDecimal minPrice, @RequestParam(name = "maxPrice") BigDecimal maxPrice) {
        if ( maxPrice != null & minPrice != null) {
            model.addAttribute("products", productService.findByPriceMinMax(minPrice, maxPrice));
        } else if (minPrice != null) {
            model.addAttribute("products", productService.findByPriceMin(minPrice));
        } else if (maxPrice != null ) {
            model.addAttribute("products", productService.findByPriceMax(maxPrice));
        } else {
            model.addAttribute("products", productService.getAllProducts());
        }
        return "products";
    }
    @GetMapping(params = {"minPrice"})
    public String findByMinPrice(Model model,@RequestParam(name = "minPrice") BigDecimal minPrice) {
        model.addAttribute("products", productService.findByPriceMin(minPrice));
        return "products";
    }
    @GetMapping(params = {"maxPrice"})
    public String findByMaxPrice(Model model,@RequestParam(name = "maxPrice") BigDecimal maxPrice) {
        model.addAttribute("products", productService.findByPriceMax(maxPrice));
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