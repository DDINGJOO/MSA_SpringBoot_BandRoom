CREATE TABLE band_room (
                           id VARCHAR(255) PRIMARY KEY,
                           name VARCHAR(255),
                           short_description VARCHAR(500),
                           detailed_description TEXT,
                           phone VARCHAR(100),
                           parking_available BOOLEAN,
                           display_address VARCHAR(255),
                           notes TEXT,
                           keyword VARCHAR(255),
                           thumbnail_url VARCHAR(500),
                           road_address VARCHAR(255),
                           detail_address VARCHAR(255),
                           latitude DOUBLE,
                           longitude DOUBLE
);

-- 인덱스 (조회 성능 개선용)
CREATE INDEX idx_band_room_name ON band_room(name);
CREATE INDEX idx_band_room_lat_lng ON band_room(latitude, longitude);


CREATE TABLE studio (
                        id VARCHAR(255) PRIMARY KEY,
                        name VARCHAR(255),
                        description TEXT,
                        default_price INT,
                        band_room_id VARCHAR(255),
                        CONSTRAINT fk_studio_bandroom FOREIGN KEY (band_room_id) REFERENCES band_room(id)
);

-- 이미지 URL은 별도 테이블로 분리한 경우
CREATE TABLE studio_image_urls (
                                   studio_id VARCHAR(255),
                                   image_url VARCHAR(500),
                                   FOREIGN KEY (studio_id) REFERENCES studio(id)
);

CREATE TABLE studio_pricing_policy (
                                       id VARCHAR(255) PRIMARY KEY,
                                       studio_id VARCHAR(255),
                                       day_of_week INT, -- 1=월 ~ 7=일
                                       start_time TIME,
                                       end_time TIME,
                                       price INT,
                                       is_holiday BOOLEAN,
                                       CONSTRAINT fk_policy_studio FOREIGN KEY (studio_id) REFERENCES studio(id)
);

-- 인덱스 (요금 조회 성능 개선용)
CREATE INDEX idx_policy_day_hour ON studio_pricing_policy(day_of_week, start_time, end_time);
CREATE INDEX idx_policy_holiday ON studio_pricing_policy(is_holiday);

CREATE TABLE band_room_schedule_rule (
                                         id VARCHAR(255) PRIMARY KEY,
                                         band_room_id VARCHAR(255),
                                         day_of_week INT,
                                         start_time TIME,
                                         end_time TIME,
                                         recurrence_pattern VARCHAR(20),
                                         is_closed BOOLEAN,
                                         is_odd_week BOOLEAN,
                                         CONSTRAINT fk_schedule_bandroom FOREIGN KEY (band_room_id) REFERENCES band_room(id)
);

-- 인덱스
CREATE INDEX idx_schedule_day_pattern ON band_room_schedule_rule(day_of_week, recurrence_pattern, is_odd_week);

CREATE TABLE special_closed_day (
                                    id VARCHAR(255) PRIMARY KEY,
                                    band_room_id VARCHAR(255),
                                    date DATE,
                                    reason VARCHAR(255),
                                    CONSTRAINT fk_closed_bandroom FOREIGN KEY (band_room_id) REFERENCES band_room(id)
);

-- 인덱스
CREATE INDEX idx_closed_date ON special_closed_day(date);
