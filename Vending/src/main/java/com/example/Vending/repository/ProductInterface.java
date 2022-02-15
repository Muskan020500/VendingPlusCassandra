package com.example.Vending.repository;

import com.example.Vending.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductInterface extends CrudRepository<Product,Integer> {

}
