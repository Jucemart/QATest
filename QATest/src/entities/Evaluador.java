package entities;

import interfaces.AsignarExamen;

import java.util.ArrayList;
import java.util.List;

public class Evaluador extends PersonaLaboratorio implements AsignarExamen {

    private String area;
    private final List<String> bitacora = new ArrayList<>();

    public Evaluador(String id, String nombre, String apellido, String correo, String area) {
        super(id, nombre, apellido, correo);
        this.area = area;
    }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    @Override
    public void asignarExamen(String evaluadoId, String examen) {
        bitacora.add("Asignó examen '" + examen + "' a Evaluado#" + evaluadoId);
    }

    @Override
    public void evaluar(String evaluadoId, String examen) {
        bitacora.add("Evaluó desempeño en '" + examen + "' de Evaluado#" + evaluadoId);
    }

    @Override
    public void calificar(String evaluadoId, String examen, int nota) {
        int n = Math.max(0, Math.min(100, nota));
        bitacora.add("Calificó '" + examen + "' de Evaluado#" + evaluadoId + " con nota " + n);
    }

    @Override
    public String presentarse() {
        return "Evaluador: " + nombre + " " + apellido + " (Área: " + area + ")";
    }

    public List<String> getBitacora() { return bitacora; }
}
