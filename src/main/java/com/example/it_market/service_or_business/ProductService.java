package com.example.it_market.service_or_business;

import com.example.it_market.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> findAll();
    List<Product> findAllSortedByPrice(boolean asc);
    Product findById(Long id);
    //Product saveProduct(String name, Float price, Float disc_price, String description);

    Product saveProduct(String name, Float price, Float disc_price, String description, String published);

    Product saveProduct(Product product, MultipartFile image) throws IOException;
    Product updateProduct(Long id, Product product, MultipartFile image) throws IOException;

    void deleteById(Long id);

    Product publishProduct(Long id);
}
