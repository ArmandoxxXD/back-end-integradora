package com.integradora.gimnasio.Proveedor.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "proveedores")
public class proveedor implements Serializable{

    @Id
    private int id;
    private String nombreProvedor;
    private String telefono;
    private String email;
    private String logo;
    private String pais;
    private String estado;
    private String municipio;
    private String calle;

    
    public proveedor() {
    }


    public proveedor(int id, String nombreProvedor, String telefono, String email, String logo, String pais,
            String estado, String municipio, String calle) {
        this.id = id;
        this.nombreProvedor = nombreProvedor;
        this.telefono = telefono;
        this.email = email;
        this.logo = logo;
        this.pais = pais;
        this.estado = estado;
        this.municipio = municipio;
        this.calle = calle;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombreProvedor() {
        return nombreProvedor;
    }
    public void setNombreProvedor(String nombreProvedor) {
        this.nombreProvedor = nombreProvedor;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
   
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getMunicipio() {
        return municipio;
    }
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getLogo() {
        return logo;
    }


    public void setLogo(String logo) {
        this.logo = logo;
    }



}
