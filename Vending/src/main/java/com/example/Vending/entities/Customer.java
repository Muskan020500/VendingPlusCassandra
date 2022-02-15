package com.example.Vending.entities;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@Table
@NoArgsConstructor

public class Customer implements Serializable {
    @PrimaryKey
    private @NotNull int customer_id;
    private @NotNull String first_name;
    private  @NotNull String last_name;
    private @NotNull String product_name;
}
