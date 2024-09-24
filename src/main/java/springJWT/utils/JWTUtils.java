package springJWT.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springJWT.models.Users;

import java.util.Date;
import java.util.Map;

@Service
public class JWTUtils {

    final private String SECRET_KEY = "1234567890asdyfugyihulj123456789012345678901234567cgg8901234567890";

    @Autowired
    private Users usersRep;

    public String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean verifyToken(String token){
        final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        Date exp = claims.getExpiration();
        System.out.println(token);
        System.out.println(exp);
        return exp.before(new Date());
    }
}
