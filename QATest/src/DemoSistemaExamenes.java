/**
 * AUTOR: Julio Martínez jmaritnezm45@miumg.edu.gt
 * FECHA: 2025
 * DESCRIPCIÓN: Clase de demostración avanzada del sistema de gestión de exámenes
 */

import entities.*;
import services.*;

public class DemoSistemaExamenes {
    
    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN AVANZADA DEL SISTEMA DE EXÁMENES ===\n");
        
        // Inicializar servicios
        TipoExamenService tipoSrv = new TipoExamenService();
        ExamenService examenSrv = new ExamenService();
        SesionExamenService sesionSrv = new SesionExamenService();
        EvaluadoService evaluadoSrv = new EvaluadoService();
        EvaluadorService evaluadorSrv = new EvaluadorService();
        
        // Crear evaluadores y evaluados
        Evaluador psicologo1 = new Evaluador("EV-02", "María", "García", "maria@lab.com", "Psicología Clínica");
        Evaluador psicologo2 = new Evaluador("EV-03", "Roberto", "Silva", "roberto@lab.com", "Recursos Humanos");
        
        Evaluado candidato1 = new Evaluado("C-002", "Juan", "Pérez", "juan@email.com", "Desarrollador Senior");
        Evaluado candidato2 = new Evaluado("C-003", "Laura", "Martínez", "laura@email.com", "Gerente de Proyecto");
        
        evaluadorSrv.registrar(psicologo1);
        evaluadorSrv.registrar(psicologo2);
        evaluadoSrv.registrar(candidato1);
        evaluadoSrv.registrar(candidato2);
        
        // Crear catálogo de tipos de examen
        crearCatalogoTipos(tipoSrv);
        
        // Crear exámenes específicos
        crearExamenesEspecificos(tipoSrv, examenSrv);
        
        // Simular múltiples evaluaciones
        simularEvaluaciones(evaluadoSrv, evaluadorSrv, examenSrv, sesionSrv);
        
