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

atlas-framework/
 ├── repository/
 │    └── BaseRepository.java
 │
 ├── specification/
 │    ├── DynamicSpecifications.java
 │    └── FilterOperator.java
 │
 ├── pagination/
 │    ├── PageQuery.java
 │    ├── PageFilterQuery.java
 │    └── PageResult.java
 │
 ├── usecase/
 │    └── UseCaseAdvance.java
 │
 ├── controller/
 │    └── CrudController.java
 │
 └── config/
      └── (Próximamente AutoConfiguration)

---

## 🧠 Patrón de Casos de Uso – UseCaseAdvance

Ejemplo:

```java
public class CreateCustomerUseCase extends UseCaseAdvance {

    @Override
    protected void preConditions(Object request) {
        // Validaciones previas…
    }

    @Override
    protected Object core(Object request) {
        // Lógica de negocio principal…
        return result;
    }

    @Override
    protected void postConditions(Object response) {
        // Auditoría, logs, notificaciones…
    }
}
```

---

## 🔌 Adaptadores

Los adaptadores sirven como puente entre las entradas (HTTP, mensajería, cron jobs) y el UseCase:

```java
@Component
public class CreateCustomerAdapter {

    private final CreateCustomerUseCase useCase;

    public CreateCustomerAdapter(CreateCustomerUseCase useCase) {
        this.useCase = useCase;
    }

    public Customer execute(CreateCustomerRequest request) {
        return (Customer) useCase.execute(request);
    }
}
```

---

## 🌐 Controladores CRUD

```java
@RestController
@RequestMapping("/customers")
public class CustomerController extends CrudController<
        CreateCustomerRequest,
        UpdateCustomerRequest,
        CustomerResponse
> {}
```

---

## 🛠 Instalación

1. Agregar el módulo al proyecto (Maven / Gradle)
2. Registrar los UseCases y Adaptadores
3. Extender los controladores desde `CrudController`
4. Usar `DynamicSpecifications` para filtros avanzados
5. Usar `PageQuery` y `PageResult` para paginado

---

## 📄 Estado del Proyecto

Versión Estable Inicial ✔  
Incluye:
- Repositorios genéricos
- Motor de filtros dinámicos
- Paginación estandarizada
- CRUD Controller
- UseCaseAdvance

---

## 🧭 Roadmap

### 🟩 **Versión 1.1**
- AccessControl (Roles / Permisos / Reglas)
- `@RequirePermission`
- Error Handler global
- Respuesta estándar AtlasResponse

### 🟧 **Versión 1.2**
- AutoConfiguration para Spring Boot
- Starter Maven oficial

### 🟦 **Versión 1.3**
- Auditoría automática
- Métricas
- Multi-tenancy opcional

---

## 👨‍💻 Autor
**Andrés Raúl Moreno López**  
Senior Java Developer | Arquitectura, microservicios y soluciones escalables.

---

## 📜 Licencia
MIT License
