lock table `postMedia` write;

INSERT INTO
    `postMedia` (`postId`, `type`, `mediaUrl`, `createTime`)
VALUES
    (1, 0, 'http://example.com/image1.jpg', NOW()),
    (1, 1, 'http://example.com/audio1.mp3', NOW()),
    (2, 2, 'http://example.com/video1.mp4', NOW()),
    (3, 0, 'http://example.com/image2.jpg', NOW()),
    (3, 1, 'http://example.com/audio2.mp3', NOW()),
    (4, 2, 'http://example.com/video2.mp4', NOW()),
    (5, 0, 'http://example.com/image3.jpg', NOW()),
    (5, 1, 'http://example.com/audio3.mp3', NOW()),
    (6, 2, 'http://example.com/video3.mp4', NOW()),
    (7, 0, 'http://example.com/image4.jpg', NOW()),
    (7, 1, 'http://example.com/audio4.mp3', NOW());

unlock tables;