package com.example.testiranje.controller.service.impl;

import com.example.testiranje.controller.domane.Department;
import com.example.testiranje.controller.domane.HistoryOfHeads;
import com.example.testiranje.controller.domane.Member;
import com.example.testiranje.controller.domane.Role;
import com.example.testiranje.controller.repository.RepositoryDepartment;
import com.example.testiranje.controller.repository.RepositoryHistoryOfHeads;
import com.example.testiranje.controller.repository.RepositoryMember;
import com.example.testiranje.controller.repository.RepositoryRole;
import com.example.testiranje.controller.service.HohService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HohRepositoryImpl implements HohService {

    private final RepositoryMember repositoryMember;
    private final RepositoryHistoryOfHeads repositoryHistoryOfHeads;
    private final RepositoryRole repositoryRole;
    private final RepositoryDepartment repositoryDepartment;

    public HohRepositoryImpl(RepositoryMember repositoryMember, RepositoryHistoryOfHeads repositoryHistoryOfHeads, RepositoryRole repositoryRole, RepositoryDepartment repositoryDepartment) {
        this.repositoryMember = repositoryMember;
        this.repositoryHistoryOfHeads = repositoryHistoryOfHeads;
        this.repositoryRole = repositoryRole;
        this.repositoryDepartment = repositoryDepartment;
    }

    @Override
    public void save(Long id, LocalDate start, LocalDate end) throws Exception {
        Optional<Member> member = repositoryMember.findById(id);
        if(member.isPresent()){
            Role role = repositoryRole.findByName("Head");
            HistoryOfHeads hoh = new HistoryOfHeads(-1l,start,end,member.get(),role,member.get().getDepartment());
            if(start.isBefore(end)){
                Optional<HistoryOfHeads> lastInput = repositoryHistoryOfHeads.findTopByDepartmentOrderByEndtOfPositionDesc(member.get().getDepartment());
                if(lastInput.isPresent()){
                    if(lastInput.get().getEndtOfPosition().isBefore(start)) {
                        //savePom(member.get(), member.get().getDepartment());
                        repositoryHistoryOfHeads.save(hoh);
                        return;
                    }else{
                        HistoryOfHeads firstInput = repositoryHistoryOfHeads.findTopByDepartmentOrderByEndtOfPosition(member.get().getDepartment());
                        if(firstInput.getStartOfPosition().isAfter(end)){
                            repositoryHistoryOfHeads.save(hoh);
                            return;
                        }else{
                            throw new Exception("In this time someone is already Head of Department");
                        }
                    }
                }
                //savePom(member.get(), member.get().getDepartment());
                repositoryHistoryOfHeads.save(hoh);
                return;
            }
            throw new Exception("Invalid dates");
        }
        throw new Exception("Member does not exist");
    }

    @Override
    public Optional<List<HistoryOfHeads>> getAllMember(Long id) throws Exception {
        Optional<Member> member = repositoryMember.findById(id);
        if(member.isPresent()){
            Optional<List<HistoryOfHeads>> list = repositoryHistoryOfHeads.findByMember(member.get());
            if(list.isPresent()) return list;
        }
        throw new Exception("This member has never been head of department");
    }

    @Override
    public Optional<List<HistoryOfHeads>> getAllDepartment(Long id) throws Exception {
        Optional<Department> dept = repositoryDepartment.findById(id);
        if(dept.isPresent()){
            Optional<List<HistoryOfHeads>> list = repositoryHistoryOfHeads.findByDepartment(dept.get());
            if(list.isPresent()) return list;
        }
        throw new Exception("This member has never been head of department");
    }

    @Override
    public void update(Long id,HistoryOfHeads hoh) throws Exception{
        Optional<HistoryOfHeads> h = repositoryHistoryOfHeads.findById(id);
        if(h.isPresent()){
            if(hoh.getStartOfPosition().isBefore(hoh.getEndtOfPosition())){
            HistoryOfHeads update = h.get();
            update.setId(id);
            update.setStartOfPosition(hoh.getStartOfPosition());
            update.setEndtOfPosition(hoh.getEndtOfPosition());
            Optional<Member> m = repositoryMember.findById(hoh.getMember().getId());
            if(m.isPresent()) {
                update.setMember(m.get());
                update.setDepartment(m.get().getDepartment());
            }else {
                throw new Exception("Member does not exist, please first make new member");
            }
            repositoryHistoryOfHeads.save(update);
            }
        }else{
            throw new Exception("Selected History does not exist,");
        }
    }
    @Override
    public String delete(Long id){
        Optional<HistoryOfHeads> hoh = repositoryHistoryOfHeads.findById(id);
        if(hoh.isPresent()){
            return "You are successfully deleted history";
        }
        return "History with that id does not exist";
    }

}
