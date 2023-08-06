package com.qxc.gacspboot.controller.UTiles;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.qxc.gacspboot.pojo.Login;
import com.qxc.gacspboot.pojo.Register;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class UserId {
    @Contract(pure = true)
    public static @NotNull String getUUID(){
        return UUID.randomUUID().toString();
    }

    public static @NotNull Map<String, Object> getMapper(@NotNull Register register) {
        Map<String,Object> map=new HashMap<>();
        map.put("username",register.getUsername());
        map.put("uuid",register.getUuid());
        return map;
    }

    @Contract(pure = true)
    public static String creatToken(String uuid){
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.SECOND, 60*60*24);
        Date expireDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        return JWT.create()
                .withHeader(map)//头
                .withClaim("userId", uuid)
                .withSubject("uuid")//
                .withIssuedAt(new Date())//签名时间
                .withExpiresAt(expireDate)//过期时间
                .sign(Algorithm.HMAC256("sdjhakdhaqxcnwuzjucas;961005"));
    }

    @Contract(pure = true)
    public static boolean verifyDate(String token){
        return JWT.decode(token).getExpiresAt().before(new Date());
    }
}
