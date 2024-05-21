package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public void save(Member member) {
        memberRepository.save(member);
    }

    public Member getMemberById(long id) {
        return memberRepository.getMemberById(id);
    }
}
