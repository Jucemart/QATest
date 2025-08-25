package interfaces;

/**
 * AUTOR: Julio Martínez jmaritnezm45@miumg.edu.gt
 * FECHA: 2025
 * DESCRIPCIÓN: Interfaz que define los métodos para gestionar exámenes
 */

public interface GestionExamen {
    
    void crearExamen(String id, String nombre, String descripcion, int puntajeMaximo, 
                     int puntajeMinimoAprobacion, String instrucciones);
    
    void configurarExamen(String id, int puntajeMaximo, int puntajeMinimoAprobacion, String instrucciones);
    
    void activarExamen(String id);
    
    void desactivarExamen(String id);
    
    boolean validarExamen(String id);
    
    String obtenerInformacionExamen(String id);
}
