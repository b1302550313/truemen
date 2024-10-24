
-- Ensure the correct database is used
CREATE DATABASE IF NOT EXISTS verto;
USE verto;

-- Disable foreign key checks to avoid constraint issues during bulk insertions
SET FOREIGN_KEY_CHECKS = 0;

-- Create usercoreinfo table (no dependencies)
CREATE TABLE IF NOT EXISTS usercoreinfo (
  uid BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  email VARCHAR(100),
  createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Create bulletscreen table
CREATE TABLE IF NOT EXISTS bulletscreen (
  bulletId BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  landmarkId BIGINT NOT NULL,
  uid BIGINT NOT NULL,
  content VARCHAR(5000) NOT NULL,
  createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updateTime TIMESTAMP NULL,
  tag INT DEFAULT NULL,
  visibility INT NOT NULL,
  allowComment INT NOT NULL,
  duration INT NOT NULL,
  contactInfo VARCHAR(200) DEFAULT NULL,
  FOREIGN KEY (uid) REFERENCES usercoreinfo (uid) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Create bulletscreenlike table
CREATE TABLE IF NOT EXISTS bulletscreenlike (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  bulletId BIGINT NOT NULL,
  userId BIGINT NOT NULL,
  FOREIGN KEY (bulletId) REFERENCES bulletscreen (bulletId),
  FOREIGN KEY (userId) REFERENCES usercoreinfo (uid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Create chatrecord table
CREATE TABLE IF NOT EXISTS chatrecord (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  userId BIGINT DEFAULT NULL,
  friendId BIGINT DEFAULT NULL,
  msgContent VARCHAR(512),
  msgDate TIMESTAMP NULL,
  createTime TIMESTAMP NULL,
  updateTime TIMESTAMP NULL,
  talkType INT DEFAULT NULL,
  msgType INT DEFAULT '0',
  FOREIGN KEY (userId) REFERENCES usercoreinfo (uid) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (friendId) REFERENCES usercoreinfo (uid) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Insert data into usercoreinfo
INSERT INTO usercoreinfo (username, email) VALUES
('user1', 'user1@example.com'),
('user2', 'user2@example.com'),
('user3', 'user3@example.com');

-- Insert data into bulletscreen
INSERT INTO bulletscreen (landmarkId, uid, content, tag, visibility, allowComment, duration, contactInfo) VALUES
(1, 1, '测试弹幕 1', 1, 0, 0, 0, '1234567890,wx123456'),
(2, 2, '测试弹幕 2', 2, 1, 0, 1, '0987654321,wx987654'),
(3, 3, '测试弹幕 3', 3, 0, 1, 2, '1111111111,wx111111');

-- Insert data into bulletscreenlike
INSERT IGNORE INTO bulletscreenlike (bulletId, userId) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 3);

-- Insert data into chatrecord
INSERT INTO chatrecord (userId, friendId, msgContent, msgDate, createTime, updateTime) VALUES
(1, 2, 'Hello from user1 to user2', '2024-10-23 10:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 1, 'Reply from user2 to user1', '2024-10-23 10:01:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 1, 'Message from user3 to user1', '2024-10-23 10:05:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Re-enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;
