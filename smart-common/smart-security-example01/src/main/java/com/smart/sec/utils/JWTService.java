package com.smart.sec.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.smart.sec.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * jwt 工具类
 */
@Component
public class JWTService {

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private  Long expiration;
    // 创建token
    public  String generateToken(String username) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, secret)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    /**
     * 用户信息生成Token
     *
     * @param user
     * @return
     */
    public static String createToken(User user) {
        //获取过期时间（一天后）
        Date expireDate = getExpireDate();
        String token = JWT.create()
                .withAudience(user.getUserId().toString())
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }


    // 从token中获取用户名
    public  String getUserNameFromToken(String token){
        return getTokenBody(token).getSubject();
    }

    // 是否已过期
    public  boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private static Date getExpireDate() {
        LocalDate expireLocalDate = LocalDate.now().plusDays(1);
        Instant instant = expireLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }



}
