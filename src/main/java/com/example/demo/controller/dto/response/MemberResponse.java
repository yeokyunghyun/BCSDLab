package com.example.demo.controller.dto.response;

import com.example.demo.domain.Member;

public record MemberResponse (
        String name,
        String email,
        String password
) {
    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getName(),
                member.getEmail(),
                member.getPassword()
        );
    }
}
