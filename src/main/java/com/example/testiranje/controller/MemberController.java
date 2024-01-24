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


}
