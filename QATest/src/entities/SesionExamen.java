package entities;

/**
 * AUTOR: Julio Martínez jmaritnezm45@miumg.edu.gt
 * FECHA: 2025
 * DESCRIPCIÓN: Entidad que gestiona las sesiones de examen y su ciclo de vida
 */

import java.time.LocalDateTime;

public class SesionExamen extends EntidadEvaluacion {
    
    public enum EstadoSesion {
        PENDIENTE("Pendiente"),
        EN_CURSO("En Curso"),
        COMPLETADO("Completado"),
        CALIFICADO("Calificado"),
        CANCELADO("Cancelado");
        
        private final String descripcion;
        
        EstadoSesion(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() { return descripcion; }
    }
    
    private Examen examen;
    private Evaluado evaluado;
    private Evaluador evaluador;
    private EstadoSesion estado;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Integer puntajeObtenido;
    private String observaciones;
    
    public SesionExamen(String id, String nombre, String descripcion, Examen examen, 
                        Evaluado evaluado, Evaluador evaluador) {
        super(id, nombre, descripcion);
        this.examen = examen;
        this.evaluado = evaluado;
        this.evaluador = evaluador;
        this.estado = EstadoSesion.PENDIENTE;
        this.fechaInicio = null;
        this.fechaFin = null;
        this.puntajeObtenido = null;
        this.observaciones = "";
    }
    
    // Getters y Setters específicos
    public Examen getExamen() { return examen; }
    public void setExamen(Examen examen) { this.examen = examen; }
    
    public Evaluado getEvaluado() { return evaluado; }
    public void setEvaluado(Evaluado evaluado) { this.evaluado = evaluado; }
    
    public Evaluador getEvaluador() { return evaluador; }
    public void setEvaluador(Evaluador evaluador) { this.evaluador = evaluador; }
    
    public EstadoSesion getEstado() { return estado; }
    public void setEstado(EstadoSesion estado) { this.estado = estado; }
    
    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }
    
    public LocalDateTime getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDateTime fechaFin) { this.fechaFin = fechaFin; }
    
    public Integer getPuntajeObtenido() { return puntajeObtenido; }
    public void setPuntajeObtenido(Integer puntajeObtenido) { this.puntajeObtenido = puntajeObtenido; }
    
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    
    @Override
    public String obtenerTipo() {
        return "Sesión de Examen";
    }
    
    // Métodos de control de estado
    public void iniciarSesion() {
        if (estado == EstadoSesion.PENDIENTE) {
            this.estado = EstadoSesion.EN_CURSO;
            this.fechaInicio = LocalDateTime.now();
        }
    }
    
    public void completarSesion() {
        if (estado == EstadoSesion.EN_CURSO) {
            this.estado = EstadoSesion.COMPLETADO;
            this.fechaFin = LocalDateTime.now();
        }
    }
    
    public void calificarSesion(int puntaje, String observaciones) {
        if (estado == EstadoSesion.COMPLETADO) {
            this.estado = EstadoSesion.CALIFICADO;
            this.puntajeObtenido = Math.max(0, Math.min(examen.getPuntajeMaximo(), puntaje));
            this.observaciones = observaciones != null ? observaciones : "";
        }
    }
    
    public void cancelarSesion(String motivo) {
        this.estado = EstadoSesion.CANCELADO;
        this.observaciones = "Cancelado: " + (motivo != null ? motivo : "Sin motivo especificado");
    }
    
    public String obtenerResultado() {
        if (estado == EstadoSesion.CALIFICADO && puntajeObtenido != null) {
            boolean aprobado = examen.esAprobado(puntajeObtenido);
            return String.format("Puntaje: %d/%d - %s", 
                puntajeObtenido, examen.getPuntajeMaximo(), 
                aprobado ? "APROBADO" : "REPROBADO");
        }
        return "Sin calificar";
    }
    
    public long obtenerDuracionMinutos() {
        if (fechaInicio != null && fechaFin != null) {
            return java.time.Duration.between(fechaInicio, fechaFin).toMinutes();
        }
        return 0;
    }
}
