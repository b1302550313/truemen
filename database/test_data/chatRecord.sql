lock table `chatRecord` write;

INSERT INTO
    `chatRecord` (
        `userId`,
        `friendId`,
        `msgContent`,
        `msgDate`,
        `createTime`,
        `updateTime`,
        `talkType`,
        `msgType`
    )
VALUES
    (
        2,
        1,
        'Test2to1',
        '2024-02-23 11:33:37',
        '2024-02-23 11:33:37',
        '2024-02-23 11:33:37',
        0,
        0
    ),
    (
        3,
        1,
        'Test3to1',
        '2024-02-23 11:33:49',
        '2024-02-23 11:33:49',
        '2024-02-23 11:33:49',
        0,
        0
    ),
    (
        2,
        3,
        'Test2to3',
        '2024-02-23 11:33:58',
        '2024-02-23 11:33:58',
        '2024-02-23 11:33:58',
        0,
        0
    );

unlock tables;