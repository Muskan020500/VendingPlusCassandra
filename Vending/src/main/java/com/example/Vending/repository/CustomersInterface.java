package com.example.Vending.repository;


import com.example.Vending.entities.Customers;
import org.springframework.data.repository.CrudRepository;

public interface CustomersInterface extends CrudRepository<Customers,Integer> {

}
