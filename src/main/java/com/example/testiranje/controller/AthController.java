package com.example.testiranje.controller;

import com.example.testiranje.controller.domane.AcademicTitleHistory;
import com.example.testiranje.controller.service.AthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("AcademicTitleHistoryController")
public class AthController {

    private final AthService athService;

    public AthController(AthService athService) {
        this.athService = athService;
    }

   /* @PostMapping("{id}/{title}")
    public void chageAcademicTitle(@PathVariable("id") Long id, @PathVariable("title") String title){
        athService.changeAcademicTitle(id,title);
    }*/
    @GetMapping("/getAll")
    public List<AcademicTitleHistory> getAll(Long id) throws Exception {
        return athService.getAll(id);
    }

    //Moguće je samo ako član pre toga nema Academic Title Hist
    /*@PostMapping("New/{id}/{startDate}/{endDate}/{title}")
    public ResponseEntity<String> setAcademicTitle(@PathVariable("id") Long id,
                   @PathVariable("startDate") LocalDate start, @PathVariable("endDate") LocalDate end, @PathVariable("title") String title){
        return new ResponseEntity<>("You have successfully add new academic title history", HttpStatus.OK);
    }*/

    @PostMapping
    public ResponseEntity<String> setAcademicTitle(@RequestBody AcademicTitleHistory ath) throws Exception{
        athService.save(ath);
        return new ResponseEntity<>("You have successfully add new academic title history", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AcademicTitleHistory> get(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(athService.get(id),HttpStatus.FOUND);
    }

}
