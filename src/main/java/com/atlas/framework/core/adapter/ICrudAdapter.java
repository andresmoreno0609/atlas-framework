package com.atlas.framework.core.adapter;

import com.atlas.framework.core.filter.FilterCriteria;
import com.atlas.framework.core.pagination.PageQuery;
import com.atlas.framework.core.pagination.PageResult;

import java.util.List;

public interface ICrudAdapter<REQ, RES, ID> {

    RES create(REQ request);

    RES update(ID id, REQ request);

    void delete(ID id);

    RES findById(ID id);

    PageResult<RES> search(PageQuery pageQuery, List<FilterCriteria> filters);
}
