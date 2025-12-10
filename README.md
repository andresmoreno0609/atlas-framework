# 🚀 Atlas Framework
**Atlas** es un framework liviano construido en Java + Spring Boot, diseñado para acelerar el desarrollo de microservicios mediante patrones estandarizados, casos de uso avanzados, repositorios genéricos, paginación dinámica, filtros automáticos y controladores CRUD reutilizables.

Atlas busca reducir boilerplate, mantener una arquitectura limpia y ofrecer componentes reutilizables para construir servicios consistentes, robustos y fáciles de mantener.

---

## ✨ Características principales

### ✔️ **1. UseCaseAdvance**
Un patrón estandarizado para estructurar casos de uso con flujo:
- `preConditions()`
- `core()`
- `postConditions()`

Permite:
- Validaciones previas
- Lógica central desacoplada
- Acciones posteriores (logs, auditoría)
- Manejo centralizado de excepciones

Sin tipos genéricos obligatorios: cada adaptador define sus propios DTOs.

---

### ✔️ **2. BaseRepository**
Un repositorio genérico basado en `JpaRepository`, que incluye:
- Métodos CRUD comunes
- Operaciones extendidas
- Paginación dinámica
- Sort automático
- Compatibilidad con `PageQuery` y `FilterQuery`

---

### ✔️ **3. DynamicSpecifications**
Motor dinámico para construir consultas usando:
- Filtros con operadores (Equals, Like, Between, In…)
- Combinación flexible AND / OR
- Integración completa con el paginado de Spring

Permite implementar filtros avanzados sin código repetitivo.

---

### ✔️ **4. PageQuery / PageFilterQuery / PageResult**
Estructuras estandarizadas para manejar:
- Paginación
- Filtros
- Ordenamiento
- Resultados de forma consistente en todos los servicios

---

### ✔️ **5. CrudController Base**
Controlador genérico que expone:
- `create()`
- `update()`
- `delete()`
- `findById()`
- `findAll()` (con paginado + filtros)

Los adaptadores solo deben extenderlo y configurar su UseCase correspondiente.

---

## 📦 Arquitectura del Framework

