package com.truemen.api.user.utils;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {

    private static final long EXPIRE = 1000 * 60 * 60 * 24; // 1天
    // 密钥，用于签名 JWT
    private static final String SECRET_KEY = "aBcDeFgHiJkLmNoPqRsTuVwXyZ1234567890!@#$%^&*()_+";

    // 生成 JWT 令牌
    public static String getJwtToken(String id, String nickname) {
        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("id", id)
                .claim("nickname", nickname)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        return JwtToken;
    }
}