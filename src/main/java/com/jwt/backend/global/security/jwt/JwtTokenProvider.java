package com.jwt.backend.global.security.jwt;

import com.jwt.backend.domain.member.service.CustomUserDetailsService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("spring.jwt.secret")
    private String secretKey;

    private final Long ACCESS_TOKEN_VALID_TIME = 1000L * 60 * 30; //30분

//    private Long REFRESH_TOKEN_VALID_TIME = 1000L * 60 * 60 * 24 * 7; //일주일
    private final CustomUserDetailsService customUserDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createAccessToken(String email, String role) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // JWT 타입 지정 bearer
                .setClaims(claims) // 내용
                .setIssuedAt(now) // 발급시간
                .setIssuer("cloudwi")
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME)) // 만료시간
                .setSubject(email)
                .signWith(SignatureAlgorithm.HS256, secretKey) // 알고리즘, 시크릿 키
                .compact();
    }

    //토큰에서 인증정보를 조회하는 메서드
    public Authentication getAuthentication(String token) {
        token = BearerRemove(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserEmail(String token) {
        token = BearerRemove(token);
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("AccessToken");
    }

    public boolean validateAccessToken(String token) {
        token = BearerRemove(token);
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private String BearerRemove(String token) {
        return token.substring("Bearer ".length());
    }
}
