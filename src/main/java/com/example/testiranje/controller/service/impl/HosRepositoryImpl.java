package com.example.testiranje.controller.service.impl;

import com.example.testiranje.controller.domane.*;
import com.example.testiranje.controller.repository.RepositoryHistoryOfService;
import com.example.testiranje.controller.repository.RepositoryMember;
import com.example.testiranje.controller.repository.RepositoryRole;
import com.example.testiranje.controller.service.HosService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HosRepositoryImpl implements HosService {

    private final RepositoryMember repositoryMember;
    private final RepositoryHistoryOfService repositoryHistoryOfService;
    private final RepositoryRole repositoryRole;

    public HosRepositoryImpl(RepositoryMember repositoryMember, RepositoryHistoryOfService repositoryHistoryOfService, RepositoryRole repositoryRole) {
        this.repositoryMember = repositoryMember;
        this.repositoryHistoryOfService = repositoryHistoryOfService;
        this.repositoryRole = repositoryRole;
    }

    @Override
    public void save(Long id) throws Exception {
        Optional<Member> member = repositoryMember.findById(id);
        if(member.isPresent()){
            Optional<HistoryOfSecretary> historyOfSecretary = repositoryHistoryOfService.findTopByDepartmentOrderById(member.get().getDepartment());
            if(historyOfSecretary.isPresent()){
                if(historyOfSecretary.get().getEndtOfPosition().before(new Date())) {
                    savePom(member.get(), member.get().getDepartment());
                }else{
                    throw new Exception("Mandat duration is not expired");
                }
                return;
            }
            savePom(member.get(), member.get().getDepartment());
            return;
        }
        throw new Exception("Member does not exist");
    }

    @Override
    public Optional<List<HistoryOfSecretary>> getAll(Long id) throws Exception {
        Optional<Member> member = repositoryMember.findById(id);
        if(member.isPresent()){
            Optional<List<HistoryOfSecretary>> list = repositoryHistoryOfService.findByMember(member.get());
            if (list.isPresent()) return list;
        }
        throw new Exception("This member has never been head of department");
    }

    private void savePom(Member member, Department department){
        Date start = new Date();
        Date end = duration(start);
        Role role = repositoryRole.findByName("Secretary of department");
        HistoryOfSecretary hos = new HistoryOfSecretary(-1l, start, end, member, role,department);
        repositoryHistoryOfService.save(hos);
    }

    private Date duration(Date start){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.MINUTE, 5);
        return calendar.getTime();
    }
}
