package com.estim.es.estim.model.dao;

import com.estim.es.estim.model.Videojuego;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public interface VideojuegoDAO {
    public List<Videojuego> buscaTodos();
    public Videojuego buscaID(int id);
    public boolean crea(Videojuego v);
    public boolean edita(Videojuego v);
    public boolean borra(Integer id);
}
