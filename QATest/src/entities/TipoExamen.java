package entities;

/**
 * AUTOR: Julio Martínez jmaritnezm45@miumg.edu.gt
 * FECHA: 2025
 * DESCRIPCIÓN: Entidad que representa los tipos de exámenes psicométricos
 */

public class TipoExamen extends EntidadEvaluacion {
    
    private String areaAplicacion;
    private String categoria;
    private int tiempoEstimado; // en minutos
    
    public TipoExamen(String id, String nombre, String descripcion, String areaAplicacion, String categoria, int tiempoEstimado) {
        super(id, nombre, descripcion);
        this.areaAplicacion = areaAplicacion;
        this.categoria = categoria;
        this.tiempoEstimado = tiempoEstimado;
    }
    
    // Getters y Setters específicos
    public String getAreaAplicacion() { return areaAplicacion; }
    public void setAreaAplicacion(String areaAplicacion) { this.areaAplicacion = areaAplicacion; }
    
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    
    public int getTiempoEstimado() { return tiempoEstimado; }
    public void setTiempoEstimado(int tiempoEstimado) { this.tiempoEstimado = tiempoEstimado; }
    
    @Override
    public String obtenerTipo() {
        return "Tipo de Examen";
    }
    
    public String obtenerInformacionCompleta() {
        return String.format("%s - %s (%s) - Tiempo: %d min", 
            nombre, areaAplicacion, categoria, tiempoEstimado);
    }
}
