package com.example.Vending.entities;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@AllArgsConstructor
@Getter@Setter
@Table
@NoArgsConstructor
public class Product implements Serializable {
    @PrimaryKey
    private @NotNull int id;
    private @NotNull String drinkName;
    private @NotNull int drinkPrice;
}
