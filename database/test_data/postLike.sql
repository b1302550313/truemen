lock table `postLike` write;

INSERT INTO
    `postLike` (`postId`, `userId`)
VALUES
    (1, 2),
    (1, 3),
    (2, 1),
    (2, 1);

unlock tables;