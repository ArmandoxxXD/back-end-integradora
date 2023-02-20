package com.integradora.gimnasio.dto;

public class Mensaje2 {
    private String mensaje;

    public Mensaje2(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje(){
        return this.mensaje;
    }

    public void setMensaje(String mensaje){
        this.mensaje=mensaje;
    }
}
