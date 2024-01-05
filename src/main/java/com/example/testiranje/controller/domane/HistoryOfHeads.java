package com.example.testiranje.controller.domane;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_history_of_heads")
public class HistoryOfHeads implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "startOfPosition")
    private Date startOfPosition;

    @Column(name = "endOfPosition")
    private Date endtOfPosition;

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

    public HistoryOfHeads(Long id, Date startOfPosition, Date endtOfPosition, Member member, Role role, Department department) {
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

    public Date getStartOfPosition() {
        return startOfPosition;
    }

    public void setStartOfPosition(Date startOfPosition) {
        this.startOfPosition = startOfPosition;
    }

    public Date getEndtOfPosition() {
        return endtOfPosition;
    }

    public void setEndtOfPosition(Date endtOfPosition) {
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
