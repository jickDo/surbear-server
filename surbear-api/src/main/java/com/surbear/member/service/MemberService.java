package com.surbear.member.service;

import com.surbear.JwtTokenProvider;
import com.surbear.exception.ProcessErrorCodeException;
import com.surbear.exception.errorcode.BasicErrorCode;
import com.surbear.member.controller.dto.CertificationRequest;
import com.surbear.member.controller.dto.LoginRequest;
import com.surbear.member.controller.dto.LoginResponse;
import com.surbear.member.entity.MemberEntity;
import com.surbear.member.mapper.MemberMapper;
import com.surbear.member.model.Member;
import com.surbear.member.repository.MemberRepository;
import com.surbear.survey.question.model.Survey;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private final MemberRepository repository;
    private final MemberMapper mapper;

    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public Long signUp(Member member) {
        boolean checkEmailDuplicate = isEmailDuplicate(member.email());

        if (checkEmailDuplicate) {
            throw new ProcessErrorCodeException(BasicErrorCode.DUPLICATED_ID);
        }
        return create(member);
    }

    public LoginResponse login(LoginRequest req) {
        Member newEntity = checkUserIdExists(req.userId());

        checkPasswordExists(req.password(), newEntity.password());
        return LoginResponse.builder()
                .accessToken(jwtTokenProvider.createToken(newEntity.id().toString()))
                .build();
    }

    public String findUserIdByEmail(String email) {
        Member newMember = repository.findByEmail(email);
        return newMember.userId();
    }

    public


    public void checkEmailExists(String email) {
        Member member = repository.findByEmail(email);
        if (member == null) {
            throw new ProcessErrorCodeException(BasicErrorCode.USER_NOT_FOUND);
        }
    }

    private Member checkUserIdExists(String userId) {
        Member member = repository.findByUserId(userId);
        if (member == null) {
            throw new ProcessErrorCodeException(BasicErrorCode.USER_NOT_FOUND);
        }
        return member;
    }

    private void checkPasswordExists(String passwordToVerify, String referencePassword) {
        if (!passwordToVerify.equals(referencePassword)) {
            throw new ProcessErrorCodeException(BasicErrorCode.PASSWORD_MISMATCH);
        }
    }

    private boolean isEmailDuplicate(String email) {
        return repository.countByEmail(email) > 0;
    }


    private Long create(Member member) {
        MemberEntity memberEntity = mapper.toEntity(member);

        MemberEntity savedEntity = repository.save(memberEntity);
        return savedEntity.getId();
    }
}
