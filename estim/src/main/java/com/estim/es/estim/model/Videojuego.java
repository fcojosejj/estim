package com.estim.es.estim.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

@Entity()
public class Videojuego implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //Auto-incremental
    private Integer id;
    private String nombre;
    private String desarrolladora;
    @Size(max = 500, message = "La longitud de la descripción no debe superar los {max} caracteres")
    private String descripcion;
    @PositiveOrZero(message = "El precio debe ser mayor o igual a cero")
    private double precio;
    @DecimalMin(value = "0.0", inclusive = true, message = "La valoración debe ser mayor o igual a 0")
    @DecimalMax(value = "10.0", inclusive = true, message = "La valoración debe ser menor o igual a 10")
    private int valoracion;

    public Videojuego(){
        id = 0;
        nombre = "";
        desarrolladora = "";
        descripcion = "";
        precio = 0.0;
        valoracion = 0;
    }

    public Videojuego(Integer id, String nombre, String desarrolladora, String descripcion, double precio, int valoracion) {
        this.id = id;
        this.nombre = nombre;
        this.desarrolladora = desarrolladora;
        this.descripcion = descripcion;
        this.precio = precio;
        this.valoracion = valoracion;
    }

    public Videojuego(Videojuego v) {
        this.id = v.id;
        this.nombre = v.nombre;
        this.desarrolladora = v.desarrolladora;
        this.descripcion = v.descripcion;
        this.precio = v.precio;
        this.valoracion = v.valoracion;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesarrolladora() {
        return desarrolladora;
    }

    public void setDesarrolladora(String desarrolladora) {
        this.desarrolladora = desarrolladora;
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

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Videojuego)) {
            return false;
        }
        Videojuego other = (Videojuego) object;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return "Videojuego[ id=" + id + " ]";
    }
}