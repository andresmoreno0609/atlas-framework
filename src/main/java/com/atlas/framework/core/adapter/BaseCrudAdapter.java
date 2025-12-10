package com.atlas.framework.core.adapter;

import com.atlas.framework.core.usecase.CrudUseCase;
import com.atlas.framework.core.pagination.PageQuery;
import com.atlas.framework.core.pagination.PageResult;
import com.atlas.framework.core.filter.FilterCriteria;

import java.util.List;

public class BaseCrudAdapter<REQ, RES, ID>
        implements ICrudAdapter<REQ, RES, ID> {

    private final CrudUseCase<REQ, RES, ID> crudUseCase;

    public BaseCrudAdapter(CrudUseCase<REQ, RES, ID> crudUseCase) {
        this.crudUseCase = crudUseCase;
    }

    @Override
    public RES create(REQ request) {
        return crudUseCase.create(request);
    }

    @Override
    public RES update(ID id, REQ request) {
        return crudUseCase.update(id, request);
    }

    @Override
    public void delete(ID id) {
        crudUseCase.delete(id);
    }

    @Override
    public RES findById(ID id) {
        return crudUseCase.findById(id);
    }

    @Override
    public PageResult<RES> search(PageQuery pageQuery, List<FilterCriteria> filters) {
        return crudUseCase.search(pageQuery, filters);
    }
}
