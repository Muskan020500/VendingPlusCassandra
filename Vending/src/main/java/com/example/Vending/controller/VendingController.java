package com.example.Vending.controller;

import com.example.Vending.entities.Product;
import com.example.Vending.repository.VendingInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class VendingController {
    @Autowired
    VendingInterface vendingInterface;
    List<Product> productList = new ArrayList<>();
    @GetMapping("/products")
    public List<Product> getProduct()
    {
        Iterable<Product> result = vendingInterface.findAll();
        result.forEach(productList::add);
        return productList;
    }
    //If i have to find by productName
    @GetMapping("/price/{productId}")
    public int getPrice(@PathVariable String productId){

        Optional<Product>  product = vendingInterface.findById(Integer.parseInt(productId));
        return product.get().getDrinkPrice();

    }
   /* @GetMapping("/price")
    public int getPriceByName(@RequestBody String productName){

        Optional<Product>  product = vendingInterface.findById(Integer.parseInt(productId));
        return product.get().getDrinkPrice();

    }*/

}
