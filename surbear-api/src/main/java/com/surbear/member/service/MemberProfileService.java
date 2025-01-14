package com.surbear.member.service;

import com.surbear.survey.answer.repository.SurveyAnswerRepository;
import com.surbear.survey.dto.survey.history.MySurveyHistoryResponse;
import com.surbear.survey.dto.survey.history.ParticipatedSurvey;
import com.surbear.survey.question.entity.SurveyEntity;
import com.surbear.survey.question.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberProfileService {

    private final SurveyRepository surveyRepository;
    private final SurveyAnswerRepository surveyAnswerRepository;

    public List<MySurveyHistoryResponse> getSurveyListByMemberId(Long surveyAuthorId) {
        List<SurveyEntity> surveyList = surveyRepository.findAllBySurveyAuthorId(surveyAuthorId);

        return surveyList.stream()
                .map(entity -> MySurveyHistoryResponse.builder()
                        .id(entity.getId())
                        .title(entity.getTitle())
                        .ongoingType(entity.getOngoingType())
                        .startDate(entity.getStartDate())
                        .deleted(entity.isDeleted())
                        .build())
                .collect(Collectors.toList());
    }

    public Integer getSurveyCount(Long memberId) {
        return surveyRepository.countAllBySurveyAuthorId(memberId);
    }

    public Integer getParticipationSurveyCount(Long memberId) {
        return surveyAnswerRepository.countAllByMemberId(memberId);
    }
}
