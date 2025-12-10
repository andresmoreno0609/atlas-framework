package com.atlas.framework.core.mapping;

import org.mapstruct.MappingTarget;

public interface CrudMapper<E, REQ, RES> {

    /**
     * Convierte DTO de Request → Entity (nuevo)
     */
    E toEntity(REQ request);

    /**
     * Actualiza una entidad existente combinando los datos del DTO Request
     */
    void updateEntity(REQ request, @MappingTarget E entity);

    /**
     * Convierte Entity → DTO Response
     */
    RES toResponse(E entity);
}
