package com.example.testiranje.controller.service.impl;

import com.example.testiranje.controller.converter.impl.MemberConverter;
import com.example.testiranje.controller.domane.Department;
import com.example.testiranje.controller.domane.HistoryOfHeads;
import com.example.testiranje.controller.domane.Member;
import com.example.testiranje.controller.domane.Role;
import com.example.testiranje.controller.repository.RepositoryHistoryOfHeads;
import com.example.testiranje.controller.repository.RepositoryMember;
import com.example.testiranje.controller.repository.RepositoryRole;
import com.example.testiranje.controller.service.HohService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HohRepositoryImpl implements HohService {

    private final RepositoryMember repositoryMember;
    private final RepositoryHistoryOfHeads repositoryHistoryOfHeads;
    private final RepositoryRole repositoryRole;

    public HohRepositoryImpl(RepositoryMember repositoryMember, RepositoryHistoryOfHeads repositoryHistoryOfHeads, RepositoryRole repositoryRole) {
        this.repositoryMember = repositoryMember;
        this.repositoryHistoryOfHeads = repositoryHistoryOfHeads;
        this.repositoryRole = repositoryRole;
    }

    @Override
    public void save(Long id, Department department) throws Exception {
        Optional<Member> member = repositoryMember.findById(id);
        if(member.isPresent()){
            Optional<HistoryOfHeads> historyOfHeads = repositoryHistoryOfHeads.findTopByDepartmentOrderById(department);
            if(historyOfHeads.isPresent()){
                if(historyOfHeads.get().getEndtOfPosition().before(new Date())) {
                    savePom(member.get(), department);
                }else{
                    throw new Exception("Mandat duration is not expired");
                }
                return;
            }
            savePom(member.get(), department);
            return;
        }
        throw new Exception("Member does not exist");
    }

    @Override
    public Optional<List<HistoryOfHeads>> getAll(Long id) throws Exception {
        Optional<Member> member = repositoryMember.findById(id);
        if(member.isPresent()){
            Optional<List<HistoryOfHeads>> list = repositoryHistoryOfHeads.findByMember(member.get());
            return list;
        }
        throw new Exception("This member has never been head of department");
    }

    private Date duration(Date start){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.MINUTE, 5);
        Date end = calendar.getTime();
        return end;
    }

    private void savePom(Member member, Department department){
        Date start = new Date();
        Date end = duration(start);
        Role role = repositoryRole.findByName("Head of department");
        HistoryOfHeads hoh = new HistoryOfHeads(-1l, start, end, member, role,department);
        repositoryHistoryOfHeads.save(hoh);
    }
}
