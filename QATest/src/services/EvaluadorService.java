package services;
import entities.Evaluado;
import entities.Evaluador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluadorService {

    private final Map<String, Evaluador> store = new HashMap<>();

    public void registrar(Evaluador ev) { store.put(ev.getId(), ev); }

    public Evaluador obtenerPorId(String id) { return store.get(id); }

    public List<Evaluador> listar() { return new ArrayList<>(store.values()); }

    public boolean eliminar(String id) { return store.remove(id) != null; }

    // ====== Operaciones de coordinación Evaluador ↔ Evaluado ======

    public void asignarYMarcarExamen(Evaluador evaluador, Evaluado evaluado, String examen) {
        evaluador.asignarExamen(evaluado.getId(), examen);
        evaluado.realizarExamen(examen);
    }

    public void evaluarYCalificar(Evaluador evaluador, Evaluado evaluado, String examen, int nota) {
        evaluador.evaluar(evaluado.getId(), examen);
        evaluador.calificar(evaluado.getId(), examen, nota);
        evaluado.registrarNota(examen, nota);
    }
}