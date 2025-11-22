package carveo_portal.carveoManagement.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    // To generate secrete key
    private static String secreteKey;

    JwtUtil(){
        SecureRandom random = new SecureRandom();

        //secrete key should be 256-bits as per JWT Rule
        byte[] key = new byte[32]; // 256-bits
        random.nextBytes(key);
        secreteKey = Base64.getEncoder().encodeToString(key);

    }
    //generate token logic
    public String generateToken(String username, List<String> roles){
        return Jwts.builder().setSubject(username).claim("roles", roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*2))
                .signWith(getSignKeyedKey(), SignatureAlgorithm.HS256)
                .compact();


    }

    private Key getSignKeyedKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secreteKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
