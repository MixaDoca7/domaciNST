package com.example.testiranje.controller.domane;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "subject")
public class Subject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Ime je obavezno polje")
    @Size(min = 2, max = 10, message = "Minimum karatkera je 2, maksimum 10")
    @Column(name = "name")
    private String name;

    @Column(name = "espb")
    private int espb;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Subject() {
    }

    public Subject(Long id, String name, int espb, Department department) {
        this.id = id;
        this.name = name;
        this.espb = espb;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
