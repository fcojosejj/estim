package com.estim.es.estim.controller;

import com.estim.es.estim.model.Videojuego;
import com.estim.es.estim.model.dao.CarritoDAO;
import com.estim.es.estim.model.Carrito;
import com.estim.es.estim.qualifiers.DAOJpa;
import com.estim.es.estim.qualifiers.DAOMap;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named(value="carritoCtrl")
@ViewScoped
public class CarritoController implements Serializable {

    @Inject @DAOJpa
    private CarritoDAO carritosDAO;
    private Carrito carrito;
    private List<Videojuego> productos;

    public CarritoController() {}
    @PostConstruct
    public void init() {
        carrito = new Carrito();
        productos = carritosDAO.buscaTodosVideojuegosCarrito(carrito.getIdCliente());
    }
    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {this.carrito = carrito;}
    public List<Videojuego> getVideojuegosCliente(Integer idCliente) {
        return carritosDAO.buscaTodosVideojuegosCarrito(idCliente);
    }

    public String anadirAlCarrito(Integer idCliente, Integer idVideojuego){
        Carrito NuevoCarrito = new Carrito(idCliente, idVideojuego);
        carritosDAO.anadirAlCarrito(NuevoCarrito);
        productos = carritosDAO.buscaTodosVideojuegosCarrito(carrito.getIdCliente());
        return "/videojuegos/carrito?faces-redirect=true";
    }


    public String borraVideojuegoDeCarrito(Integer idCliente, Integer idVideojuego) {
        carritosDAO.borraVideojuegoDeCarrito(idCliente, idVideojuego);
        return "carrito";
    }

    public double getTotal(Integer idCliente){
        return carritosDAO.getTotal(idCliente);
    }
}
