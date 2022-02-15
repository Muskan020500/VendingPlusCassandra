package com.example.Vending.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@AllArgsConstructor
@Getter
@Setter
@Table
@NoArgsConstructor
public class Orders {
    @PrimaryKey
    private @NotNull String order_id;
    private @NotNull int customer_id;
    private  @NotNull String product_name;
    private @NotNull int product_price;
}
