package com.example.testiranje.controller.service.impl;

import com.example.testiranje.controller.domane.AcademicTitleHistory;
import com.example.testiranje.controller.domane.Academic_title;
import com.example.testiranje.controller.domane.Member;
import com.example.testiranje.controller.repository.RepositoryAcademicTitle;
import com.example.testiranje.controller.repository.RepositoryAth;
import com.example.testiranje.controller.repository.RepositoryMember;
import com.example.testiranje.controller.service.AthService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AthRepositoryImpl implements AthService {

    private final RepositoryMember repositoryMember;
    private final RepositoryAcademicTitle repositoryAcademicTitle;
    private final RepositoryAth repositoryAth;

    public AthRepositoryImpl( RepositoryMember repositoryMember, RepositoryAcademicTitle repositoryAcademicTitle, RepositoryAth repositoryAth) {
        this.repositoryMember = repositoryMember;
        this.repositoryAcademicTitle = repositoryAcademicTitle;
        this.repositoryAth = repositoryAth;
    }

    @Override
    public void changeAcademicTitle(Long id, String title) {
        Optional<Member> member = repositoryMember.findById(id);
        if(member.isPresent()){
            Instant ldt = (LocalDateTime.now()).atZone(ZoneId.systemDefault()).toInstant();
            Date start = Date.from(ldt);
            Optional<AcademicTitleHistory> old = repositoryAth.findByMemberAndEndDateIsNull(member.get());
            old.get().setEndDate(start);
            Optional<Academic_title> at = repositoryAcademicTitle.findByName(title);
            member.get().setAcademic_title(at.get());
            AcademicTitleHistory ath = new AcademicTitleHistory(-1l,member.get(),start,null,at.get(),member.get().getScientific_field());
            repositoryAth.save(ath);
        }
    }

    @Override
    public List<AcademicTitleHistory> getAll(Long id) throws Exception {
        Optional<Member> member= repositoryMember.findById(id);
        if(member.isPresent()) {
            return repositoryAth.findByMember(member.get());
        }
        throw new Exception("Ovaj member ne postoji.");
    }
}
