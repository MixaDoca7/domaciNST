package com.example.testiranje.controller.repository;

import com.example.testiranje.controller.domane.Department;
import com.example.testiranje.controller.domane.HistoryOfSecretary;
import com.example.testiranje.controller.domane.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepositoryHistoryOfSecretary extends JpaRepository<HistoryOfSecretary,Long> {

    Optional<HistoryOfSecretary> findTopByDepartmentOrderById(Department department);
    Optional<List<HistoryOfSecretary>> findByMember(Member member);

    Optional<HistoryOfSecretary> findTopByDepartmentOrderByEndtOfPositionDesc(Department department);

    HistoryOfSecretary findTopByDepartmentOrderByEndtOfPosition(Department department);

    Optional<List<HistoryOfSecretary>> findByDepartment(Department department);
}
