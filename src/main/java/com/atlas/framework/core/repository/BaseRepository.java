package com.atlas.framework.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Repositorio base de Atlas.
 *
 * Extiende JpaRepository y JpaSpecificationExecutor para soportar
 * operaciones CRUD y consultas con Specifications.
 *
 * @param <T>  Tipo de la entidad.
 * @param <ID> Tipo del identificador.
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
