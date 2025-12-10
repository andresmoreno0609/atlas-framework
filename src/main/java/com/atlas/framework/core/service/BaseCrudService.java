package com.atlas.framework.core.service;

import com.atlas.framework.core.repository.BaseRepository;
import com.atlas.framework.core.mapping.CrudMapper;
import com.atlas.framework.core.filter.DynamicSpecifications;
import com.atlas.framework.core.filter.FilterCriteria;
import com.atlas.framework.core.pagination.PageMapper;
import com.atlas.framework.core.pagination.PageQuery;
import com.atlas.framework.core.pagination.PageResult;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class BaseCrudService<E, REQ, RES, ID> implements ITCrudService<REQ, RES, ID> {

    protected final BaseRepository<E, ID> repository;
    protected final CrudMapper<E, REQ, RES> mapper;

    public BaseCrudService(BaseRepository<E, ID> repository,
                           CrudMapper<E, REQ, RES> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // CREATE
    @Override
    public RES create(REQ request) {
        E entity = mapper.toEntity(request);
        E saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    // UPDATE
    @Override
    public RES update(ID id, REQ request) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found: " + id));

        mapper.updateEntity(request, entity);
        E saved = repository.save(entity);

        return mapper.toResponse(saved);
    }

    // DELETE
    @Override
    public void delete(ID id) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found: " + id));

        repository.delete(entity);
    }

    // FIND BY ID
    @Override
    public RES findById(ID id) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found: " + id));

        return mapper.toResponse(entity);
    }

    // SEARCH
    @Override
    public PageResult<RES> search(PageQuery pageQuery, List<FilterCriteria> filters) {

        Pageable pageable = PageRequest.of(
                pageQuery.page(),
                pageQuery.size(),
                pageQuery.direction(),
                pageQuery.sortBy()
        );

        Specification<E> spec = DynamicSpecifications.build(filters);

        var page = repository.findAll(spec, pageable)
                .map(mapper::toResponse);

        return PageMapper.from(page);
    }
}
