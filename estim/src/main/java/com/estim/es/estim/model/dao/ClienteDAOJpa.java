package com.estim.es.estim.model.dao;

import com.estim.es.estim.model.Cliente;
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
public class ClienteDAOJpa implements ClienteDAO {

    private final Logger logger = Logger.getLogger(ClienteDAOJpa.class.getName());
    @PersistenceContext(unitName = "clientesPU")
    private EntityManager em;

    @Override
    public List<Cliente> buscaTodos() {
        List<Cliente> c;
        try{
            c = em.createQuery("Select c from Cliente c", Cliente.class).getResultList();
        } catch (Exception e){
            logger.log(Level.SEVERE,"No se pueden recuperar los clientes", e);
            c = new ArrayList<>();
        }
        return c;
    }

    @Override
    public Cliente buscaID(int id) {
        Cliente c = null;
        try {
            TypedQuery<Cliente> q = em.createQuery("Select c from Cliente c where c.id=:id",Cliente.class);
            q.setParameter("id", id);
            c = q.getSingleResult();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return c;
    }

    @Override
    public boolean crea(Cliente c) {
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
    public boolean edita(Cliente c) {
        boolean editado = false;
        try {
            c = em.merge(c);
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
            Cliente c = null;
            c = em.find(Cliente.class, id);
            em.remove(c);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }

    @Override
    public Cliente getByUser(String usuario){
        Cliente c = null;
        try {
            TypedQuery<Cliente> q = em.createQuery("Select c from Cliente c where c.usuario=:usuario",Cliente.class);
            q.setParameter("usuario", usuario);
            c = q.getSingleResult();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return c;
    }
}
