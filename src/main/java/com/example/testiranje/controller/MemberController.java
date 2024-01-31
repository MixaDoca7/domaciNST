package com.example.testiranje.controller;

import com.example.testiranje.controller.dto.MemberDto;
import com.example.testiranje.controller.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberDto> save(@Valid @RequestBody MemberDto memberDto){
        return new ResponseEntity<>(memberService.save(memberDto), HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
            memberService.delete(id);
            return new ResponseEntity<>("Member is removed",HttpStatus.GONE);
    }
    @GetMapping("{id}")
    public ResponseEntity<MemberDto> getMember(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(memberService.getById(id), HttpStatus.FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<MemberDto> update(@PathVariable Long id, @RequestBody MemberDto memberDto) throws Exception{
        return new ResponseEntity<>(memberService.update(id,memberDto),HttpStatus.OK);
    }


}
