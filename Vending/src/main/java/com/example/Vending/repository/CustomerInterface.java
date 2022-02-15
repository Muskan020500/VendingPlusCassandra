package com.example.Vending.repository;

import com.example.Vending.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerInterface extends CrudRepository<Customer,Integer> {
}
