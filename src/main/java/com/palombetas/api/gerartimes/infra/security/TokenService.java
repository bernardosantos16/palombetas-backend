package com.palombetas.api.gerartimes.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.palombetas.api.gerartimes.domain.entity.UserEntity;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Setter
    private Double expirationHours = 2.0;

    public String generateToken(UserEntity userEntity){
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.create()
                    .withIssuer("API GERAR TIMES")
                    .withSubject(userEntity.getLogin())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new RuntimeException("error when generating token", ex);
        }
    }

    public String getSubject(String jwtToken){
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.require(algorithm)
                    .withIssuer("API GERAR TIMES")
                    .build()
                    .verify(jwtToken)
                    .getSubject();
        } catch (JWTVerificationException ex){
            throw new RuntimeException("invalid token or expired", ex);
        }
    }

    private Instant generateExpirationDate() {
        var hoursInSeconds = (long) (expirationHours * 3600);
        return Instant.now().plusSeconds(hoursInSeconds); // Token válido por 2 horas
    }
}
