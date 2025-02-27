CREATE DATABASE IF NOT EXISTS room_address;
USE room_address;

CREATE TABLE room_address (
                         id BIGINT PRIMARY KEY,  -- Store ID와 동일하게 설정 (Auto Increment 없음)
                         address_name VARCHAR(255) NOT NULL,
                         road_address_name VARCHAR(255),
                         city VARCHAR(100),
                         district VARCHAR(100),
                         town VARCHAR(100),
                         zip_code VARCHAR(20),
                         latitude DECIMAL(10, 7),  -- 위도
                         longitude DECIMAL(10, 7), -- 경도
                         create_at DATETIME,
                         modified_at DATETIME

);
