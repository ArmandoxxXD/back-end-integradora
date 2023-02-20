package com.integradora.gimnasio.dto;

import jakarta.validation.constraints.NotBlank;

public class ClienteDto {
    
    @NotBlank
    private String nombre;

   public ClienteDto(){
   }

   public ClienteDto(String nombre){
    this.nombre=nombre;
   }

   public String getNombre(){
    return nombre;
   }

   public void setNombre(String nombre){
    this.nombre=nombre;
   }

}
