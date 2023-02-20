package com.integradora.gimnasio.security.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integradora.gimnasio.dto.Mensaje;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{
    private static final Logger logger= LoggerFactory.getLogger(JwtEntryPoint.class);

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res,AuthenticationException e) throws IOException, ServletException {
        logger.error("el token no se formo o es invalido");
        Mensaje mensaje = new Mensaje(HttpStatus.UNAUTHORIZED,"el token no se formo o es invalido");
        res.setContentType("application/json");
        res.setStatus(mensaje.getStatus().value());
        res.getWriter().write(new ObjectMapper().writeValueAsString(mensaje));
        res.getWriter().flush();
        res.getWriter().close();
    }

}
