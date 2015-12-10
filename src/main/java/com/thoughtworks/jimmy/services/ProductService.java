package com.thoughtworks.jimmy.services;


import com.thoughtworks.jimmy.domain.Product;

public interface ProductService {
    Iterable<Product> listAllProducts();

    Product getProductById(Integer id);

    Product saveProduct(Product product);
}
