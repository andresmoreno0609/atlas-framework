package com.atlas.framework.core.service;

import com.atlas.framework.core.pagination.PageQuery;
import com.atlas.framework.core.pagination.PageResult;
import com.atlas.framework.core.filter.FilterCriteria;

import java.util.List;

public interface ITCrudService<REQ, RES, ID> {

    RES create(REQ request);

    RES update(ID id, REQ request);

    void delete(ID id);

    RES findById(ID id);

    PageResult<RES> search(PageQuery pageQuery, List<FilterCriteria> filters);
}
