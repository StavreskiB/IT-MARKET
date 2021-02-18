package com.example.it_market.web_or_presentation.controller;

import com.example.it_market.model.Category;
import com.example.it_market.model.Product;
import com.example.it_market.model.Subcategory;
import com.example.it_market.model.User;
import com.example.it_market.persistence_or_repository.CategoryRepository;
import com.example.it_market.persistence_or_repository.SubCategoryRepository;
import com.example.it_market.persistence_or_repository.UserRepository;
import com.example.it_market.service_or_business.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {


    private ProductService productService;
    private CategoryRepository categoryRepository;
    private SubCategoryRepository subCategoryRepository;
    private UserRepository userRepository;


    public ProductController(ProductService productService, UserRepository userRepository, CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getProductPage(Model model) {
        List<Product> products = this.productService.findAll();
        List<Category> category = this.categoryRepository.findAll();
        List<Subcategory> subcategory = this.subCategoryRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        model.addAttribute("subcategory", subcategory);
        return "index";
    }


    @GetMapping("/products")
    public String getProductAfterLogin(Model model) {
        List<Product> products = this.productService.findAll();
        model.addAttribute("products", products);
        List<Category> category = this.categoryRepository.findAll();
        List<Subcategory> subcategory = this.subCategoryRepository.findAll();
        model.addAttribute("category", category);
        model.addAttribute("subcategory", subcategory);
        return "products";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/add-new")
    public String addNewProductPage(Model model) {
        model.addAttribute("category", this.categoryRepository.findAll());
        model.addAttribute("subcategory", this.subCategoryRepository.findAll());
        model.addAttribute("product", new Product());
        return "add-product";
    }


    @PostMapping
    public String saveProduct(
            @Valid Product product,
            @RequestParam MultipartFile image) {

        try {
            this.productService.saveProduct(product, image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/products";
    }



}
