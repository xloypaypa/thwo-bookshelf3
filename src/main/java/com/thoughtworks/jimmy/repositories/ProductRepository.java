package com.thoughtworks.jimmy.repositories;

import com.thoughtworks.jimmy.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer>{
}
