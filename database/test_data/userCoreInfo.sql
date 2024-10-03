lock table `userCoreInfo` write;

INSERT INTO
    `userCoreInfo` (
        `userId`,
        `phone`,
        `wechatId`,
        `password`,
        `permission`
    )
VALUES
    (
        'user123',
        '12345678901',
        'wx_user123',
        'encrypted_password_1',
        '1'
    ),
    (
        'user456',
        '09876543211',
        'wx_user456',
        'encrypted_password_2',
        '2'
    ),
    (
        'user789',
        '11223344551',
        NULL,
        'encrypted_password_3',
        '1'
    );

unlock tables;