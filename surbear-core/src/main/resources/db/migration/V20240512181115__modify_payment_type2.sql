ALTER TABLE points_histories
    MODIFY COLUMN payment_type ENUM ('SURVEY', 'ADMIN', 'CANCEL','BUY_PRODUCT') NOT NULL;