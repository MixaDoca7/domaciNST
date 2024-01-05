package com.example.testiranje.controller.domane;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tbl_member")
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstName")
    private String firstame;
    @Column(name = "lastName")
    private String lastname;
    @ManyToOne
    @JoinColumn(name = "academic_title_id")
    private Academic_title academic_title;

    @ManyToOne
    @JoinColumn(name = "education_title_id")
    private Education_title education_title;
    @ManyToOne
    @JoinColumn(name = "scientific_field_id")
    private Scientific_field scientific_field;


    public Member() {
    }

    public Member(Long id, String firstame, String lastname, Academic_title academic_title, Education_title education_title, Scientific_field scientific_field) {
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
