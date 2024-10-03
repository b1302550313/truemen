lock table `userGroup` write;

INSERT INTO
    `userGroup` (`userId`, `groupId`, `createTime`, `updateTime`)
VALUES
    (
        1,
        '5307397',
        '2024-01-01 00:00:00',
        '2024-01-01 00:00:00'
    ),
    (
        2,
        '5307397',
        '2024-01-01 00:00:00',
        '2024-01-01 00:00:00'
    ),
    (
        3,
        '5307397',
        '2024-01-01 00:00:00',
        '2024-01-01 00:00:00'
    );

unlock tables;