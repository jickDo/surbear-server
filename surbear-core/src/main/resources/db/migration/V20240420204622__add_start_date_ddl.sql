ALTER TABLE surveys
    ADD COLUMN start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL COMMENT '설문 시작 시간';