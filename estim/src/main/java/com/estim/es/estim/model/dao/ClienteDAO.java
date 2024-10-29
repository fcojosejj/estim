package com.estim.es.estim.model.dao;

import com.estim.es.estim.model.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ClienteDAO {
    public List<Cliente> buscaTodos();
    public Cliente buscaID(int id);
    public boolean crea(Cliente c);
    public boolean edita(Cliente c);
    public boolean borra(Integer id);
    public Cliente getByUser(String username);
}
