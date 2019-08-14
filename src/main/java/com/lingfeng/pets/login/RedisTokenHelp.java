package com.lingfeng.pets.login;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.jsonwebtoken.impl.Base64Codec;

@Component
public class RedisTokenHelp implements TokenHelper{
    
    @Autowired
    private RedisClient redisClient;
    
    
    /**
     * 公用密钥 保存在服务器端，客户端是不会知道密钥的，以防被攻击
     */
    public static String SECRET = "pets";
    
    @Override
    public String create(String userName, String userPass, String userId) {
        Date iaDate = new Date();
        //设置过期时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 30);
        Date expiresDate = nowTime.getTime();
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        //附带用户名和用户id生成认证
        String token = null;
            try {
            token = JWT.create()
                    .withHeader(map)//header
                    .withClaim("userName", userName)//payload
                    .withClaim("userPass", userPass)
                    .withClaim("userId", userId)
                    .withExpiresAt(expiresDate)//设置有效期
                    .withIssuedAt(iaDate)//设置签发时间
                    .sign(Algorithm.HMAC256(SECRET));
            
            } catch (IllegalArgumentException | JWTCreationException | UnsupportedEncodingException e) {
            e.printStackTrace();
            }
       // TokenModel mode = new TokenModel(userId, token);
        redisClient.set(userId == null ? null : String.valueOf(userId), token, RedisClient.TOKEN_EXPIRES_SECOND);
        return token;
    }
    

    @Override
    public boolean delete(String id) {
        return redisClient.remove(id == null ? null : String.valueOf(id));
    }
 
    
    @Override
    public boolean verifyToke(String token) {
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC256(SECRET);
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwts = verifier.verify(token);
        String jwt = jwts.toString();
        if (!StringUtils.isNotBlank(jwt)) {
            String[] split = jwt.split("\\.");
            String content = split[1];//负荷
            String s = Base64Codec.BASE64URL.decodeToString(content);
            String sign = split[2];//签名
            JSONObject jsonObject1 = JSONObject.parseObject(s);
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            long expiresSecond = (long) jsonObject1.get("expiresSecond");
            //判断是否过期
            if(now.getTime()>expiresSecond) return false;
            return true;
        }
        return false;
    }

}
