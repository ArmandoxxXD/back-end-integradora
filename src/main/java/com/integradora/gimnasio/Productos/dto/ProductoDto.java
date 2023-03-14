package com.integradora.gimnasio.Productos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductoDto{

    @NotBlank(message = "El nombre de producto es obligatorio")
    String nombreProducto;
    @NotBlank(message = "La imagen es obligatoria")
    String imagen;
    @NotNull(message = "La cantidad no puede ser nula")
    int cantidad;
    @NotNull(message = "El precio no puede ser nulo")
    float precio;
    boolean existencia;
    boolean porAgotarse;
    @NotBlank(message = "El nombre de proveedor es obligatorio")
    String nombreProvedor;
    @NotBlank(message = "La caegoria es obligatoria")
    String categoria;
    
    public ProductoDto() {
    }

    public ProductoDto(String nombreProducto, String imagen, int cantidad, float precio, boolean existencia,
            boolean porAgotarse, String nombreProvedor, String categoria) {
        this.nombreProducto = nombreProducto;
        this.imagen = imagen;
        this.cantidad = cantidad;
        this.precio = precio;
        this.existencia = existencia;
        this.porAgotarse = porAgotarse;
        this.nombreProvedor = nombreProvedor;
        this.categoria=categoria;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean isExistencia() {
        if(cantidad>0){
            return true;
        }else{
           return false;
        }
    }

    public void setExistencia(boolean existencia) {
        this.existencia = existencia;
    }

    public boolean isPorAgotarse() {
        if(cantidad <= 10){
            return true;
        }else{
            return false;
        }
    }

    public void setPorAgotarse(boolean porAgotarse) {
        this.porAgotarse = porAgotarse;
    }

    public String getNombreProvedor() {
        return nombreProvedor;
    }

    public void setNombreProvedor(String nombreProvedor) {
        this.nombreProvedor = nombreProvedor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    
    
}
