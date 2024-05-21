package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @RequestMapping(method = RequestMethod.POST)
    public void saveMember(@RequestBody Member member) {
        memberService.save(member);
    }
    @GetMapping
    @ResponseBody
    public String getMembers() {
        return "members";
    }
}
