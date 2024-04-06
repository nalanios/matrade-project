-- Use the correct schema
USE maitre_d;

-- Drop tables if they already exist
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS tables;

-- Users Table
CREATE TABLE IF NOT EXISTS users (
  user_id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  user_type ENUM('customer', 'owner', 'staff') NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Restaurants Table
CREATE TABLE IF NOT EXISTS restaurants (
  restaurant_id INT AUTO_INCREMENT PRIMARY KEY,
  owner_id INT NOT NULL,
  name VARCHAR(255) NOT NULL
  -- Add other necessary fields as required
);

-- Reservations Table
CREATE TABLE IF NOT EXISTS reservations (
  reservation_id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  restaurant_id INT NOT NULL,
  reservation_time TIMESTAMP NOT NULL
  -- Add other necessary fields as required
);

-- Reviews Table
CREATE TABLE IF NOT EXISTS reviews (
  review_id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  restaurant_id INT NOT NULL,
  review_text TEXT,
  rating INT
  -- Add other necessary fields as required
);

-- Tables Table
CREATE TABLE IF NOT EXISTS tables (
  table_id INT AUTO_INCREMENT PRIMARY KEY,
  restaurant_id INT NOT NULL,
  capacity INT NOT NULL
  -- Add other necessary fields as required
);
