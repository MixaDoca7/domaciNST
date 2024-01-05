package com.example.testiranje.controller.service.impl;

import com.example.testiranje.controller.converter.impl.MemberConverter;
import com.example.testiranje.controller.domane.*;
import com.example.testiranje.controller.dto.MemberDto;
import com.example.testiranje.controller.repository.*;
import com.example.testiranje.controller.service.MemberService;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.config.RepositoryConfiguration;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MemberRepositoryImpl implements MemberService {

    private final MemberConverter memberConverter;
    private final RepositoryMember repositoryMember;
    private final RepositoryAcademicTitle repositoryAcademicTitle;
    private final RepositoryEducationTitle repositoryEducationTitle;
    private final RepositoryScientificField repositoryScientificField;
    private final RepositoryAth repositoryAth;

    public MemberRepositoryImpl(MemberConverter memberConverter, RepositoryMember repositoryMember, RepositoryAcademicTitle repositoryAcademicTitle, RepositoryEducationTitle repositoryEducationTitle, RepositoryScientificField repositoryScientificField, RepositoryAth repositoryAth) {
        this.memberConverter = memberConverter;
        this.repositoryMember = repositoryMember;
        this.repositoryAcademicTitle = repositoryAcademicTitle;
        this.repositoryEducationTitle = repositoryEducationTitle;
        this.repositoryScientificField = repositoryScientificField;
        this.repositoryAth = repositoryAth;
    }

    @Override
    @Transactional
    public MemberDto save(MemberDto memberDto) {
        Member member = memberConverter.toEntity(memberDto);
        verificationAT(member);
        verificationET(member);
        verificationSF(member);
        repositoryMember.save(member);
        Instant ldt = (LocalDateTime.now()).atZone(ZoneId.systemDefault()).toInstant();
        Date start = Date.from(ldt);
        AcademicTitleHistory ath = new AcademicTitleHistory(-1l,member,start,null,member.getAcademic_title(),member.getScientific_field());
        repositoryAth.save(ath);
        return memberDto;
    }

    @Override
    public List<MemberDto> getAll() {
        return null;
    }

    @Override
    public void delete(MemberDto memberDto) {

    }

    @Override
    public MemberDto getById(Long id) {
        return null;
    }

    private void verificationAT(Member member){
        if(member.getAcademic_title().getId() == null){
            repositoryAcademicTitle.save(member.getAcademic_title());
        }else{
            Optional<Academic_title> acdt = repositoryAcademicTitle.findById(member.getAcademic_title().getId());
            if(acdt.isEmpty()){
                repositoryAcademicTitle.save(member.getAcademic_title());
            }
        }
    }
    private void verificationET(Member member){
        if(member.getEducation_title().getId() == null){
            repositoryEducationTitle.save(member.getEducation_title());
        }else{
            Optional<Education_title> acet = repositoryEducationTitle.findById(member.getEducation_title().getId());
            if(acet.isEmpty()){
                repositoryEducationTitle.save(member.getEducation_title());
            }
        }
    }
    private void verificationSF(Member member){
        if(member.getScientific_field().getId() == null){
            repositoryScientificField.save(member.getScientific_field());
        }else{
            Optional<Scientific_field> acst = repositoryScientificField.findById(member.getScientific_field().getId());
            if(acst.isEmpty()){
                repositoryScientificField.save(member.getScientific_field());
            }
        }
    }
}
