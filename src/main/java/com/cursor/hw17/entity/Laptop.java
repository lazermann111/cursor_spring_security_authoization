package com.cursor.hw17.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notebook")
@Getter
@Setter
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @org.springframework.data.relational.core.mapping.Embedded.Nullable
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "brand")
    private String brand;

    @Column(name = "processor")
    private String processor;

    @Column(name = "RAM_memory")
    private Integer ram;

    @Column(name = "used")
    private boolean used;

    @Column(name = "case_type")
    private String case_type;

    @Column(name = "price")
    private Integer price;

    @DateTimeFormat
    @Column(name = "data")
    private Date data;

    public Laptop() {
    }

    public Laptop(String model, String brand, String processor, Integer ram, boolean used, String case_type, Integer price, Date data) {
        this.model = model;
        this.brand = brand;
        this.processor = processor;
        this.ram = ram;
        this.used = used;
        this.case_type = case_type;
        this.price = price;
        this.data = data;
    }
}
