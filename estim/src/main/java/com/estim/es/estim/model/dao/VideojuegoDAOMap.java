package com.estim.es.estim.model.dao;

import com.estim.es.estim.model.Videojuego;
import com.estim.es.estim.qualifiers.DAOMap;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@ApplicationScoped
@DAOMap
public class VideojuegoDAOMap implements VideojuegoDAO {
    private Map<Integer, Videojuego> videojuegos = null;
    private int idVideojuego = 4;

    public VideojuegoDAOMap() {
        if (videojuegos == null) {
            videojuegos = new HashMap<>();
            videojuegos.put(1, new Videojuego(1,
                    "Minecraft", "Mojang", "Juego de bloques", 20.00, 5));
            videojuegos.put(2, new Videojuego(2,
                    "Lost Ark", "Smilegate Games", "MMORPG", 25.00, 4));
            videojuegos.put(3, new Videojuego(3,
                    "League of Legends", "Rito Games", "MOBA", 5.00, 3));
        }
    }

    @Override
    public List<Videojuego> buscaTodos() {
        return videojuegos.values().stream().collect(Collectors.toList());
    }

    @Override
    public Videojuego buscaID(int id) {
        return videojuegos.get(id);
    }

    @Override
    public boolean crea(Videojuego v) {
        Videojuego nv=new Videojuego(v);
        nv.setId(idVideojuego);
        videojuegos.put(idVideojuego, nv);
        v.setId(idVideojuego);
        idVideojuego++;
        return true;
    }

    @Override
    public boolean edita(Videojuego v) {
        boolean result=false;
        if (videojuegos.containsKey(v.getId())) {
            Videojuego nv=new Videojuego(v);
            videojuegos.replace(v.getId(),nv);
            result=true;
        }
        return result;
    }

    @Override
    public boolean borra(Integer id) {
        boolean result=false;
        if (videojuegos.containsKey(id)) {
            videojuegos.remove(id);
            result = true;
        }
        return result;
    }

    public int numVideojuegos() {
        return videojuegos.size();
    }
}