lock table `userFriend` write;

INSERT INTO
    `userFriend` (
        `userId`,
        `userFriendId`,
        `createTime`,
        `updateTime`
    )
VALUES
    (
        1,
        2,
        '2024-02-23 13:02:45',
        '2024-02-23 13:02:45'
    ),
    (
        1,
        3,
        '2024-02-23 13:02:45',
        '2024-02-23 13:02:45'
    ),
    (
        2,
        3,
        '2024-02-23 13:02:45',
        '2024-02-23 13:02:45'
    );

unlock tables;