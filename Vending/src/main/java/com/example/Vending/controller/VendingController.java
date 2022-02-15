package com.example.Vending.controller;

import com.example.Vending.entities.Customer;
import com.example.Vending.entities.Product;
import com.example.Vending.repository.CustomerInterface;
import com.example.Vending.repository.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class VendingController {
    @Autowired
    ProductInterface productInterface;
    @Autowired
    CustomerInterface customerInterface;
    List<Product> productList = new ArrayList<>();
    List<Customer> customerList = new ArrayList<>();
    Product selectedProduct;
    int amount ;
    //Map<String,Integer> refundAmount = new TreeMap<>();
    @GetMapping("/products")
    public List<Product> getProduct()
    {
        Iterable<Product> result = productInterface.findAll();
        result.forEach(productList::add);
        return productList;
    }
    @GetMapping("/customers")
    public List<Customer> getCustomer()
    {
        Iterable<Customer> result = customerInterface.findAll();
        result.forEach(customerList::add);
        return customerList;
    }
    //If i have to find by productName
    @GetMapping("/price/{productId}")
    public int getPrice(@PathVariable String productId){

        Optional<Product>  product = productInterface.findById(Integer.parseInt(productId));
        selectedProduct = product.get();
        amount = selectedProduct.getDrinkPrice();
        //String customerInfo = addCustomer();
        //System.out.println(customerInfo);
        return product.get().getDrinkPrice();


    }
    /* @GetMapping("/price")
     public int getPriceByName(@RequestBody String productName){

         Optional<Product>  product = vendingInterface.findById(Integer.parseInt(productId));
         return product.get().getDrinkPrice();

     }*/
    @GetMapping("/coins/{coinValue}")
    public String putCoins(@PathVariable String coinValue){
        int value = Integer.parseInt(coinValue);
        int remainingAmount = selectedProduct.getDrinkPrice();
        if(remainingAmount == value){
            return  selectedProduct.getDrinkName();
        }
        remainingAmount = remainingAmount-value;
        String s = remainingAmount+" cents more needed";
        return s;
    }

    @GetMapping("/coins")
    public String putCoins1(@RequestBody int value){
        //int value = Integer.parseInt(coinValue);
        Boolean b ;
        switch (value){
            case 1:b=true;break;
            case 5:b=true;break;
            case 10:b=true;break;
            case 25:b=true;break;
            default:b=false;break;
        }
        if(b){
            if(amount == value){
                return  selectedProduct.getDrinkName();
            }
            if(value >amount){
                int extraAmount = value-amount;
                refund(extraAmount);
            }
            amount = amount-value;
            String s = amount+" cents more needed";
            return s;
        }
        return ("Accepts coins of 1,5,10,25 Cents i.e. penny, nickel, dime, and quarter.");
    }

    public String refund(int refundAmount){
        return (refundAmount+" Cents Refund Initiated...");
    }
    @GetMapping("/refund")
    public String cancelRequest(){
        int refundedAmount = selectedProduct.getDrinkPrice()-amount;
        amount = selectedProduct.getDrinkPrice();
        return refund(refundedAmount);
    }
    @GetMapping("/reset")
    public String resetMachine(){
        selectedProduct = null;
        amount = 0;
        return ("Machine reseted ..");
    }
    @GetMapping("/show")
    public String showSelectedProduct(){
        if(selectedProduct == null){return ("No product Selected");}
        return selectedProduct.getDrinkName();
    }

    /*public String addCustomer()
    {
        int id = randomOrderId();
        if(selectedProduct == null){return ("Select product to proceed ...");}
        Customer customer = new Customer(id,, selectedProduct.getDrinkPrice());
        System.out.println(customer.getOrderId());
        customerInterface.save(customer);
        return ("OrderId generated insert coins to place the order");
    }
    public int randomOrderId(){
        Random random = new Random();
        int id = random.nextInt(1000000);
        //boolean checkIdExists = customerInterface.existsById(id);
       // if(checkIdExists){randomOrderId(); }
        return id;
    }*/

}

