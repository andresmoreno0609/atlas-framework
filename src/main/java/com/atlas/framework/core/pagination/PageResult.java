package com.atlas.framework.core.pagination;

import java.util.List;

/**
 * Resultado estándar para respuestas paginadas.
 *
 * @param <T> Tipo de los elementos devueltos en la página.
 */
public class PageResult<T> {

    private List<T> items;
    private long totalElements;
    private int totalPages;
    private int page;
    private int size;

    public PageResult(List<T> items, long totalElements, int totalPages, int page, int size) {
        this.items = items;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.page = page;
        this.size = size;
    }

    public List<T> getItems() {
        return items;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }
}
