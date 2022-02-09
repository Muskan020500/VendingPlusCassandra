package com.example.Vending.entities;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@AllArgsConstructor
@Getter@Setter
@Table
public class Coin {
    @PrimaryKey
    private @NotNull int id;
    private @NotNull String coinName;
    private  @NotNull int coinValue;
}
