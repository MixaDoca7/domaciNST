package com.example.testiranje.controller.repository;

import com.example.testiranje.controller.domane.AcademicTitleHistory;
import com.example.testiranje.controller.domane.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepositoryAth extends JpaRepository<AcademicTitleHistory, Long> {

    Optional<AcademicTitleHistory> findByMemberAndEndDateIsNull(Member member);
    List<AcademicTitleHistory> findByMember(Member member);
}
