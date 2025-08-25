import entities.Evaluado;
import entities.Evaluador;
import entities.TipoExamen;
import entities.Examen;
import entities.SesionExamen;
import services.EvaluadoService;
import services.EvaluadorService;
import services.TipoExamenService;
import services.ExamenService;
import services.SesionExamenService;

public class Main {
    public static void main(String[] args) {
        // ====== SERVICIOS EXISTENTES ======
        EvaluadoService evaluadoSrv = new EvaluadoService();
        EvaluadorService evaluadorSrv = new EvaluadorService();
        
        // ====== NUEVOS SERVICIOS ======
        TipoExamenService tipoExamenSrv = new TipoExamenService();
        ExamenService examenSrv = new ExamenService();
        SesionExamenService sesionSrv = new SesionExamenService();

        // ====== CREACIÓN DE ENTIDADES EXISTENTES ======
        Evaluado candidato = new Evaluado("C-001", "Ana", "López", "ana@correo.com", "Analista Junior");
        Evaluador tecnico = new Evaluador("EV-01", "Carlos", "Méndez", "carlos@laboratorio.com", "Psicometría");

        evaluadoSrv.registrar(candidato);
        evaluadorSrv.registrar(tecnico);

        // ====== NUEVA FUNCIONALIDAD: SISTEMA DE EXÁMENES ======
        System.out.println("=== SISTEMA DE GESTIÓN DE EXÁMENES ===\n");
        
        // 1. Crear tipos de exámenes
        TipoExamen raven = new TipoExamen("TE-001", "Raven", "Test de Matrices Progresivas", 
                                         "Inteligencia", "Cognitivo", 45);
        TipoExamen bigFive = new TipoExamen("TE-002", "Big Five", "Test de Personalidad", 
                                           "Personalidad", "Psicométrico", 30);
        TipoExamen wisc = new TipoExamen("TE-003", "WISC", "Escala de Inteligencia Wechsler", 
                                        "Inteligencia", "Cognitivo", 90);
        
        tipoExamenSrv.registrar(raven);
        tipoExamenSrv.registrar(bigFive);
        tipoExamenSrv.registrar(wisc);
        
        System.out.println("Tipos de examen creados:");
        tipoExamenSrv.listar().forEach(tipo -> 
            System.out.println("  - " + tipo.obtenerInformacionCompleta()));
        
        // 2. Crear exámenes específicos
        Examen raven2024 = new Examen("E-001", "Raven 2024", "Test Raven versión 2024", 
                                     raven, 100, 70, "Resuelve las matrices progresivas");
        Examen bigFive2024 = new Examen("E-002", "Big Five 2024", "Test Big Five versión 2024", 
                                       bigFive, 50, 35, "Evalúa los 5 factores de personalidad");
        
        examenSrv.registrar(raven2024);
        examenSrv.registrar(bigFive2024);
        
        System.out.println("\nExámenes creados:");
        System.out.println(examenSrv.obtenerInformacionCompleta("E-001"));
        System.out.println(examenSrv.obtenerInformacionCompleta("E-002"));
        
        // 3. Crear sesiones de examen
        SesionExamen sesionRaven = sesionSrv.crearSesionCompleta("S-001", "Sesión Raven Ana", 
                                                                 "Evaluación de Ana en Raven", 
                                                                 raven2024, candidato, tecnico);
        SesionExamen sesionBigFive = sesionSrv.crearSesionCompleta("S-002", "Sesión Big Five Ana", 
                                                                   "Evaluación de Ana en Big Five", 
                                                                   bigFive2024, candidato, tecnico);
        
        System.out.println("\n=== PROCESO DE EVALUACIÓN COMPLETO ===");
        
        // 4. Proceso completo de evaluación Raven
        System.out.println("\n--- Evaluación Raven ---");
        System.out.println("Estado inicial: " + sesionSrv.obtenerEstadoSesion("S-001"));
        
        sesionSrv.iniciarSesion("S-001");
        System.out.println("Después de iniciar: " + sesionSrv.obtenerEstadoSesion("S-001"));
        
        sesionSrv.completarSesion("S-001");
        System.out.println("Después de completar: " + sesionSrv.obtenerEstadoSesion("S-001"));
        
        sesionSrv.calificarSesion("S-001", 88, "Excelente razonamiento abstracto");
        System.out.println("Después de calificar: " + sesionSrv.obtenerEstadoSesion("S-001"));
        System.out.println("Resultado: " + sesionSrv.obtenerResultadoSesion("S-001"));
        
        // 5. Proceso completo de evaluación Big Five
        System.out.println("\n--- Evaluación Big Five ---");
        System.out.println("Estado inicial: " + sesionSrv.obtenerEstadoSesion("S-002"));
        
        sesionSrv.iniciarSesion("S-002");
        sesionSrv.completarSesion("S-002");
        sesionSrv.calificarSesion("S-002", 42, "Perfil equilibrado en los 5 factores");
        
        System.out.println("Estado final: " + sesionSrv.obtenerEstadoSesion("S-002"));
        System.out.println("Resultado: " + sesionSrv.obtenerResultadoSesion("S-002"));
        
        // ====== FUNCIONALIDAD EXISTENTE (SIN MODIFICAR) ======
        System.out.println("\n=== FUNCIONALIDAD EXISTENTE ===");
        
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
        
        // ====== DEMOSTRACIÓN DE NUEVAS FUNCIONALIDADES ======
        System.out.println("\n=== CONSULTAS AVANZADAS ===");
        
        System.out.println("\nSesiones de Ana:");
        sesionSrv.buscarPorEvaluado("C-001").forEach(sesion -> 
            System.out.println("  - " + sesion.getNombre() + " (" + sesion.getEstado().getDescripcion() + ")"));
        
        System.out.println("\nSesiones de Carlos:");
        sesionSrv.buscarPorEvaluador("EV-01").forEach(sesion -> 
            System.out.println("  - " + sesion.getNombre() + " (" + sesion.getEstado().getDescripcion() + ")"));
        
        System.out.println("\nExámenes vigentes:");
        examenSrv.listarVigentes().forEach(examen -> 
            System.out.println("  - " + examen.getNombre() + " - " + examen.obtenerEstadoVigencia()));
        
        System.out.println("\nTipos de examen por área 'Inteligencia':");
        tipoExamenSrv.buscarPorArea("Inteligencia").forEach(tipo -> 
            System.out.println("  - " + tipo.getNombre() + " (" + tipo.getCategoria() + ")"));
    }
}