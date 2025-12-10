package com.atlas.framework.core.usecase;


/**
 * Caso de uso avanzado para el framework Atlas.
 *
 * Flujo:
 * 1. preConditions() → validaciones, permisos, integridad.
 * 2. core() → lógica principal del caso de uso.
 * 3. postConditions() → auditoría, logs, cálculos derivados.
 *
 * Cada caso de uso define sus propios DTOs.
 */
public abstract class UseCaseAdvance {

    /**
     * Método principal que ejecuta el flujo completo del caso de uso.
     *
     * @param request objeto que contiene los datos necesarios para ejecutar el caso de uso.
     * @return resultado del caso de uso (puede ser un DTO o null).
     */
    public final Object execute(Object request) {
        try {
            preConditions(request);
            Object response = core(request);
            postConditions(response);
            return response;

        } catch (Exception ex) {
            handleException(ex);
            throw ex; // Para que el Adaptador/Controller lo capture
        }
    }

    /**
     * Validaciones previas, verificaciones de permisos, existencia de datos,
     * o cualquier regla previa antes del core.
     */
    protected void preConditions(Object request) {
        // Implementación opcional
    }

    /**
     * Lógica principal del caso de uso.
     * Este método es obligatorio.
     */
    protected abstract Object core(Object request);

    /**
     * Acciones posteriores al core.
     * Auditar, registrar logs, enviar notificaciones, etc.
     */
    protected void postConditions(Object response) {
        // Implementación opcional
    }

    /**
     * Manejo centralizado de excepciones del caso de uso.
     * Se puede extender para logs o métricas.
     */
    protected void handleException(Exception ex) {
        System.out.println("Atlas UseCase Error → " + ex.getMessage());
    }
}
