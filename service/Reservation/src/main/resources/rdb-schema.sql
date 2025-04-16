CREATE TABLE product (
                         product_id VARCHAR(255) PRIMARY KEY,
                         band_room_id VARCHAR(255) NOT NULL,
                         name VARCHAR(255),
                         price BIGINT,
                         description TEXT,
                         thumbnail_url VARCHAR(500),
                         INDEX idx_band_room_id (band_room_id)
);
