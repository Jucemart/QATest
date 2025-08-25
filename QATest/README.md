# 🧠 QATest - Sistema de Gestión de Evaluaciones Psicométricas

[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://openjdk.java.net/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Status](https://img.shields.io/badge/Status-Production%20Ready-brightgreen.svg)](https://github.com/Jucemart/QATest)

## 📋 Descripción

**QATest** es un sistema robusto de gestión de evaluaciones psicométricas desarrollado en Java, diseñado para laboratorios y centros de evaluación psicológica. El sistema permite administrar, configurar y evaluar diferentes tipos de tests psicométricos de manera eficiente y estructurada.

## ✨ Características Principales

### 🔧 Sistema Base (Existente)
- **Gestión de Evaluadores**: Administración de psicólogos y técnicos especializados
- **Gestión de Evaluados**: Control de candidatos y sus perfiles
- **Sistema de Bitácoras**: Seguimiento completo de actividades de evaluación
- **Asignación de Exámenes**: Proceso automatizado de asignación y calificación

### 🆕 Sistema de Gestión de Exámenes (Nuevo)
- **Catálogo de Tipos de Examen**: Raven, MBTI, DISC, WAIS, 16PF, Bender
- **Configuración de Exámenes**: Puntajes, vigencia, instrucciones personalizadas
- **Gestión de Sesiones**: Control completo del ciclo de vida de evaluaciones
- **Estados de Sesión**: Pendiente → En Curso → Completado → Calificado
- **Reportes Avanzados**: Estadísticas, filtros y análisis por múltiples criterios

## 🏗️ Arquitectura del Sistema

```
QATest/
├── entities/                 # Entidades del dominio
│   ├── PersonaLaboratorio   # Clase base abstracta
│   ├── Evaluado            # Candidatos a evaluar
│   ├── Evaluador           # Psicólogos y técnicos
│   ├── EntidadEvaluacion   # Base para entidades de evaluación
│   ├── TipoExamen          # Catálogo de tipos de test
│   ├── Examen              # Exámenes específicos
│   └── SesionExamen        # Sesiones de evaluación
├── interfaces/              # Contratos del sistema
│   ├── AsignarExamen       # Gestión de asignaciones
│   ├── Evaluado            # Operaciones de evaluados
│   ├── GestionExamen       # Gestión de exámenes
│   └── GestionSesion       # Gestión de sesiones
├── services/                # Lógica de negocio
│   ├── EvaluadoService     # Servicios de evaluados
│   ├── EvaluadorService    # Servicios de evaluadores
│   ├── TipoExamenService   # Gestión de tipos
│   ├── ExamenService       # Gestión de exámenes
│   └── SesionExamenService # Gestión de sesiones
└── Main.java               # Programa principal
```

## 🚀 Funcionalidades Destacadas

### 📊 Gestión de Exámenes
- Crear y configurar diferentes tipos de tests psicométricos
- Definir puntajes mínimos de aprobación
- Control de vigencia automática (6 meses por defecto)
- Validaciones de integridad de datos

### 🎯 Control de Sesiones
- Estados bien definidos del proceso de evaluación
- Control de fechas de inicio y finalización
- Sistema de calificación con observaciones
- Cálculo automático de duración

### 🔍 Búsquedas Avanzadas
- Filtrado por evaluador, evaluado, estado
- Búsqueda por tipo de examen y área
- Consultas por vigencia y puntajes
- Reportes estadísticos completos

## 🛠️ Tecnologías Utilizadas

- **Java 11+**: Lenguaje principal del proyecto
- **Programación Orientada a Objetos**: Herencia, interfaces, polimorfismo
- **Streams API**: Procesamiento eficiente de colecciones
- **Enums**: Estados bien definidos del sistema
- **LocalDateTime**: Manejo moderno de fechas y horas

## 📦 Instalación y Uso

### Prerrequisitos
- Java 11 o superior
- IDE compatible (IntelliJ IDEA, Eclipse, VS Code)

### Compilación
```bash
# Compilar todas las clases
javac -encoding UTF-8 src/entities/*.java src/interfaces/*.java src/services/*.java src/*.java

# Ejecutar programa principal
java -cp src Main

# Ejecutar demo avanzado
java -cp src DemoSistemaExamenes
```

### Ejecución en IntelliJ IDEA
1. Abrir el proyecto en IntelliJ IDEA
2. Configurar SDK Java 11+
3. Ejecutar `Main.java` para funcionalidad básica
4. Ejecutar `DemoSistemaExamenes.java` para demostración avanzada

## 📊 Ejemplo de Uso

```java
// Crear tipo de examen
TipoExamen raven = new TipoExamen("TE-001", "Raven", 
    "Test de Matrices Progresivas", "Inteligencia", "Cognitivo", 45);

// Crear examen específico
Examen raven2024 = new Examen("E-001", "Raven 2024", 
    "Test Raven versión 2024", raven, 100, 70, 
    "Resuelve las matrices progresivas");

// Crear sesión de evaluación
SesionExamen sesion = new SesionExamen("S-001", "Sesión Raven Ana", 
    "Evaluación de Ana en Raven", raven2024, candidato, evaluador);

// Proceso completo de evaluación
sesion.iniciarSesion();
sesion.completarSesion();
sesion.calificarSesion(88, "Excelente razonamiento abstracto");
```

## 📈 Estado del Proyecto

- ✅ **Sistema Base**: Completamente funcional
- ✅ **Sistema de Exámenes**: Implementado y probado
- ✅ **Integración**: Perfecta entre ambos sistemas
- ✅ **Testing**: Validado en IntelliJ IDEA
- ✅ **Documentación**: Comentarios de autoría completos

## 👥 Autores

- **Julio Martínez** - [jmaritnezm45@miumg.edu.gt](mailto:jmaritnezm45@miumg.edu.gt)
  - Implementación del Sistema de Gestión de Exámenes
  - Arquitectura de entidades y servicios
  - Sistema de reportes y estadísticas

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📞 Contacto

- **Repositorio**: [https://github.com/Jucemart/QATest](https://github.com/Jucemart/QATest)
- **Email**: [jmaritnezm45@miumg.edu.gt](mailto:jmaritnezm45@miumg.edu.gt)

---

⭐ **¡Dale una estrella al proyecto si te fue útil!**
