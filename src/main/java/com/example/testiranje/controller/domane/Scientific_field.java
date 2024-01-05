package com.example.testiranje.controller.domane;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tbl_scientificField")
public class Scientific_field implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    public Scientific_field() {
    }

    public Scientific_field(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }
}
