lock table `userBaseInfo` write;

INSERT INTO
    `userBaseInfo` (
        `uid`,
        `userName`,
        `avatar`,
        `gender`,
        `birthDate`,
        `bio`
    )
VALUES
    (
        1,
        'Alice',
        'avatar_01.png',
        '女',
        '1990-01-01',
        'Hello, I am Alice.'
    ),
    (
        2,
        'Bob',
        'avatar_02.png',
        '男',
        '1985-05-15',
        'Hi there, I am Bob.'
    ),
    (
        3,
        'Charlie',
        'avatar_03.png',
        '男',
        '1992-08-20',
        'Charlie here.'
    );

unlock tables;