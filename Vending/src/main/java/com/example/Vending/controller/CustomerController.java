package com.example.Vending.controller;

import com.example.Vending.entities.Customer;
import com.example.Vending.entities.Orders;
import com.example.Vending.entities.Product;
import com.example.Vending.repository.CustomerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.Random;
import java.util.jar.Attributes;

@RestController
public class CustomerController {
    @Autowired
    CustomerInterface customerInterface;


    @GetMapping("/customer")
    public Customer getCustomer(@RequestBody String name){
        int id = randomOrderId();
        RestTemplate restTemplate = new RestTemplate();
        String orderURL = "http://localhost:8082/order/nWDmLum8gm";
        String[] customerName = name.trim().split("\\s+");
        // System.out.println(customerName[0]+","+customerName[1]);
        Orders orders = restTemplate.getForObject(orderURL,Orders.class);
        //RestTemplate restTemplate1 = new RestTemplate();
        //String productURL = "http://localhost:8082/price/1";
        //  Product product = restTemplate.getForObject(productURL,Product.class);

        Customer customer = new Customer(id,customerName[0],customerName[1],orders.getProduct_name());
        //customerInterface.save(customer);
        return customer;

    }
    public int randomOrderId(){
        Random random = new Random();
        int id = random.nextInt(1000000);
        boolean checkIdExists = customerInterface.existsById(id);
        if(checkIdExists){randomOrderId(); }
        return id;
    }
    @GetMapping("/customer/{customerId}")
    public Customer getCustomerInfo(@PathVariable Integer customerId){
        Optional<Customer> customer = customerInterface.findById(customerId);
        return customer.get();
    }
}
