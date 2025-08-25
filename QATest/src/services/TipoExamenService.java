package services;

/**
 * AUTOR: Julio Martínez jmaritnezm45@miumg.edu.gt
 * FECHA: 2025
 * DESCRIPCIÓN: Servicio para gestionar los tipos de examen
 */

import entities.TipoExamen;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TipoExamenService {
    
    private final Map<String, TipoExamen> store = new HashMap<>();
    
    public void registrar(TipoExamen tipoExamen) { 
        store.put(tipoExamen.getId(), tipoExamen); 
    }
    
    public TipoExamen obtenerPorId(String id) { 
        return store.get(id); 
    }
    
    public List<TipoExamen> listar() { 
        return new ArrayList<>(store.values()); 
    }
    
    public List<TipoExamen> listarActivos() {
        return store.values().stream()
                   .filter(TipoExamen::isActivo)
                   .collect(java.util.stream.Collectors.toList());
    }
    
    public boolean eliminar(String id) { 
        return store.remove(id) != null; 
    }
    
    public void activar(String id) {
        TipoExamen tipo = store.get(id);
        if (tipo != null) {
            tipo.setActivo(true);
        }
    }
    
    public void desactivar(String id) {
        TipoExamen tipo = store.get(id);
        if (tipo != null) {
            tipo.setActivo(false);
        }
    }
    
    public List<TipoExamen> buscarPorArea(String area) {
        return store.values().stream()
                   .filter(tipo -> tipo.isActivo() && tipo.getAreaAplicacion().equalsIgnoreCase(area))
                   .collect(java.util.stream.Collectors.toList());
    }
    
    public List<TipoExamen> buscarPorCategoria(String categoria) {
        return store.values().stream()
                   .filter(tipo -> tipo.isActivo() && tipo.getCategoria().equalsIgnoreCase(categoria))
                   .collect(java.util.stream.Collectors.toList());
    }
}
