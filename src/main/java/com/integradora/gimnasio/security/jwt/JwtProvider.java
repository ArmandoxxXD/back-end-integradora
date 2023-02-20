package com.integradora.gimnasio.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.integradora.gimnasio.security.service.UserDetailsImp;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

    private static final Logger logger= LoggerFactory.getLogger(JwtEntryPoint.class);
    
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){
        UserDetailsImp userDetailsImp=(UserDetailsImp) authentication.getPrincipal();
        return Jwts.builder()
            .signWith(getKey(secret))
            .setSubject(userDetailsImp.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime()+expiration*1000))
            .claim("roles", getRoles(userDetailsImp))
            .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(getKey(secret)).build().parseClaimsJws(token).getBody().getSubject();

    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(getKey(secret)).build().parseClaimsJws(token).getBody();
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("Token expirado");
        } catch (UnsupportedJwtException e) {
            logger.error("Token insoportado");
        } catch (MalformedJwtException e) {
            logger.error("Token mal formado");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("Fallo en el token");
        }
        return false;
    }

    private List<String> getRoles(UserDetailsImp userDetailsImp){
        return userDetailsImp.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

    private Key getKey(String secret){
        byte [] secretBytes= Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    } 

}
