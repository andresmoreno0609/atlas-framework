package com.atlas.framework.core.pagination;

import com.atlas.framework.core.filter.FilterCriteria;

import java.util.List;

public record PageFilterQuery(
        PageQuery pageQuery,
        List<FilterCriteria> filters
) {}
