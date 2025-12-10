package com.atlas.framework.core.controller;

import com.atlas.framework.core.adapter.ICrudAdapter;
import com.atlas.framework.core.filter.FilterCriteria;
import com.atlas.framework.core.pagination.PageQuery;
import com.atlas.framework.core.pagination.PageResult;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class CrudController<REQ, RES, ID> {

    protected final ICrudAdapter<REQ, RES, ID> adapter;

    protected CrudController(ICrudAdapter<REQ, RES, ID> adapter) {
        this.adapter = adapter;
    }

    // CREATE
    @PostMapping
    public RES create(@RequestBody REQ request) {
        return adapter.create(request);
    }

    // UPDATE
    @PutMapping("/{id}")
    public RES update(@PathVariable ID id, @RequestBody REQ request) {
        return adapter.update(id, request);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) {
        adapter.delete(id);
    }

    // FIND BY ID
    @GetMapping("/{id}")
    public RES findById(@PathVariable ID id) {
        return adapter.findById(id);
    }

    // SEARCH - paginación + filtros dinámicos
    @PostMapping("/search")
    public PageResult<RES> search(
            @RequestBody(required = false) List<FilterCriteria> filters,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction
    ) {
        PageQuery query = new PageQuery(
                page,
                size,
                sortBy,
                "DESC".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC
        );

        return adapter.search(query, filters);
    }
}
