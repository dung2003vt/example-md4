package com.example.examplemd4.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double area;
    private Long population;
    private Double gdp;
    private String introduce;

    @ManyToOne
    private Country country;
}
