package entities;

/**
 * AUTOR: Julio Martínez jmaritnezm45@miumg.edu.gt
 * FECHA: 2025
 * DESCRIPCIÓN: Clase abstracta base para entidades relacionadas con evaluaciones
 */

import java.time.LocalDateTime;

public abstract class EntidadEvaluacion {
    
    protected String id;
    protected String nombre;
    protected String descripcion;
    protected LocalDateTime fechaCreacion;
    protected boolean activo;
    
    public EntidadEvaluacion(String id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = LocalDateTime.now();
        this.activo = true;
    }
    
    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    
    // Métodos abstractos
    public abstract String obtenerTipo();
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id='" + id + "', nombre='" + nombre + "', activo=" + activo + "}";
    }
}
