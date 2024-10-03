lock table `groups` write;

INSERT INTO
    `groups` (
        `groupId`,
        `groupName`,
        `groupHead`,
        `createTime`,
        `updateTime`
    )
VALUES
    (
        '5307397',
        '楚们交流群',
        'group_1',
        '2024-01-01 00:00:00',
        '2024-01-01 00:00:00'
    );

unlock tables;