package com.ruslan.articulosfxml.modelo;

public class Articulo {

    int id;

    String codigo;
    String nombre;

    String seccion;

    String descripcion;

    double precio;

    String fecha;

    int stock;
    String origen;



    public Articulo() {
        this(0,"","","","",0,"",0,"");
    }




    public Articulo(int id, String codigo, String nombre, String seccion, String descripcion, double precio, String fecha, int stock, String origen) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.seccion = seccion;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha = fecha;
        this.stock = stock;
        this.origen = origen;
    }

    public Articulo(String codigo, String nombre, String seccion, String descripcion, double precio, String fecha, int stock, String origen) {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    @Override
    public String toString() {
        return id + " | " + codigo + " | " + nombre+ " | " + seccion+ " | " + descripcion+ " | " + precio+ " € "+" | " + fecha+ " | " + stock
                + " | " + origen + " | ";
    }
}
