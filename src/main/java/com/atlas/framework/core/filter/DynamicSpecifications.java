package com.atlas.framework.core.filter;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.*;
import java.util.List;

public final class DynamicSpecifications {

    private DynamicSpecifications() {}

    public static <E> Specification<E> build(List<FilterCriteria> filters) {
        if (filters == null || filters.isEmpty()) {
            return null;
        }

        return (root, query, cb) -> {
            Predicate[] predicates = filters.stream()
                    .map(fc -> toPredicate(fc, root, cb))
                    .toArray(Predicate[]::new);

            return cb.and(predicates);
        };
    }

    private static <E> Predicate toPredicate(
            FilterCriteria fc,
            Root<E> root,
            CriteriaBuilder cb
    ) {
        Path<Object> path = resolvePath(root, fc.field());

        return switch (fc.operator()) {
            case EQ -> cb.equal(path, fc.value());
            case NE -> cb.notEqual(path, fc.value());
            case GT -> cb.greaterThan(path.as(Comparable.class), (Comparable) fc.value());
            case LT -> cb.lessThan(path.as(Comparable.class), (Comparable) fc.value());
            case GTE -> cb.greaterThanOrEqualTo(path.as(Comparable.class), (Comparable) fc.value());
            case LTE -> cb.lessThanOrEqualTo(path.as(Comparable.class), (Comparable) fc.value());
            case LIKE -> cb.like(cb.lower(path.as(String.class)), "%" + fc.value().toString().toLowerCase() + "%");
            case STARTS_WITH -> cb.like(cb.lower(path.as(String.class)), fc.value().toString().toLowerCase() + "%");
            case ENDS_WITH -> cb.like(cb.lower(path.as(String.class)), "%" + fc.value().toString().toLowerCase());
            case IN -> path.in(fc.values());
            case NOT_IN -> cb.not(path.in(fc.values()));
        };
    }

    private static Path<Object> resolvePath(Root<?> root, String field) {
        if (!field.contains(".")) {
            return root.get(field);
        }

        String[] parts = field.split("\\.");
        Path<?> path = root;

        for (String part : parts) {
            path = path.get(part);
        }

        return (Path<Object>) path;
    }
}
