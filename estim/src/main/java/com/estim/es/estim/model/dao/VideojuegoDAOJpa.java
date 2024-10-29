package com.estim.es.estim.model.dao;

import com.estim.es.estim.model.Videojuego;
import com.estim.es.estim.qualifiers.DAOJpa;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequestScoped
@DAOJpa
@Transactional
public class VideojuegoDAOJpa implements VideojuegoDAO{

    private final Logger logger = Logger.getLogger(VideojuegoDAOJpa.class.getName());
    @PersistenceContext(unitName = "videojuegosPU")
    private EntityManager em;

    @Override
    public List<Videojuego> buscaTodos() {
        List<Videojuego> v;
        try{
            v = em.createQuery("Select v from Videojuego v", Videojuego.class).getResultList();
        } catch (Exception e){
            logger.log(Level.SEVERE,"No se pueden recuperar los videojuegos", e);
            v = new ArrayList<>();
        }
        return v;
    }

    @Override
    public Videojuego buscaID(int id) {
        Videojuego v = null;
        try {
            TypedQuery<Videojuego> q = em.createQuery("Select v from Videojuego v where v.id=:id",Videojuego.class);
            q.setParameter("id", id);
            v = q.getSingleResult();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return v;
    }

    @Override
    public boolean crea(Videojuego v) {
        boolean creado = false;

        try {
            em.persist(v);
            creado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return creado;
    }

    @Override
    public boolean edita(Videojuego v) {
        boolean editado = false;
        try {
            v = em.merge(v);
            editado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return editado;
    }

    @Override
    public boolean borra(Integer id) {
        boolean borrado = false;
        try {
            Videojuego v = null;
            v = em.find(Videojuego.class, id);
            em.remove(v);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }
}