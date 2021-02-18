package com.example.it_market.web_or_presentation.controller;

import com.example.it_market.model.Product;
import com.example.it_market.service_or_business.ProductService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping
    public String indexPage() {
        return "redirect:/index";
    }

    @GetMapping("/home")
    public String getHomePage(HttpServletResponse res, HttpServletRequest req) {
        return "index";
    }



}
