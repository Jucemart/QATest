import entities.Evaluado;
import entities.Evaluador;
import services.EvaluadoService;
import services.EvaluadorService;

public class Main {
    public static void main(String[] args) {
        EvaluadoService evaluadoSrv = new EvaluadoService();
        EvaluadorService evaluadorSrv = new EvaluadorService();

        Evaluado candidato = new Evaluado("C-001", "Ana", "López", "ana@correo.com", "Analista Junior");
        Evaluador tecnico  = new Evaluador("EV-01", "Carlos", "Méndez", "carlos@laboratorio.com", "Psicometría");

        evaluadoSrv.registrar(candidato);
        evaluadorSrv.registrar(tecnico);

        // 1) Asignación y realización
        evaluadorSrv.asignarYMarcarExamen(tecnico, candidato, "Raven");
        // 2) Evaluación y calificación
        evaluadorSrv.evaluarYCalificar(tecnico, candidato, "Raven", 88);
        // 3) Consulta de resultado
        candidato.consultarResultado("Raven");

        // Presentaciones
        System.out.println(tecnico.presentarse());
        System.out.println(candidato.presentarse());

        // Bitácora del evaluador
        System.out.println("Bitácora: " + tecnico.getBitacora());
    }
}