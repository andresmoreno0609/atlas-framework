package com.atlas.framework.core.pagination;

import org.springframework.data.domain.Sort;

public record PageQuery(
        int page,
        int size,
        String sortBy,
        Sort.Direction direction
) {}
