package com.example.testiranje.controller.domane;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "head_history")
public class HistoryOfHeads implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private LocalDate startOfPosition;

    @Column(name = "end_date")
    private LocalDate endtOfPosition;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public HistoryOfHeads() {
    }

    public HistoryOfHeads(Long id, LocalDate startOfPosition, LocalDate endtOfPosition, Member member, Role role, Department department) {
        this.id = id;
        this.startOfPosition = startOfPosition;
        this.endtOfPosition = endtOfPosition;
        this.member = member;
        this.role = role;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartOfPosition() {
        return startOfPosition;
    }

    public void setStartOfPosition(LocalDate startOfPosition) {
        this.startOfPosition = startOfPosition;
    }

    public LocalDate getEndtOfPosition() {
        return endtOfPosition;
    }

    public void setEndtOfPosition(LocalDate endtOfPosition) {
        this.endtOfPosition = endtOfPosition;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
