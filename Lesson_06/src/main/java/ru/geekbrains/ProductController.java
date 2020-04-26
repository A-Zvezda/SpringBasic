package ru.geekbrains;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
import java.util.Optional;
import java.util.Random;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping(params = {"id"})
    public String findById(Model model,@RequestParam(name = "id") Long id) {
        model.addAttribute("products", productService.findById(id));
        return "products";
    }

    @GetMapping()
    public String allProducts(Model model,
                                    @RequestParam(name = "minPrice") Optional<BigDecimal> minPrice,
                                    @RequestParam(name = "maxPrice") Optional<BigDecimal> maxPrice,
                                    @RequestParam(value = "page") Optional<Integer> page,
                                    @RequestParam(value = "size") Optional<Integer> size) {
        model.addAttribute("activePage", "Products");
        model.addAttribute("productsPage", productService.allProducts(
                minPrice, maxPrice,
                PageRequest.of(page.orElse(1) - 1, size.orElse(25))
        ));
        model.addAttribute("minPrice", minPrice.orElse(null));
        model.addAttribute("maxPrice", maxPrice.orElse(null));
        return "products";
    }


    @GetMapping("/form")
    public String formProduct(Model model,
                              @RequestParam(name = "id") Optional<Long> id) {
        model.addAttribute("activePage", "Products");
        model.addAttribute("product", productService.editOrAddProduct(id));
        return "product_form";
    }

    @PostMapping("/form_add")
    public String newProduct(Product product) {
        productService.insertOrUpdate(product);
        return "redirect:/products";
    }
}