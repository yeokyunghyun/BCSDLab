package com.example.demo.service;

import com.example.demo.controller.dto.request.MemberCreateRequest;
import com.example.demo.controller.dto.request.MemberUpdateRequest;
import com.example.demo.controller.dto.response.MemberResponse;
import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository; // 여러 repository가 사용될 수도 있으니 repository보다는 memberRepository

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberResponse createMember(MemberCreateRequest request) {
        Member member = new Member(request.name(), request.email(), request.password());
        Member saved = memberRepository.insert(member);

        return MemberResponse.from(saved);
    }

    public List<MemberResponse> getMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponse::from)
                .toList();
    }

    public MemberResponse getMemberById(Long id) {
        Member saved = memberRepository.findById(id);
        return MemberResponse.from(saved);
    }

    @Transactional
    public MemberResponse updateMember(Long id, MemberUpdateRequest request) {
        Member member = memberRepository.findById(id);
        member.update(request.name(), request.email(), request.password());

        Member updated = memberRepository.update(member);
        return MemberResponse.from(updated);
    }

    @Transactional
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
