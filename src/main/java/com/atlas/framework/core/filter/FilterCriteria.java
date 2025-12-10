package com.atlas.framework.core.filter;

import java.util.List;

public record FilterCriteria(
        String field,
        FilterOperator operator,
        Object value,
        List<Object> values
) {

    public static FilterCriteria simple(String field, FilterOperator operator, Object value) {
        return new FilterCriteria(field, operator, value, null);
    }

    public static FilterCriteria multi(String field, FilterOperator operator, List<Object> values) {
        return new FilterCriteria(field, operator, null, values);
    }
}
