package com.example.demo.repository;

import com.example.demo.domain.Member;

public interface MemberRepository {
    public Member findById(Long id);
}
