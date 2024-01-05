package com.example.testiranje.controller.converter.impl;

import com.example.testiranje.controller.converter.DtoEntityConverter;
import com.example.testiranje.controller.domane.Member;
import com.example.testiranje.controller.dto.MemberDto;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter implements DtoEntityConverter<MemberDto, Member> {
    @Override
    public MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getFirstame(), member.getLastname(),
                member.getAcademic_title(), member.getEducation_title(), member.getScientific_field());
    }

    @Override
    public Member toEntity(MemberDto memberDto) {
        return new Member(memberDto.getId(), memberDto.getFirstame(), memberDto.getLastname(),
                memberDto.getAcademic_title(), memberDto.getEducation_title(), memberDto.getScientific_field());
    }
}
