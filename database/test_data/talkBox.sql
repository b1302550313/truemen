lock table `talkBox` write;

INSERT INTO
    `talkBox` (
        `userId`,
        `talkId`,
        `talkType`,
        `createTime`,
        `updateTime`
    )
VALUES
    (
        1,
        1,
        1,
        '2024-02-23 19:35:54',
        '2024-02-23 19:35:54'
    ),
    (
        1,
        2,
        0,
        '2024-02-23 19:36:03',
        '2024-02-23 19:36:03'
    ),
    (
        3,
        1,
        1,
        '2024-02-23 15:26:32',
        '2024-02-23 15:26:32'
    );

unlock tables;