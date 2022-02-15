package com.example.Vending.controller;

import com.example.Vending.entities.Customer;
import com.example.Vending.entities.Orders;
import com.example.Vending.entities.Product;
import com.example.Vending.entities.RandomString;
import com.example.Vending.repository.OrderInterface;
import com.example.Vending.repository.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.Random;



@RestController
public class OrderController {
    @Autowired
    OrderInterface orderInterface;
    @Autowired
    ProductInterface productInterface;


    @GetMapping("/order")
    //@ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public Orders getOrder(){
        Random random = new Random();
        RandomString randomString = new RandomString(10,random);
        String id = randomString.nextString();
        RestTemplate restTemplate = new RestTemplate();
        String customerURL = "http://localhost:8082/customer/10401";
        Customer customer = restTemplate.getForObject(customerURL,Customer.class);

        String productURL = "http://localhost:8082/price/1";
        Product product = restTemplate.getForObject(productURL,Product.class);
        Orders orders = new Orders(id,customer.getCustomer_id(),product.getDrinkName(),product.getDrinkPrice());
        orderInterface.save(orders);
        return orders;
    }
    @GetMapping("/order/{orderId}")
    public Orders getSingleOrder(@PathVariable String orderId){
        Optional<Orders> order = orderInterface.findById(orderId);
        return order.get();
    }
    @GetMapping("/order/addmore/count")
    //@ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public Orders setOrder(){
        Random random = new Random();
        RandomString randomString = new RandomString(10,random);
        String id = randomString.nextString();
        RestTemplate restTemplate = new RestTemplate();
        String customerURL = "http://localhost:8082/customer/10401";
        Customer customer = restTemplate.getForObject(customerURL,Customer.class);

        String productURL = "http://localhost:8082/price/1";
        Product product = restTemplate.getForObject(productURL,Product.class);
        String s = addMore();
        String s1 = product.getDrinkName()+","+s ;
        Orders orders = new Orders(id,customer.getCustomer_id(),s1,product.getDrinkPrice());
        orderInterface.save(orders);
        return orders;
    }

    public String addMore(){
        RestTemplate restTemplate = new RestTemplate();
        String productURL = "http://localhost:8082/price/2";
        Product product = restTemplate.getForObject(productURL,Product.class);
        return product.getDrinkName();
    }
}
