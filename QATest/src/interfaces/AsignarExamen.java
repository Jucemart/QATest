package interfaces;

public interface AsignarExamen {

    void asignarExamen(String evaludoId, String examen);

    void evaluar(String evaluado, String examen);

    void calificar(String evaludoId, String examen, int nota);

    String presentarse();
}
