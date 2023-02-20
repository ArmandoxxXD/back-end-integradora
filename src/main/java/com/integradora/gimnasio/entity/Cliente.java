package com.integradora.gimnasio.entity;
import jakarta.persistence.*;


@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;

    public Cliente(){
    }

    public Cliente(String nombre){
        this.nombre=nombre;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }
}
