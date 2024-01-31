package com.example.testiranje.controller.service.impl;

import com.example.testiranje.controller.domane.AcademicTitleHistory;
import com.example.testiranje.controller.domane.Academic_title;
import com.example.testiranje.controller.domane.Member;
import com.example.testiranje.controller.repository.RepositoryAcademicTitle;
import com.example.testiranje.controller.repository.RepositoryAth;
import com.example.testiranje.controller.repository.RepositoryMember;
import com.example.testiranje.controller.repository.RepositoryScientificField;
import com.example.testiranje.controller.service.AthService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AthRepositoryImpl implements AthService {

    private final RepositoryMember repositoryMember;
    private final RepositoryAcademicTitle repositoryAcademicTitle;
    private final RepositoryAth repositoryAth;
    private final RepositoryScientificField repositoryScientificField;

    public AthRepositoryImpl(RepositoryMember repositoryMember, RepositoryAcademicTitle repositoryAcademicTitle, RepositoryAth repositoryAth, RepositoryScientificField repositoryScientificField) {
        this.repositoryMember = repositoryMember;
        this.repositoryAcademicTitle = repositoryAcademicTitle;
        this.repositoryAth = repositoryAth;
        this.repositoryScientificField = repositoryScientificField;
    }

    @Override
    public void changeAcademicTitle(Long id, String title) {
        Optional<Member> member = repositoryMember.findById(id);
        if(member.isPresent()){
            LocalDate start = LocalDate.now();
            Optional<AcademicTitleHistory> old = repositoryAth.findByMemberAndEndDateIsNull(member.get());
            old.get().setEndDate(start);
            Optional<Academic_title> at = repositoryAcademicTitle.findByName(title);
            member.get().setAcademic_title(at.get());
            AcademicTitleHistory ath = new AcademicTitleHistory(-1l,member.get(),start,null,at.get(),member.get().getScientific_field());
            repositoryAth.save(ath);
        }
    }

    @Override
    public AcademicTitleHistory get(Long id) throws Exception {
        Optional<AcademicTitleHistory> ath= repositoryAth.findById(id);
        if(ath.isPresent()) {
            return ath.get();
        }
        throw new Exception("This history does not exist.");
    }

    @Override
    public List<AcademicTitleHistory> getAll(Long id) throws Exception {
        Optional<Member> member= repositoryMember.findById(id);
        if(member.isPresent()) {
            return repositoryAth.findByMember(member.get());
        }
        throw new Exception("This member does not exist.");
    }

    @Override
    public void save(AcademicTitleHistory academicTitleHistory) throws Exception {
        Optional<Member> member = repositoryMember.findById(academicTitleHistory.getMember().getId());
        if(repositoryMember.findById(academicTitleHistory.getMember().getId()).isPresent()
            && repositoryAcademicTitle.findById(academicTitleHistory.getAcademic_title().getId()).isPresent()
            && repositoryScientificField.findById(academicTitleHistory.getScientific_field().getId()).isPresent()){
            if(academicTitleHistory.getStartDate().isBefore(academicTitleHistory.getEndDate())){
                repositoryAth.save(academicTitleHistory);
            }else{
                throw new Exception("Invalid dates");
            }
        }else{
            throw new Exception("Nema m,at ili sf");
        }
    }
}
