package com.estim.es.estim.model.dao;

import com.estim.es.estim.model.Carrito;
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
public class CarritoDAOJpa implements CarritoDAO{

    private final Logger logger = Logger.getLogger(CarritoDAOJpa.class.getName());
    @PersistenceContext(unitName = "carritoPU")
    private EntityManager em;
    
    @Override
    public List<Videojuego> buscaTodosVideojuegosCarrito(Integer idCliente) {
        List<Videojuego> v;
        try {
            v = em.createQuery("SELECT v FROM Videojuego v, Carrito c WHERE v.id=c.idVideojuego AND c.idCliente=:idCliente", Videojuego.class)
                    .setParameter("idCliente", idCliente)
                    .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE,"No se pueden recuperar los videojuegos para el carrito del cliente " + idCliente, e);
            v = new ArrayList<>();
        }
        return v;
    }

    @Override
    public boolean anadirAlCarrito(Carrito c) {
        boolean creado = false;

        try {
            em.persist(c);
            creado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return creado;
    }

    @Override
    public boolean borraVideojuegoDeCarrito(Integer idCliente, Integer idVideojuego) {
        boolean borrado = false;
        try {
            TypedQuery<Carrito> query = em.createQuery("SELECT c FROM Carrito c WHERE c.idCliente = :idCliente AND c.idVideojuego = :idVideojuego", Carrito.class);
            query.setParameter("idCliente", idCliente);
            query.setParameter("idVideojuego", idVideojuego);
            Carrito c = query.getSingleResult();
            em.remove(c);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }

    @Override
    public double getTotal(Integer idCliente) {
        double total_precio;
        try {
            total_precio = em.createQuery("SELECT SUM(v.precio) FROM Videojuego v, Carrito c WHERE v.id=c.idVideojuego AND c.idCliente=:idCliente", Double.class)
                    .setParameter("idCliente", idCliente)
                    .getSingleResult().doubleValue();
        } catch (Exception e) {
            logger.log(Level.SEVERE,"No se puede recuperar el precio total para el carrito del cliente " + idCliente, e);
            total_precio = 10;
        }
        return total_precio;
    }
}