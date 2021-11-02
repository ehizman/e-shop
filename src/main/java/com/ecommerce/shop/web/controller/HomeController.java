package com.ecommerce.shop.web.controller;

import com.ecommerce.shop.data.model.Product;
import com.ecommerce.shop.service.ProductService;
import com.ecommerce.shop.service.exceptions.ProductDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class HomeController {
    ProductService productService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"/", "/index"})
    public String getIndex(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("products", productList);
        return "indexPage";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }

    @GetMapping("/contact")
    public String getContact() {
        return "contact";
    }

    @GetMapping("/single/{id}")
    public ModelAndView getProductById(@PathVariable Long id) throws ProductDoesNotExistException {
        try {
            ModelAndView modelAndView = new ModelAndView("productDetails");
            Product product = productService.findProductById(id);
            log.info("product --> {} ", product);
            modelAndView.addObject("product", product);
            return modelAndView;
        } catch (ProductDoesNotExistException ex) {
            return new ModelAndView("errorPage");
        }
    }

    @GetMapping("/services")
    public String getServices() {
        return "services";
    }

}
