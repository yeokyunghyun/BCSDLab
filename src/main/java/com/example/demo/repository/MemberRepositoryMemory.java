package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemberRepositoryMemory implements MemberRepository {

    private static final HashMap<Long, Member> members = new HashMap<>();
    private static final AtomicLong autoIncrement = new AtomicLong(1);

    static {
        members.put(autoIncrement.getAndIncrement(), new Member("여경현", "durudgus0806@naver.com", "du3886131"));
    }
    @Override
    public Member findById(Long id) {
        return members.getOrDefault(id, null);
    }


}
