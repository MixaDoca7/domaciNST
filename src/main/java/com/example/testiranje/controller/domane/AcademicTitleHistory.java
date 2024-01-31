package com.example.testiranje.controller.domane;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "academic_title_history")
public class AcademicTitleHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id)")
    private Member member;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "academic_title_id")
    private Academic_title academic_title;
    @ManyToOne
    @JoinColumn(name = "scientific_field_id")
    private Scientific_field scientific_field;

    public AcademicTitleHistory() {
    }

    public AcademicTitleHistory(Long id, Member member, LocalDate startDate, LocalDate endDate, Academic_title academic_title, Scientific_field scientific_field) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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
