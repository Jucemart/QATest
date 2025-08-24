package services;
import entities.Evaluado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluadoService {

    private final Map<String, Evaluado> store = new HashMap<>();

    public void registrar(Evaluado e) { store.put(e.getId(), e); }

    public Evaluado obtenerPorId(String id) { return store.get(id); }

    public List<Evaluado> listar() { return new ArrayList<>(store.values()); }

    public boolean eliminar(String id) { return store.remove(id) != null; }
}