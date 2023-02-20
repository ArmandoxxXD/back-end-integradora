package com.integradora.gimnasio.dto;

import org.springframework.http.HttpStatus;

public class Mensaje {
    private HttpStatus status;
    private String mensaje;

    public Mensaje() {
    }

    public Mensaje(HttpStatus status,String mensaje){
        this.status = status;
        this.mensaje=mensaje;
    }
    
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMensaje(){
        return this.mensaje;
    }

    public void setMensaje(String mensaje){
        this.mensaje=mensaje;
    }
}