        // Mostrar reportes y estadísticas
        mostrarReportes(tipoSrv, examenSrv, sesionSrv);
    }
    
    private static void crearCatalogoTipos(TipoExamenService tipoSrv) {
        System.out.println("--- CREANDO CATÁLOGO DE TIPOS DE EXAMEN ---");
        
        TipoExamen[] tipos = {
            new TipoExamen("TE-004", "MBTI", "Indicador de Tipo Myers-Briggs", "Personalidad", "Psicométrico", 25),
            new TipoExamen("TE-005", "DISC", "Perfil de Comportamiento DISC", "Personalidad", "Psicométrico", 20),
            new TipoExamen("TE-006", "16PF", "16 Factores de Personalidad", "Personalidad", "Psicométrico", 40),
            new TipoExamen("TE-007", "WAIS", "Escala de Inteligencia Adultos Wechsler", "Inteligencia", "Cognitivo", 120),
            new TipoExamen("TE-008", "Bender", "Test Gestáltico Visomotor", "Neuropsicología", "Cognitivo", 15)
        };
        
        for (TipoExamen tipo : tipos) {
            tipoSrv.registrar(tipo);
            System.out.println("✓ " + tipo.getNombre() + " registrado");
        }
        System.out.println();
    }
    
    private static void crearExamenesEspecificos(TipoExamenService tipoSrv, ExamenService examenSrv) {
        System.out.println("--- CREANDO EXAMENES ESPECÍFICOS ---");
        
        TipoExamen mbti = tipoSrv.obtenerPorId("TE-004");
        TipoExamen disc = tipoSrv.obtenerPorId("TE-005");
        TipoExamen wais = tipoSrv.obtenerPorId("TE-007");
        
        Examen[] examenes = {
            new Examen("E-003", "MBTI 2024", "Test MBTI versión 2024", mbti, 100, 60, "Identifica tu tipo de personalidad"),
            new Examen("E-004", "DISC 2024", "Test DISC versión 2024", disc, 80, 50, "Evalúa tu estilo de comportamiento"),
            new Examen("E-005", "WAIS 2024", "Test WAIS versión 2024", wais, 150, 90, "Mide tu coeficiente intelectual")
        };
        
        for (Examen examen : examenes) {
            examenSrv.registrar(examen);
            System.out.println("✓ " + examen.getNombre() + " creado");
        }
        System.out.println();
    }
    
    private static void simularEvaluaciones(EvaluadoService evaluadoSrv, EvaluadorService evaluadorSrv, 
                                          ExamenService examenSrv, SesionExamenService sesionSrv) {
        System.out.println("--- SIMULANDO EVALUACIONES MÚLTIPLES ---");
        
        Evaluado juan = evaluadoSrv.obtenerPorId("C-002");
        Evaluado laura = evaluadoSrv.obtenerPorId("C-003");
        Evaluador maria = evaluadorSrv.obtenerPorId("EV-02");
        Evaluador roberto = evaluadorSrv.obtenerPorId("EV-03");
        
        Examen mbti = examenSrv.obtenerPorId("E-003");
        Examen disc = examenSrv.obtenerPorId("E-004");
        Examen wais = examenSrv.obtenerPorId("E-005");
        
        // Evaluación de Juan
        SesionExamen sesionJuan1 = sesionSrv.crearSesionCompleta("S-003", "MBTI Juan", "Evaluación MBTI de Juan", mbti, juan, maria);
        sesionSrv.iniciarSesion("S-003");
        sesionSrv.completarSesion("S-003");
        sesionSrv.calificarSesion("S-003", 85, "Perfil INTJ muy marcado");
        
        SesionExamen sesionJuan2 = sesionSrv.crearSesionCompleta("S-004", "DISC Juan", "Evaluación DISC de Juan", disc, juan, roberto);
        sesionSrv.iniciarSesion("S-004");
        sesionSrv.completarSesion("S-004");
        sesionSrv.calificarSesion("S-004", 72, "Estilo analítico predominante");
        
        // Evaluación de Laura
        SesionExamen sesionLaura1 = sesionSrv.crearSesionCompleta("S-005", "MBTI Laura", "Evaluación MBTI de Laura", mbti, laura, maria);
        sesionSrv.iniciarSesion("S-005");
        sesionSrv.completarSesion("S-005");
        sesionSrv.calificarSesion("S-005", 78, "Perfil ENFJ equilibrado");
        
        SesionExamen sesionLaura2 = sesionSrv.crearSesionCompleta("S-006", "WAIS Laura", "Evaluación WAIS de Laura", wais, laura, maria);
        sesionSrv.iniciarSesion("S-006");
        sesionSrv.completarSesion("S-006");
        sesionSrv.calificarSesion("S-006", 125, "Inteligencia superior");
        
        System.out.println("✓ 4 evaluaciones simuladas completadas\n");
    }
    
    private static void mostrarReportes(TipoExamenService tipoSrv, ExamenService examenSrv, SesionExamenService sesionSrv) {
        System.out.println("--- REPORTES Y ESTADÍSTICAS ---");
        
        System.out.println("\n📊 ESTADÍSTICAS GENERALES:");
        System.out.println("  • Tipos de examen: " + tipoSrv.listar().size());
        System.out.println("  • Exámenes activos: " + examenSrv.listarActivos().size());
        System.out.println("  • Sesiones totales: " + sesionSrv.listar().size());
        System.out.println("  • Sesiones calificadas: " + sesionSrv.buscarPorEstado(SesionExamen.EstadoSesion.CALIFICADO).size());
        
        System.out.println("\n🔍 EXÁMENES POR CATEGORÍA:");
        System.out.println("  • Cognitivos: " + tipoSrv.buscarPorCategoria("Cognitivo").size());
        System.out.println("  • Psicométricos: " + tipoSrv.buscarPorCategoria("Psicométrico").size());
        
        System.out.println("\n📋 EXÁMENES POR ÁREA:");
        System.out.println("  • Inteligencia: " + tipoSrv.buscarPorArea("Inteligencia").size());
        System.out.println("  • Personalidad: " + tipoSrv.buscarPorArea("Personalidad").size());
        System.out.println("  • Neuropsicología: " + tipoSrv.buscarPorArea("Neuropsicología").size());
        
        System.out.println("\n✅ EXÁMENES VIGENTES:");
        examenSrv.listarVigentes().forEach(examen -> 
            System.out.println("  • " + examen.getNombre() + " - " + examen.obtenerEstadoVigencia()));
        
        System.out.println("\n🎯 SESIONES POR ESTADO:");
        for (SesionExamen.EstadoSesion estado : SesionExamen.EstadoSesion.values()) {
            int cantidad = sesionSrv.buscarPorEstado(estado).size();
            if (cantidad > 0) {
                System.out.println("  • " + estado.getDescripcion() + ": " + cantidad);
            }
        }
    }
}
