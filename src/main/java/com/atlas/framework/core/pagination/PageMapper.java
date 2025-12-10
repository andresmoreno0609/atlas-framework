package com.atlas.framework.core.pagination;

import org.springframework.data.domain.Page;

public final class PageMapper {

    private PageMapper() {}

    public static <T> PageResult<T> from(Page<T> page) {
        return new PageResult<>(
                page.getContent(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize()
        );
    }
}
