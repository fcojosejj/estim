package com.estim.es.estim.model.dao;

import com.estim.es.estim.model.Carrito;
import com.estim.es.estim.model.Videojuego;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public interface CarritoDAO {
    public List<Videojuego> buscaTodosVideojuegosCarrito(Integer idCliente);
    public boolean anadirAlCarrito(Carrito c);
    public boolean borraVideojuegoDeCarrito(Integer idCliente, Integer idVideojuego);
    public double getTotal(Integer idCliente);
}
