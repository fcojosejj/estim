package com.estim.es.estim;

import com.estim.es.estim.model.Cliente;
import com.estim.es.estim.model.dao.ClienteDAO;
import com.estim.es.estim.qualifiers.DAOJpa;
import com.estim.es.estim.qualifiers.DAOMap;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class ClientesIndentityStore implements IdentityStore {
    @Inject @DAOJpa
    private ClienteDAO clientesDAO;

    public ClientesIndentityStore(){}

    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {

        String username = usernamePasswordCredential.getCaller();
        String password = usernamePasswordCredential.getPasswordAsString();

        Cliente cliente = clientesDAO.getByUser(username);

        if(cliente != null && cliente.getPsswd().equals(password)){
            Set<String> roles = new HashSet<>(Arrays.asList(cliente.getRol()));

            return new CredentialValidationResult(username, roles);
        }
        return CredentialValidationResult.INVALID_RESULT;
    }
}
