package com.example.it_market.web_or_presentation.controller;

import com.example.it_market.model.Product;
import com.example.it_market.model.User;
import com.example.it_market.persistence_or_repository.UserRepository;
import com.example.it_market.service_or_business.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private ProductService productService;

    private UserRepository userRepository;

    public AdminController(ProductService productService, UserRepository userRepository) {
        this.productService = productService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getAdminPage(Model model) {
        List<Product> products = this.productService.findAll();
        List<User> users = this.userRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("user", users);
        return "admin";
    }


    @PostMapping("/{id}/delete")
    public String deleteProductWithPost(@PathVariable Long id) {
        this.productService.deleteById(id);
        return "redirect:/admin";
    }


    @PostMapping("/{username}/block")
    public String BlockUser(@PathVariable String username) {
        this.userRepository.deleteById(username);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/publish")
    public String PublishProduct(@PathVariable Long id) {
        this.productService.publishProduct(id);
        return "redirect:/admin";
    }

}
