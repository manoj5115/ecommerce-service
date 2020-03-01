package com.bleedev.momentum.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "ecom_product")
public class Product {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
 
    @NotNull(message = "Product name is mandatory.")
    @Basic(optional = false)
    private String name;
 
    @Column(name = "pointscost")
    private Integer pointsCost;
    
}