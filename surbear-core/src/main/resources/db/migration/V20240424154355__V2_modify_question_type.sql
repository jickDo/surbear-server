ALTER TABLE survey_questions
    MODIFY COLUMN question_type ENUM ('SINGLE_CHOICE', 'MULTIPLE_CHOICE', 'SHORT_ANSWER', 'SUBJECTIVE', 'SLIDER') NOT NULL;
