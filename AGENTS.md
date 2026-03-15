# AGENTS.md — Copilot Coding Agent Instructions

Este archivo es leído por el **Copilot Coding Agent autónomo** (y otras IAs como Claude Code, Cursor)
al trabajar en issues y PRs de forma autónoma. Define cómo operar en este repositorio.

## Contexto del Proyecto

Repositorio de templates y agentes para generar microservicios Java con:
- **Lenguaje**: Java 21 (LTS)
- **Framework**: Spring Boot 3.x
- **Arquitectura**: Hexagonal (Ports & Adapters)
- **API**: OpenAPI 3.0 / Swagger (contract-first)
- **Build**: Maven
- **Calidad**: JaCoCo + SonarQube + Checkstyle

## Comandos del Proyecto

### Compilar
```bash
mvn clean compile
```

### Ejecutar Tests
```bash
mvn clean test
```

### Compilar + Tests + Cobertura
```bash
mvn clean verify
```

### Análisis SonarQube
```bash
mvn clean verify sonar:sonar \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=${SONAR_TOKEN}
```

### Solo Checkstyle
```bash
mvn checkstyle:check
```

## Reglas Operativas para el Agente

### El agente DEBE:
- Seguir siempre la arquitectura hexagonal definida en `docs/hexagonal-architecture.md`
- Generar tests para cada clase creada o modificada
- Mantener cobertura de líneas >= 80% (verificar con JaCoCo)
- Aplicar las reglas SOLID de `docs/platform-rules/solid-principles.md`
- Usar constructor injection, nunca `@Autowired` en campo
- Usar `Optional<T>` en lugar de retornar `null`

### El agente NO DEBE:
- Modificar archivos en `src/main/resources/application-prod.yml`
- Hacer commit directamente a `main` o `master`
- Eliminar tests existentes
- Usar field injection (`@Autowired` sobre campo)
- Importar clases de Spring/JPA en la capa `domain/model/`
- Hardcodear secrets o credenciales

## Estructura de Branches
- `main` → rama protegida, solo mediante PR
- `feature/{nombre}` → nuevas funcionalidades
- `fix/{nombre}` → correcciones

## Definition of Done
Antes de crear un PR, verificar:
- [ ] `mvn clean verify` pasa sin errores
- [ ] Cobertura JaCoCo >= 80%
- [ ] `mvn checkstyle:check` sin violaciones
- [ ] Todos los métodos públicos tienen tests
- [ ] No hay `TODO` pendientes en el código generado