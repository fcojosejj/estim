package com.estim.es.estim.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity()
public class Carrito implements Serializable {
    @Id
    private Integer idCliente;
    @Id
    private Integer idVideojuego;


    public Carrito() {
        this.idCliente = 0;
        this.idVideojuego = 0;
    }
    public Carrito(Integer idCliente, Integer idVideojuego) {
        this.idCliente = idCliente;
        this.idVideojuego = idVideojuego;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdVideojuego() {
        return idVideojuego;
    }

    public void setIdVideojuego(Integer idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Carrito)) {
            return false;
        }
        Carrito other = (Carrito) object;
        return (this.idCliente == other.idCliente) && (this.idVideojuego == other.idVideojuego);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.idCliente + this.idVideojuego;
        return hash;
    }

    @Override
    public String toString() {
        return "Carrito[ idCliente=" + idCliente + ", idVideojuego=" + idVideojuego + " ]";
    }
}