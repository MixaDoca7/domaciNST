package com.example.testiranje.controller.repository;

import com.example.testiranje.controller.domane.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepositoryMember extends JpaRepository<Member,Long> {


}
