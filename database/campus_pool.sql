DROP DATABASE IF EXISTS campus_pool;
CREATE DATABASE campus_pool;
USE campus_pool;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    phone VARCHAR(30),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE rides (
    id INT PRIMARY KEY AUTO_INCREMENT,
    driver_id INT NOT NULL,
    origin VARCHAR(150) NOT NULL,
    destination VARCHAR(150) NOT NULL,
    ride_date DATE NOT NULL,
    ride_time TIME NOT NULL,
    seats_available INT NOT NULL,
    price DECIMAL(10,2) DEFAULT 0.00,
    notes VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_ride_driver FOREIGN KEY (driver_id) REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE TABLE bookings (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ride_id INT NOT NULL,
    passenger_id INT NOT NULL,
    seats_booked INT NOT NULL DEFAULT 1,
    status ENUM('CONFIRMED','CANCELLED') NOT NULL DEFAULT 'CONFIRMED',
    booked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_booking_ride FOREIGN KEY (ride_id) REFERENCES rides(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_booking_passenger FOREIGN KEY (passenger_id) REFERENCES users(id)
        ON DELETE CASCADE,
    UNIQUE KEY unique_active_booking (ride_id, passenger_id)
);

CREATE INDEX idx_rides_search
ON rides(destination, ride_date);

-- Optional demo account:
-- Password is: password123
INSERT INTO users(full_name, email, password_hash, phone)
VALUES
('Demo Student', 'demo@campus.edu',
 '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
 '555-0100');
