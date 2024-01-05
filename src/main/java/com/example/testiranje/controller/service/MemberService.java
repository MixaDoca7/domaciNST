package com.example.testiranje.controller.service;

import com.example.testiranje.controller.dto.MemberDto;

import java.util.List;

public interface MemberService {

    MemberDto save(MemberDto memberDto);
    List<MemberDto> getAll();
    void delete(MemberDto memberDto);
    MemberDto getById(Long id);

}
