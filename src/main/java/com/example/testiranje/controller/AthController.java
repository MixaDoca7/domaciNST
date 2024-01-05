package com.example.testiranje.controller;

import com.example.testiranje.controller.domane.AcademicTitleHistory;
import com.example.testiranje.controller.dto.MemberDto;
import com.example.testiranje.controller.service.AthService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("AcademicTitleHistoryController")
public class AthController {

    private final AthService athService;

    public AthController(AthService athService) {
        this.athService = athService;
    }

    @GetMapping("{id}/{title}")
    public void chageAcademicTitle(@PathVariable("id") Long id, @PathVariable("title") String title){
        athService.changeAcademicTitle(id,title);
    }
    @GetMapping("/getAll")
    public List<AcademicTitleHistory> getAll(Long id) throws Exception {
        return athService.getAll(id);
    }

}
