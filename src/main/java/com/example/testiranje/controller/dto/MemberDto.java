package com.example.testiranje.controller.dto;

import com.example.testiranje.controller.domane.Academic_title;
import com.example.testiranje.controller.domane.Education_title;
import com.example.testiranje.controller.domane.Scientific_field;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

public class MemberDto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Obavezno polje")
    @Column(name = "firstName")
    private String firstame;
    @NotEmpty(message = "Obavezno polje")
    @Column(name = "lastName")
    private String lastname;

    @Column(name = "academic_title")
    private Academic_title academic_title;
    @Column(name = "education_title")
    private Education_title education_title;
    @Column(name = "scientific_field")
    private Scientific_field scientific_field;

    public MemberDto() {
    }

    public MemberDto(Long id, String firstame, String lastname, Academic_title academic_title, Education_title education_title, Scientific_field scientific_field) {
        this.id = id;
        this.firstame = firstame;
        this.lastname = lastname;
        this.academic_title = academic_title;
        this.education_title = education_title;
        this.scientific_field = scientific_field;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstame() {
        return firstame;
    }

    public void setFirstame(String firstame) {
        this.firstame = firstame;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Academic_title getAcademic_title() {
        return academic_title;
    }

    public void setAcademic_title(Academic_title academic_title) {
        this.academic_title = academic_title;
    }

    public Education_title getEducation_title() {
        return education_title;
    }

    public void setEducation_title(Education_title education_title) {
        this.education_title = education_title;
    }

    public Scientific_field getScientific_field() {
        return scientific_field;
    }

    public void setScientific_field(Scientific_field scientific_field) {
        this.scientific_field = scientific_field;
    }
}
