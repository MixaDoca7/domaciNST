package com.example.testiranje.controller.domane;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_academicTitleHistory")
public class AcademicTitleHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id)")
    private Member member;
    @Column(name = "startDate")
    private Date startDate;
    @Column(name = "endDate")
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "academic_title_id")
    private Academic_title academic_title;
    @ManyToOne
    @JoinColumn(name = "scientific_field_id")
    private Scientific_field scientific_field;

    public AcademicTitleHistory() {
    }

    public AcademicTitleHistory(Long id, Member member, Date startDate, Date endDate, Academic_title academic_title, Scientific_field scientific_field) {
        this.id = id;
        this.member = member;
        this.startDate = startDate;
        this.endDate = endDate;
        this.academic_title = academic_title;
        this.scientific_field = scientific_field;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Academic_title getAcademic_title() {
        return academic_title;
    }

    public void setAcademic_title(Academic_title academic_title) {
        this.academic_title = academic_title;
    }

    public Scientific_field getScientific_field() {
        return scientific_field;
    }

    public void setScientific_field(Scientific_field scientific_field) {
        this.scientific_field = scientific_field;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
