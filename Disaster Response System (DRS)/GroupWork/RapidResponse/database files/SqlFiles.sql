-- Create database if not exists drs_database;
CREATE DATABASE IF NOT EXISTS drs_database;

USE drs_database;

-- Drop and create users table
DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    gender VARCHAR(10),
    birthday DATE,
    role VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Added created_at column
);

-- Insert initial user data
INSERT INTO users (username, email, password, gender, birthday, role) VALUES 
('visitor', 'visitor@example.com', 'visitor', 'Male', '1990-05-10', 'Visitor'), 
('manager', 'manager@example.com', 'manager', 'Female', '1985-02-15', 'Resource Manager'), 
('responder', 'responder@example.com', 'responder', 'Other', '1980-11-20', 'Emergency Responder');

-- Drop and create resources table
DROP TABLE IF EXISTS resources;
CREATE TABLE resources (
    id INT AUTO_INCREMENT PRIMARY KEY,
    resource_name VARCHAR(100) NOT NULL,
    available_units INT NOT NULL,
    total_units INT NOT NULL,
    date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Added created_at column
);

-- Insert initial resource data
INSERT INTO resources (resource_name, available_units, total_units, date) VALUES 
('Water Supply', 100, 200, '2024-09-01'),
('Medical Kits', 50, 100, '2024-09-02'),
('Food Supplies', 200, 300, '2024-09-03'),
('Emergency Tents', 20, 50, '2024-09-04');

-- Drop and create disaster_reports table
DROP TABLE IF EXISTS disaster_reports;
CREATE TABLE disaster_reports (
    id INT AUTO_INCREMENT PRIMARY KEY,
    disaster_type VARCHAR(50) NOT NULL,
    location VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Added created_at column
);

-- Insert initial disaster report data
INSERT INTO disaster_reports (disaster_type, location, date, description) VALUES 
('Fire', 'California', '2024-08-15', 'A massive wildfire spreading rapidly across California.'),
('Flood', 'Queensland', '2024-09-01', 'Severe flooding caused by heavy rains in Queensland.'),
('Earthquake', 'Tokyo', '2024-09-07', 'A major earthquake with a magnitude of 7.2 hit Tokyo.'),
('Hurricane', 'Florida', '2024-09-12', 'A Category 5 hurricane approaching the coast of Florida.');

-- Drop and create alerts table
DROP TABLE IF EXISTS alerts;
CREATE TABLE alerts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    alert_type VARCHAR(50) NOT NULL,
    severity INT NOT NULL,
    critical VARCHAR(10) NOT NULL,
    description TEXT,
    date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Added created_at column
);

-- Insert initial alert data
INSERT INTO alerts (alert_type, severity, critical, description, date) VALUES 
('Fire', 4, 'High', 'Fire reported in the northern region.', '2024-08-10'),
('Flood', 3, 'Medium', 'Minor flooding in the eastern district.', '2024-08-15'),
('Earthquake', 5, 'High', 'Strong earthquake hit downtown area.', '2024-08-20'),
('Hurricane', 4, 'Low', 'Hurricane approaching coastal city.', '2024-08-25');

-- Drop and create live_updates table
-- Drop and create live_updates table
DROP TABLE IF EXISTS live_updates;
CREATE TABLE live_updates (
    id INT AUTO_INCREMENT PRIMARY KEY,
    update_type VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert initial live update data with different timestamps
INSERT INTO live_updates (update_type, description, created_at) VALUES 
('Resource', 'New resource Water Supply created with 100 available units.', NOW()), 
('Disaster', 'A new disaster report of a flood in Queensland.', DATE_SUB(NOW(), INTERVAL 1 HOUR)), 
('Alert', 'Severe weather alert issued for hurricane approaching Florida.', DATE_SUB(NOW(), INTERVAL 2 HOUR)), 
('Resource', 'New resource Medical Kits created with 50 available units.', DATE_SUB(NOW(), INTERVAL 3 HOUR)), 
('Disaster', 'Earthquake with magnitude 7.2 hit Tokyo, Japan.', DATE_SUB(NOW(), INTERVAL 4 HOUR)), 
('Alert', 'New alert issued: Wildfire detected in California.', DATE_SUB(NOW(), INTERVAL 5 HOUR)), 
('Resource', 'Food Supplies created with 200 units for emergency.', DATE_SUB(NOW(), INTERVAL 6 HOUR)), 
('Alert', 'Critical alert issued for high-risk area in Florida due to hurricane.', DATE_SUB(NOW(), INTERVAL 7 HOUR)), 
('Disaster', 'New fire reported in California spreading rapidly.', DATE_SUB(NOW(), INTERVAL 8 HOUR));
