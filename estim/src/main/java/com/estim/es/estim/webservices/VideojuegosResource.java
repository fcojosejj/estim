package com.estim.es.estim.webservices;

import com.estim.es.estim.model.Videojuego;
import com.estim.es.estim.model.dao.VideojuegoDAO;
import com.estim.es.estim.qualifiers.DAOJpa;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/videojuegos") //Acceso /api/videojuegos
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class VideojuegosResource {

    @Inject @DAOJpa
    private VideojuegoDAO videojuegoDAO;

    @GET
    public List<Videojuego> listado() {
        return videojuegoDAO.buscaTodos();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevoVideojuego(@Valid Videojuego v) {
        Response response;
        if (videojuegoDAO.crea(v)) {
            response= Response.ok(v).build();
        } else {
            //Error messages (using Map for create generic json objects)
            List< Map<String,Object> > errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "No se ha podido crear el videojuego");
            err.put("videojuego", v);
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }

    @GET
    @Path("/{id}")
    public Response getVideojuego(@PathParam("id") int id) {
        Response response;
        Videojuego v = videojuegoDAO.buscaID(id);
        if(v != null) {
            response= Response.ok(v).build();
        } else {
            //Error messages (using Map for create generic json objects)
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "El videojuego al que se está intentando acceder no existe");
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }

    @DELETE
    @Path("/{id}")
    public Response borraVideojuego(@PathParam("id") int id) {
        Response response;

        if (videojuegoDAO.borra(id)) {
            response= Response.ok(id).build();
        } else {
            //Error messages (using Map for create generic json objects)
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "El videojuego que se está intentado borrar no existe");
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }

        return response;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificaVideojuego(@Valid Videojuego v, @PathParam("id") Integer id) {
        Response response;
        v.setId(id);
        if (videojuegoDAO.edita(v)) {
            response= Response.ok(v).build();
        } else {
            //Error messages (using Map for create generic json objects)
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>(); //Error messages
            err.put("message", "No se ha podido modificar el videojuego");
            err.put("videojuego", v);
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }
}
