package com.surbear.survey.question.repository;

import com.surbear.survey.question.entity.SurveyQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestionEntity,Long> {
    List<SurveyQuestionEntity> findAllBySurveyId(Long surveyId);
    SurveyQuestionEntity findBySurveyId(Long surveyQuestionId);
    SurveyQuestionEntity findFirstBySurveyIdAndDeletedIsFalse(Long surveyId);

}