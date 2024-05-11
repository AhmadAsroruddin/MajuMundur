package com.ari.majumundur.Util;

import com.ari.majumundur.Models.Entities.AppUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    @Value("${app.shopee.jwt.jwt-secret}")
    private String jwtSecret;
    @Value("${app.shopee.jwt.app-name}")
    private String appName;

    @Value("${app.shopee.jwt.jwt-expired}")
    private Long expiredTime;

    public String generateToken(AppUser appUser){
        try{
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));

            String token = JWT.create()
                    .withIssuer(appName)//INFO APLIKASI YANG AKAN DIBUAT
                    .withSubject(appUser.getId()) //MENENTUKAN OBJECT YANG AKAN DIBUAT
                    .withExpiresAt(Instant.now().plusSeconds(3600))
                    .withIssuedAt(Instant.now())//UNTUK MENENTUKAN KAPAN WAKTU DIBUAT
                    .withClaim("role", appUser.getRole().name())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException jwt){
            throw new RuntimeException();
        }
    }


    public boolean verifyJwtToken(String token){
        try{
            Algorithm algorithm =  Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            return decodedJWT.getIssuer().equals(appName);

        }   catch (JWTVerificationException e){
            throw  new RuntimeException();
        }
    }

    //TODO 1:Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));: Baris ini membuat objek Algorithm yang mewakili algoritma HMAC256 yang akan digunakan untuk memverifikasi tanda tangan JWT. Ini dilakukan dengan memanggil metode statis HMAC256() dari kelas Algorithm dan memberikannya kunci rahasia JWT (dalam bentuk byte array) sebagai parameter. Kunci rahasia ini digunakan untuk membuat dan memverifikasi tanda tangan HMAC256.
    //
    //TODO 2:JWTVerifier jwtVerifier = JWT.require(algorithm).build();: Baris ini membuat objek JWTVerifier yang akan digunakan untuk memverifikasi JWT. Ini dilakukan dengan memanggil metode statis require() dari kelas JWT dan memberikannya objek Algorithm yang telah dibuat sebelumnya sebagai parameter. Kemudian, dengan memanggil metode build(), kita mendapatkan instance dari JWTVerifier yang telah dikonfigurasi dengan algoritma yang tepat untuk memverifikasi JWT.
    //
    //DecodedJWT decodedJWT = jwtVerifier.verify(token);: Baris ini digunakan untuk memverifikasi dan mendekode JWT yang diberikan (yang disimpan dalam variabel token). Ini dilakukan dengan memanggil metode verify() dari objek JWTVerifier yang telah dibuat sebelumnya. Jika JWT valid, metode verify() akan mengembalikan objek DecodedJWT yang berisi klaim-klaim JWT yang terdekripsi.

    public Map<String, String> getUserInfoByToken(String token) {
        try{
            Algorithm algorithm =  Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("userId", decodedJWT.getSubject());
            userInfo.put("role", decodedJWT.getClaim("role").asString());

            return userInfo;
        }catch (JWTVerificationException e){
            throw new RuntimeException();
        }
    }

}