package interfaces;

/**
 * AUTOR: Julio Martínez jmaritnezm45@miumg.edu.gt
 * FECHA: 2025
 * DESCRIPCIÓN: Interfaz que define los métodos para gestionar sesiones de examen
 */

public interface GestionSesion {
    
    void crearSesion(String id, String nombre, String descripcion);
    
    void iniciarSesion(String id);
    
    void completarSesion(String id);
    
    void calificarSesion(String id, int puntaje, String observaciones);
    
    void cancelarSesion(String id, String motivo);
    
    String obtenerEstadoSesion(String id);
    
    String obtenerResultadoSesion(String id);
}
