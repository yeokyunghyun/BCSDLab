package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class MemberRepository {

    private long id = 1;
    private HashMap<Long, Member> members = new HashMap<>();
    public void save(Member member) {
        members.put(id++, member);
    }

    public Member getMemberById(long id) {
        return members.get(id);
    }
}
