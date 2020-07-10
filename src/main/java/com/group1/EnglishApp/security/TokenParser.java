package com.group1.EnglishApp.security;

import com.group1.EnglishApp.exception.EnglishAppException;
import com.group1.EnglishApp.exception.ExpiredTokenException;
import com.group1.EnglishApp.exception.TokenMalformedException;
import com.group1.EnglishApp.model.Role;
import com.group1.EnglishApp.model.User;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * @author Hai Dang
 */
@Component
public class TokenParser {
    public static final int TOKEN_EXPIRATION_1440_MIN = 1440; // 1 day
    public static final int TOKEN_EXPIRATION_7_DAYS = 1440 * 7;
    public static final int TOKEN_EXPIRATION_30_MIN = 30;
    public static final int TOKEN_EXPIRATION_480_MIN = 480;

    public static final String USER_SUBJECT = "user";
    public static final String API_SUBJECT = "api";
    public static final String RESET_PASSWORD_SUBJECT = "resetPassword";
    public static final String TOKEN_SECRET = "r8QkBKBmBxCWKSv2nxdubNKXZckZbKQvKreyAgZhpZYEvyFdQNdnpwEZ7t7aXBK5";

    public static final String EMAIL = "email";
    public static final String ROLE = "role";
    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String CREDENTIALS = "credentials";

    public static final String APPLICATION_ID = "applicationId";
    public static final String APPLICATION_NAME = "applicationName";
    public static final String APPLICATION_URL = "applicationUrl";

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenParser.class);
    private Key secretKey = null;

    @PostConstruct
    public void init() {
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        if (secretKey == null) {
            byte[] keyBytes = DatatypeConverter.parseBase64Binary(TOKEN_SECRET);
            secretKey = new SecretKeySpec(keyBytes, signatureAlgorithm.getJcaName());
        }
    }

    /**
     * @param subject
     * @param token
     * @return
     */
    public Claims parseToken(String subject, String token) throws EnglishAppException {
        try {
            Claims body = Jwts.parser()
                    .requireSubject(subject)
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
            return body;
        } catch (ExpiredJwtException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ExpiredTokenException("Token Expired");
        } catch (ClaimJwtException claimEx) {
            LOGGER.error(claimEx.getMessage());
            throw new EnglishAppException(String.format("Token subject[%s] was not found", subject));
        } catch (JwtException | ClassCastException e) {
            LOGGER.error(e.getMessage(), e);
            throw new TokenMalformedException("Token Invalid");
        }
    }

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with email, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public User parseUserToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            User user = new User();

            if (USER_SUBJECT.equals(body.getSubject())) {
                user.setId(Long.valueOf(body.get(ID).toString()));
                user.setUsername(body.get(USERNAME).toString());
                String roleStr = body.get(ROLE).toString();
                user.setRole(new Role(roleStr));
            }

            return user;

        } catch (ExpiredJwtException e) {
            LOGGER.error(e.getMessage());
            throw new ExpiredTokenException("Token Expired");
        } catch (JwtException | ClassCastException e) {
            LOGGER.error(e.getMessage());
            throw new TokenMalformedException("Token Invalid");
        }
    }

    /**
     * Generates a JWT token containing email as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     *
     * @param user the user for which the token will be generated
     * @return the JWT token
     */
    public String generateUserToken(User user, int expirationInMinutes) {
        Claims claims = Jwts.claims().setSubject(USER_SUBJECT);
        claims.put(ID, user.getId());
        claims.put(USERNAME, user.getUsername());
        claims.put(ROLE, user.getRole().getName());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(Date.from(LocalDateTime
                        .now()
                        .plusMinutes(expirationInMinutes)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * @param user
     * @return
     */
    public String generateUserToken(User user) {
        return generateUserToken(user, TOKEN_EXPIRATION_480_MIN);
    }

    /**
     * @param subject
     * @param claimMap
     * @param expirationInMinutes (-1 = infinite)
     * @return
     */
    public String generateToken(String subject, Map<String, Object> claimMap, int expirationInMinutes) {

        Claims claims = Jwts.claims().setSubject(subject);
        if (claimMap != null) {
            if (API_SUBJECT.equals(subject)) {
                // in case of API token then set role to ADMIN
                claims.put(ROLE, "ROLE_ADMIN");
            }
            claims.putAll(claimMap);
        }

        Date expiration = null;
        if (expirationInMinutes > 0) {
            expiration = Date.from(LocalDateTime
                    .now()
                    .plusMinutes(expirationInMinutes)
                    .atZone(ZoneId.systemDefault())
                    .toInstant());
        }

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setIssuedAt(new Date())
                .compact();
    }
}
