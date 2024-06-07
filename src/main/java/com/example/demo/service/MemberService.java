package com.example.demo.service;

import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository; // 여러 repository가 사용될 수도 있으니 repository보다는 memberRepository

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

}
