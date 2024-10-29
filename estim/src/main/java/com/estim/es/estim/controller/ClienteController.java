package com.estim.es.estim.controller;

import com.estim.es.estim.model.dao.ClienteDAO;
import com.estim.es.estim.model.Cliente;
import com.estim.es.estim.qualifiers.DAOJpa;
import com.estim.es.estim.qualifiers.DAOMap;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.Serializable;
import java.util.List;

@Named(value="clienteCtrl")
@ViewScoped
public class ClienteController implements Serializable {

    @Inject @DAOJpa
    private ClienteDAO clientesDAO;
    private Cliente cliente;
    List<Cliente> clientes;
    public ClienteController() {}
    @PostConstruct
    public void init() {
        cliente = new Cliente();
        clientes = clientesDAO.buscaTodos();
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {this.cliente = cliente;}
    public List<Cliente> getClientes() {
        return clientes;
    }

    public String crea(){
        clientesDAO.crea(cliente);
        clientes = clientesDAO.buscaTodos();
        return "/clientes/listado?faces-redirect=true";
    }

    public String edita(){
        clientesDAO.edita(cliente);
        return "detalle?faces-redirect=true&id=" + cliente.getId();
    }
    public void recupera(){
        cliente = clientesDAO.buscaID(cliente.getId());
    }

    public String borra(Integer id) {
        clientesDAO.borra(id);
        return "listado";
    }

    public Cliente getByUser(String usuario){
        return clientesDAO.getByUser(usuario);
    }

    public String irPerfil(String usuario){
        Cliente c = clientesDAO.getByUser(usuario);
        return "/clientes/detalle?faces-redirect=true&id=" + c.getId();
    }

    @Inject
    HttpServletRequest request;
    public String logout() throws ServletException {
        request.logout();
        request.getSession().invalidate();
        return "/index?faces-redirect=true";
    }
}
