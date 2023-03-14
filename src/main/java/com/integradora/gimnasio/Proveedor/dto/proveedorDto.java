package com.integradora.gimnasio.Proveedor.dto;



import jakarta.validation.constraints.NotBlank;

public class proveedorDto{

    @NotBlank(message = "El nombre de proveedor es obligatorio")
    private String nombreProvedor;
    @NotBlank(message = "EL telefono del proveedor es obligatorio")
    private String telefono;
    @NotBlank(message = "EL email del proveedor es obligatorio")
    private String email;
    @NotBlank(message = "El pais del proveedor es obligatorio")
    private String pais;
    @NotBlank(message = "El estado del proveedor es obligatorio")
    private String estado;
    @NotBlank(message = "El municipio del proveedor es obligatorio")
    private String municipio;
    private String calle;
    private String logo;
    
    public proveedorDto() {
    }

    public proveedorDto( String nombreProvedor, String telefono, String email, String logo, String pais,String estado, String municipio, String calle) {
        this.nombreProvedor = nombreProvedor;
        this.telefono = telefono;
        this.email = email;
        this.logo = logo;
        this.pais = pais;
        this.estado = estado;
        this.municipio = municipio;
        this.calle = calle;
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
    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
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

}
