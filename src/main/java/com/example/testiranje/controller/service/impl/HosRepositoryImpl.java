package com.example.testiranje.controller.service.impl;

import com.example.testiranje.controller.domane.*;
import com.example.testiranje.controller.repository.RepositoryDepartment;
import com.example.testiranje.controller.repository.RepositoryHistoryOfSecretary;
import com.example.testiranje.controller.repository.RepositoryMember;
import com.example.testiranje.controller.repository.RepositoryRole;
import com.example.testiranje.controller.service.HosService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HosRepositoryImpl implements HosService {

    private final RepositoryMember repositoryMember;
    private final RepositoryHistoryOfSecretary repositoryHistoryOfSecretary;
    private final RepositoryRole repositoryRole;

    private final RepositoryDepartment repositoryDepartment;

    public HosRepositoryImpl(RepositoryMember repositoryMember, RepositoryHistoryOfSecretary repositoryHistoryOfSecretary, RepositoryRole repositoryRole, RepositoryDepartment repositoryDepartment) {
        this.repositoryMember = repositoryMember;
        this.repositoryHistoryOfSecretary = repositoryHistoryOfSecretary;
        this.repositoryRole = repositoryRole;
        this.repositoryDepartment = repositoryDepartment;
    }

    @Override
    public void save(Long id, LocalDate start, LocalDate end) throws Exception {
        Optional<Member> member = repositoryMember.findById(id);
        if(member.isPresent()){
            Role role = repositoryRole.findByName("Secretary");
            HistoryOfSecretary hos = new HistoryOfSecretary(-1l,start,end,member.get(),role,member.get().getDepartment());
            if(start.isBefore(end)){
                Optional<HistoryOfSecretary> lastInput = repositoryHistoryOfSecretary.findTopByDepartmentOrderByEndtOfPositionDesc(member.get().getDepartment());
                if(lastInput.isPresent()){
                    if(lastInput.get().getEndtOfPosition().isBefore(start)) {
                        repositoryHistoryOfSecretary.save(hos);
                        return;
                    }else{
                        HistoryOfSecretary firstInput = repositoryHistoryOfSecretary.findTopByDepartmentOrderByEndtOfPosition(member.get().getDepartment());
                        if(firstInput.getStartOfPosition().isAfter(end)){
                            repositoryHistoryOfSecretary.save(hos);
                            return;
                        }else{
                            throw new Exception("In this time someone is already Secretary of Department");
                        }
                    }
                }
                repositoryHistoryOfSecretary.save(hos);
                return;
            }
            throw new Exception("Invalid dates");
        }
        throw new Exception("Member does not exist");
    }

    @Override
    public Optional<List<HistoryOfSecretary>> getAllMember(Long id) throws Exception {
        Optional<Member> member = repositoryMember.findById(id);
        if(member.isPresent()){
            Optional<List<HistoryOfSecretary>> list = repositoryHistoryOfSecretary.findByMember(member.get());
            if(list.isPresent()) return list;
        }
        throw new Exception("This member has never been secretary of department");
    }

    @Override
    public Optional<List<HistoryOfSecretary>> getAllDepartment(Long id) throws Exception {
        Optional<Department> dept = repositoryDepartment.findById(id);
        if(dept.isPresent()){
            Optional<List<HistoryOfSecretary>> list = repositoryHistoryOfSecretary.findByDepartment(dept.get());
            if(list.isPresent()) return list;
        }
        throw new Exception("This member has never been secretary of department");
    }

    @Override
    public void update(Long id,HistoryOfHeads hoh) throws Exception{
        Optional<HistoryOfSecretary> s = repositoryHistoryOfSecretary.findById(id);
        if(s.isPresent()){
            if(hoh.getStartOfPosition().isBefore(hoh.getEndtOfPosition())){
                HistoryOfSecretary update = s.get();
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
                repositoryHistoryOfSecretary.save(update);
            }
        }else{
            throw new Exception("Selected History does not exist,");
        }
    }
    @Override
    public String delete(Long id){
        Optional<HistoryOfSecretary> hos = repositoryHistoryOfSecretary.findById(id);
        if(hos.isPresent()){
            return "You are successfully deleted history";
        }
        return "History with that id does not exist";
    }
}
