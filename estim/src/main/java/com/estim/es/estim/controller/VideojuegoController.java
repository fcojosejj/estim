package com.estim.es.estim.controller;

import com.estim.es.estim.model.dao.VideojuegoDAO;
import com.estim.es.estim.model.Videojuego;
import com.estim.es.estim.qualifiers.DAOJpa;
import com.estim.es.estim.qualifiers.DAOMap;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named(value="videojuegoCtrl")
@ViewScoped
public class VideojuegoController implements Serializable {

    @Inject @DAOJpa
    private VideojuegoDAO videojuegosDAO;
    private Videojuego videojuego;
    List<Videojuego> videojuegos;
    public VideojuegoController() {}
    @PostConstruct
    public void init() {
        videojuego = new Videojuego();
        videojuegos = videojuegosDAO.buscaTodos();
    }
    public Videojuego getVideojuego() {
        return videojuego;
    }
    public void setVideojuego(Videojuego videojuego) {this.videojuego = videojuego;}
    public List<Videojuego> getVideojuegos() {
        return videojuegos;
    }

    public String crea(){
        videojuegosDAO.crea(videojuego);
        videojuegos = videojuegosDAO.buscaTodos();
        return "listado";
    }

    public String edita(){
        videojuegosDAO.edita(videojuego);
        return "detalle?faces-redirect=true&id=" + videojuego.getId();
    }
    public void recupera(){
        videojuego = videojuegosDAO.buscaID(videojuego.getId());
    }

    public String borra(Integer id) {
        videojuegosDAO.borra(id);
        return "catalogo";
    }
}
