package com.example.Vending.repository;

import com.example.Vending.entities.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderInterface extends CrudRepository<Orders,String> {
}
