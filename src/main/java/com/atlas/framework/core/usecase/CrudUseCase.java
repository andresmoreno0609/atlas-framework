package com.atlas.framework.core.usecase;

import com.atlas.framework.core.service.ITCrudService;
import com.atlas.framework.core.pagination.PageQuery;
import com.atlas.framework.core.pagination.PageResult;
import com.atlas.framework.core.filter.FilterCriteria;

import java.util.List;

public class CrudUseCase<REQ, RES, ID> implements ITCrudUseCase<REQ, RES, ID> {

    private final ITCrudService<REQ, RES, ID> service;

    public CrudUseCase(ITCrudService<REQ, RES, ID> service) {
        this.service = service;
    }

    @Override
    public RES create(REQ request) {
        return service.create(request);
    }

    @Override
    public RES update(ID id, REQ request) {
        return service.update(id, request);
    }

    @Override
    public void delete(ID id) {
        service.delete(id);
    }

    @Override
    public RES findById(ID id) {
        return service.findById(id);
    }

    @Override
    public PageResult<RES> search(PageQuery pageQuery, List<FilterCriteria> filters) {
        return service.search(pageQuery, filters);
    }
}
