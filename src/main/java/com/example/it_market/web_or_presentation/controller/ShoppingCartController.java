package com.example.it_market.web_or_presentation.controller;

import com.example.it_market.model.ShoppingCart;
import com.example.it_market.service_or_business.AuthService;
import com.example.it_market.service_or_business.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopping_cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final AuthService authService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, AuthService authService) {
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
    }


    @PostMapping("/{productId}/add-product")
    public String addProductToShoppingCart(@PathVariable Long productId) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartService.addProductToShoppingCart(this.authService.getCurrentUserId(), productId);
        } catch (RuntimeException ex) {
            return "redirect:/products?error=" + ex.getLocalizedMessage();
        }
        return "redirect:/products";
    }

    @GetMapping("")
    public String getCheckoutPage(Model model) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartService.findActiveShoppingCartByUsername(this.authService.getCurrentUserId());
            model.addAttribute("shoppingCart", shoppingCart);
        } catch (RuntimeException ex) {
            return "redirect:/products?error=" + ex.getLocalizedMessage();
        }
        return "shopping_cart";
    }

    @PostMapping("/{productId}/remove-product")
    public String removeProductToShoppingCart(@PathVariable Long productId) {
      try {
          ShoppingCart shoppingCart = this.shoppingCartService.removeProductFromShoppingCart(this.authService.getCurrentUserId(), productId);

      } catch (RuntimeException ex) {
          return "redirect:/products?error=" + ex.getLocalizedMessage();
      }
        return "redirect:/shopping_cart";
    }

    @PostMapping("/{productId}/order")
    public String orderProduct(@PathVariable Long productId) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartService.removeProductFromShoppingCart(this.authService.getCurrentUserId(), productId);
        } catch (RuntimeException ex) {
            return "redirect:/products?error=" + ex.getLocalizedMessage();
        }
        return "redirect:/shopping_cart";
    }
    

}
