package com.estim.es.estim.model.dao;

import com.estim.es.estim.model.Cliente;
import com.estim.es.estim.qualifiers.DAOMap;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
@DAOMap
public class ClienteDAOMap implements ClienteDAO {
    private Map<Integer, Cliente> clientes = null;
    private int idCliente = 4;

    public ClienteDAOMap() {
        if (clientes == null) {
            clientes = new HashMap<>();
            clientes.put(1, new Cliente(1, "Paco", "Martinez Soria", "11111111A", "paco@hotmail.com", LocalDate.of(2001, 11, 29), "USUARIOS", "paco123", "paco123", "Calle Normandia", 4, "Jaén", "23300", "España"));
            clientes.put(2, new Cliente(2, "Laura", "Ortiz Gómez", "22222222B", "laura@hotmail.com", LocalDate.of(2001, 1, 29), "USUARIOS", "laura123", "laura123", "Calle Lucrecia", 15, "Barcelona", "69345", "España"));
            clientes.put(3, new Cliente(3, "Ander", "Herrera Goikoetxea", "33333333C", "ander@hotmail.com", LocalDate.of(1987, 5, 24), "USUARIOS", "anderherrera", "anderherrera", "Calle Lucas Román", 9, "Sevilla", "17004", "España"));
        }
    }
    @Override
    public List<Cliente> buscaTodos() {
        return clientes.values().stream().collect(Collectors.toList());
    }
    @Override
    public Cliente buscaID(int id) { return clientes.get(id); }
    @Override
    public boolean crea(Cliente c) {
        Cliente nc=new Cliente(c);
        nc.setId(idCliente);
        clientes.put(idCliente, nc);
        c.setId(idCliente);
        idCliente++;
        return true;
    }
    @Override
    public boolean edita(Cliente c) {
        boolean result=false;
        if (clientes.containsKey(c.getId())) {
            Cliente nc=new Cliente(c);
            clientes.replace(c.getId(),nc);
            result=true;
        }
        return result;
    }
    @Override
    public boolean borra(Integer id) {
        boolean result=false;
        if (clientes.containsKey(id)) {
            clientes.remove(id);
            result = true;
        }
        return result;
    }

    @Override
    public Cliente getByUser(String username) {
        Cliente cliente = null;
        for(Cliente c : clientes.values()){
            if(c.getUsuario().equals(username)){
                cliente = c;
                break;
            }
        }

        if (cliente != null) cliente = new Cliente(cliente);
        return cliente;
    }

    public int numClientes() {
        return clientes.size();
    }
}
