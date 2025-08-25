package entities;

/**
 * AUTOR: Julio Martínez jmaritnezm45@miumg.edu.gt
 * FECHA: 2024
 * DESCRIPCIÓN: Entidad que representa un examen específico basado en un tipo
 */

import java.time.LocalDateTime;

public class Examen extends EntidadEvaluacion {
    
    private TipoExamen tipoExamen;
    private int puntajeMaximo;
    private int puntajeMinimoAprobacion;
    private LocalDateTime fechaVigencia;
    private String instrucciones;
    
    public Examen(String id, String nombre, String descripcion, TipoExamen tipoExamen, 
                  int puntajeMaximo, int puntajeMinimoAprobacion, String instrucciones) {
        super(id, nombre, descripcion);
        this.tipoExamen = tipoExamen;
        this.puntajeMaximo = puntajeMaximo;
        this.puntajeMinimoAprobacion = puntajeMinimoAprobacion;
        this.instrucciones = instrucciones;
        this.fechaVigencia = LocalDateTime.now().plusMonths(6); // 6 meses por defecto
    }
    
    // Getters y Setters específicos
    public TipoExamen getTipoExamen() { return tipoExamen; }
    public void setTipoExamen(TipoExamen tipoExamen) { this.tipoExamen = tipoExamen; }
    
    public int getPuntajeMaximo() { return puntajeMaximo; }
    public void setPuntajeMaximo(int puntajeMaximo) { this.puntajeMaximo = puntajeMaximo; }
    
    public int getPuntajeMinimoAprobacion() { return puntajeMinimoAprobacion; }
    public void setPuntajeMinimoAprobacion(int puntajeMinimoAprobacion) { 
        this.puntajeMinimoAprobacion = puntajeMinimoAprobacion; 
    }
    
    public LocalDateTime getFechaVigencia() { return fechaVigencia; }
    public void setFechaVigencia(LocalDateTime fechaVigencia) { this.fechaVigencia = fechaVigencia; }
    
    public String getInstrucciones() { return instrucciones; }
    public void setInstrucciones(String instrucciones) { this.instrucciones = instrucciones; }
    
    @Override
    public String obtenerTipo() {
        return "Examen";
    }
    
    public boolean estaVigente() {
        return LocalDateTime.now().isBefore(fechaVigencia);
    }
    
    public boolean esAprobado(int puntaje) {
        return puntaje >= puntajeMinimoAprobacion;
    }
    
    public String obtenerEstadoVigencia() {
        if (estaVigente()) {
            return "Vigente hasta: " + fechaVigencia.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        } else {
            return "Expirado desde: " + fechaVigencia.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        }
    }
}
