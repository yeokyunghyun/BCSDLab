package com.example.demo.repository;

import com.example.demo.domain.Member;

import java.util.Collection;
import java.util.List;

public interface MemberRepository {

    public Member findById(Long id);

    Member insert(Member member);

    List<Member> findAll();

    Member update(Member member);

    void deleteById(Long id);
}
