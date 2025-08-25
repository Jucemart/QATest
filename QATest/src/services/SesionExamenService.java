package services;

/**
 * AUTOR: Julio Martínez jmaritnezm45@miumg.edu.gt
 * FECHA: 2025
 * DESCRIPCIÓN: Servicio para gestionar las sesiones de examen y su ciclo de vida
 */

import entities.SesionExamen;
import entities.Examen;
import entities.Evaluado;
import entities.Evaluador;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SesionExamenService {
    
    private final Map<String, SesionExamen> store = new HashMap<>();
    
    public void registrar(SesionExamen sesion) { 
        store.put(sesion.getId(), sesion); 
    }
    
    public SesionExamen obtenerPorId(String id) { 
        return store.get(id); 
    }
    
    public List<SesionExamen> listar() { 
        return new ArrayList<>(store.values()); 
    }
    
    public List<SesionExamen> listarActivas() {
        return store.values().stream()
                   .filter(SesionExamen::isActivo)
                   .collect(java.util.stream.Collectors.toList());
    }
    
    public boolean eliminar(String id) { 
        return store.remove(id) != null; 
    }
    
    public void activar(String id) {
        SesionExamen sesion = store.get(id);
        if (sesion != null) {
            sesion.setActivo(true);
        }
    }
    
    public void desactivar(String id) {
        SesionExamen sesion = store.get(id);
        if (sesion != null) {
            sesion.setActivo(false);
        }
    }
    
    // Métodos de gestión de sesiones
    public void iniciarSesion(String id) {
        SesionExamen sesion = store.get(id);
        if (sesion != null && sesion.isActivo()) {
            sesion.iniciarSesion();
        }
    }
    
    public void completarSesion(String id) {
        SesionExamen sesion = store.get(id);
        if (sesion != null && sesion.isActivo()) {
            sesion.completarSesion();
        }
    }
    
    public void calificarSesion(String id, int puntaje, String observaciones) {
        SesionExamen sesion = store.get(id);
        if (sesion != null && sesion.isActivo()) {
            sesion.calificarSesion(puntaje, observaciones);
        }
    }
    
    public void cancelarSesion(String id, String motivo) {
        SesionExamen sesion = store.get(id);
        if (sesion != null && sesion.isActivo()) {
            sesion.cancelarSesion(motivo);
        }
    }
    
    // Métodos de búsqueda
    public List<SesionExamen> buscarPorEvaluado(String evaluadoId) {
        return store.values().stream()
                   .filter(sesion -> sesion.isActivo() && 
                                   sesion.getEvaluado().getId().equals(evaluadoId))
                   .collect(java.util.stream.Collectors.toList());
    }
    
    public List<SesionExamen> buscarPorEvaluador(String evaluadorId) {
        return store.values().stream()
                   .filter(sesion -> sesion.isActivo() && 
                                   sesion.getEvaluador().getId().equals(evaluadorId))
                   .collect(java.util.stream.Collectors.toList());
    }
    
    public List<SesionExamen> buscarPorEstado(SesionExamen.EstadoSesion estado) {
        return store.values().stream()
                   .filter(sesion -> sesion.isActivo() && 
                                   sesion.getEstado() == estado)
                   .collect(java.util.stream.Collectors.toList());
    }
    
    public List<SesionExamen> buscarPorExamen(String examenId) {
        return store.values().stream()
                   .filter(sesion -> sesion.isActivo() && 
                                   sesion.getExamen().getId().equals(examenId))
                   .collect(java.util.stream.Collectors.toList());
    }
    
    // Métodos de consulta
    public String obtenerEstadoSesion(String id) {
        SesionExamen sesion = store.get(id);
        if (sesion == null) return "Sesión no encontrada";
        return sesion.getEstado().getDescripcion();
    }
    
    public String obtenerResultadoSesion(String id) {
        SesionExamen sesion = store.get(id);
        if (sesion == null) return "Sesión no encontrada";
        return sesion.obtenerResultado();
    }
    
    // Método para crear sesión completa
    public SesionExamen crearSesionCompleta(String id, String nombre, String descripcion, 
                                           Examen examen, Evaluado evaluado, Evaluador evaluador) {
        SesionExamen sesion = new SesionExamen(id, nombre, descripcion, examen, evaluado, evaluador);
        store.put(id, sesion);
        return sesion;
    }
}
