package services;

/**
 * AUTOR: Julio Martínez jmaritnezm45@miumg.edu.gt
 * FECHA: 2024
 * DESCRIPCIÓN: Servicio para gestionar los exámenes y sus validaciones
 */

import entities.Examen;
import entities.TipoExamen;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamenService {
    
    private final Map<String, Examen> store = new HashMap<>();
    
    public void registrar(Examen examen) { 
        store.put(examen.getId(), examen); 
    }
    
    public Examen obtenerPorId(String id) { 
        return store.get(id); 
    }
    
    public List<Examen> listar() { 
        return new ArrayList<>(store.values()); 
    }
    
    public List<Examen> listarActivos() {
        return store.values().stream()
                   .filter(Examen::isActivo)
                   .collect(java.util.stream.Collectors.toList());
    }
    
    public List<Examen> listarVigentes() {
        return store.values().stream()
                   .filter(examen -> examen.isActivo() && examen.estaVigente())
                   .collect(java.util.stream.Collectors.toList());
    }
    
    public boolean eliminar(String id) { 
        return store.remove(id) != null; 
    }
    
    public void activar(String id) {
        Examen examen = store.get(id);
        if (examen != null) {
            examen.setActivo(true);
        }
    }
    
    public void desactivar(String id) {
        Examen examen = store.get(id);
        if (examen != null) {
            examen.setActivo(false);
        }
    }
    
    public List<Examen> buscarPorTipo(String tipoExamenId) {
        return store.values().stream()
                   .filter(examen -> examen.isActivo() && 
                                   examen.getTipoExamen().getId().equals(tipoExamenId))
                   .collect(java.util.stream.Collectors.toList());
    }
    
    public List<Examen> buscarPorPuntajeMinimo(int puntajeMinimo) {
        return store.values().stream()
                   .filter(examen -> examen.isActivo() && 
                                   examen.getPuntajeMinimoAprobacion() <= puntajeMinimo)
                   .collect(java.util.stream.Collectors.toList());
    }
    
    public boolean validarExamen(String id) {
        Examen examen = store.get(id);
        if (examen == null) return false;
        
        return examen.isActivo() && 
               examen.estaVigente() && 
               examen.getPuntajeMaximo() > 0 &&
               examen.getPuntajeMinimoAprobacion() >= 0 &&
               examen.getPuntajeMinimoAprobacion() <= examen.getPuntajeMaximo();
    }
    
    public String obtenerInformacionCompleta(String id) {
        Examen examen = store.get(id);
        if (examen == null) return "Examen no encontrado";
        
        return String.format("Examen: %s\nTipo: %s\nPuntaje: %d-%d\nEstado: %s\n%s", 
            examen.getNombre(),
            examen.getTipoExamen().getNombre(),
            examen.getPuntajeMinimoAprobacion(),
            examen.getPuntajeMaximo(),
            examen.isActivo() ? "Activo" : "Inactivo",
            examen.obtenerEstadoVigencia());
    }
}
