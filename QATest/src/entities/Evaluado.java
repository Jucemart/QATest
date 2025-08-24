package entities;

import java.util.HashMap;
import java.util.Map;

public class Evaluado extends PersonaLaboratorio implements interfaces.Evaluado {

    private String puestoAplicado;
    private final Map<String, Integer> examenes = new HashMap<>();

    public Evaluado(String id, String nombre, String apellido, String correo, String puestoAplicado) {
        super(id, nombre, apellido, correo);
        this.puestoAplicado = puestoAplicado;
    }

    public String getPuestoAplicado() { return puestoAplicado; }
    public void setPuestoAplicado(String puestoAplicado) { this.puestoAplicado = puestoAplicado; }

    @Override
    public void realizarExamen(String examen) {
        // Marca el examen como realizado pero aún sin calificar.
        examenes.put(examen, null);
        System.out.println(nombre + " realizó el examen: " + examen);
    }

    @Override
    public void consultarResultado(String examen) {
        Integer nota = examenes.get(examen);
        if (!examenes.containsKey(examen)) {
            System.out.println("No hay registro del examen '" + examen + "'.");
        } else if (nota == null) {
            System.out.println("El examen '" + examen + "' aún no ha sido calificado.");
        } else {
            System.out.println("Resultado de '" + examen + "': " + nota);
        }
    }

    @Override
    public String presentarse() {
        return "Evaluado: " + nombre + " " + apellido + " (Puesto: " + puestoAplicado + ")";
    }

    public void registrarNota(String examen, int nota) {
        if (examenes.containsKey(examen)) {
            examenes.put(examen, Math.max(0, Math.min(100, nota)));
        }
    }
}
