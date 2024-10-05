package com.truemen.api.common.util;

import java.util.UUID;

/*
* 随机数有关工具类
* */
public class RandomNumber {

    // 唯一随机数生成
    public static UUID randomUUID() {
        UUID uniqueId = UUID.randomUUID();
        // 将 UUID 转换为一个长整型数字
        long mostSignificantBits = uniqueId.getMostSignificantBits();
        long leastSignificantBits = uniqueId.getLeastSignificantBits();

        return uniqueId;
    }
}
