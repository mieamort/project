package com.example.sbintern3.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "name")
    private String orderName;

    @Column
    private String clientName;

    @Column
    private Integer cost;

    public Order (String name, String clientName, Integer cost){
        this.orderName = name;
        this.clientName = clientName;
        this.cost = cost;
    }
}
