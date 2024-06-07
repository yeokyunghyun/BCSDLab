package com.example.demo.controller;

import com.example.demo.controller.dto.request.MemberCreateRequest;
import com.example.demo.controller.dto.request.MemberUpdateRequest;
import com.example.demo.controller.dto.response.MemberResponse;
import com.example.demo.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public List<MemberResponse> getMembers() {
        return memberService.getMembers();
    }

    @PostMapping("/members/{id}")
    public MemberResponse getMember(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @PostMapping("/members")
    public MemberResponse createMember(@RequestBody MemberCreateRequest request) {
        return memberService.createMember(request);
    }

    @PutMapping("/members/{id}")
    public MemberResponse updateMember(@PathVariable Long id, @RequestBody MemberUpdateRequest request) {
        return memberService.updateMember(id, request);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
