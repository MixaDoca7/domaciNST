package com.example.testiranje.controller.repository;

import com.example.testiranje.controller.domane.Department;
import com.example.testiranje.controller.domane.HistoryOfHeads;
import com.example.testiranje.controller.domane.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepositoryHistoryOfHeads extends JpaRepository<HistoryOfHeads, Long> {

    Optional<HistoryOfHeads> findTopByDepartmentOrderById(Department department);
    Optional<List<HistoryOfHeads>> findByMember(Member member);
}
