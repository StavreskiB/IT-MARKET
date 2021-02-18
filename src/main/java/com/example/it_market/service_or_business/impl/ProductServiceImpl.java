package com.example.it_market.service_or_business.impl;

import com.example.it_market.model.Product;
import com.example.it_market.model.exception.ProductNotFoundException;
import com.example.it_market.persistence_or_repository.ProductRepository;
import com.example.it_market.service_or_business.AuthService;
import com.example.it_market.service_or_business.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final AuthService authService;

    public ProductServiceImpl(ProductRepository productRepository, AuthService authService) {
        this.productRepository = productRepository;
        this.authService = authService;
    }


    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }


    @Override
    public List<Product> findAllSortedByPrice(boolean asc) {
        if (asc) {
            return this.productRepository.findAllByOrderByPriceAsc();
        }
        return this.productRepository.findAllByOrderByPriceDesc();
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product saveProduct(String name, Float price, Float disc_price, String description, String published) {

        Product product = new Product(null, name, price, disc_price, description, published);
        return this.productRepository.save(product);
    }

    @Override
    public Product saveProduct(Product product, MultipartFile image) throws IOException {
        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
//            product.setImage(image);
            product.setImageBase64(base64Image);
        }
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product, MultipartFile image) throws IOException {
        Product p = this.findById(id);
        p.setPrice(product.getPrice());
        p.setDisc_price(product.getDisc_price());
        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            p.setImageBase64(base64Image);
        }
        return this.productRepository.save(p);
    }

   /* @Override
    @Transactional
    public void deleteById(Long id) {
        Product product = this.findById(id);
        if (product.getShoppingCarts().size() > 0) {
            //avoid deleting product that is already in shopping cart!
            throw new ProductIsAlreadyInShoppingCartException(product.getName());
        }

        this.productRepository.deleteById(id);
    }*/



    @Override
    public void deleteById(Long id)
    {
        this.productRepository.deleteById(id);
    }


    @Override
    public Product publishProduct(Long id)
    {

        Product p = this.findById(id);
        p.setPublished("1");

        return this.productRepository.save(p);

    }

    /*public ShoppingCart getActiveShoppingCart(String userId) {
        return this.shoppingCartRepository
                .findByUserUsernameAndStatus(userId, CartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart shoppingCart = new ShoppingCart();
                    User user = this.userService.findById(userId);
                    shoppingCart.setUser(user);
                    return this.shoppingCartRepository.save(shoppingCart);
                });
    }*/
}
