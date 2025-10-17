package com.erico.lavanderia.domain.scheduling;

import com.erico.lavanderia.application.dto.SchedulingSearchFiltersDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SchedulingSearchSpecification {

    private SchedulingSearchSpecification() {
    }

    public static Specification<Scheduling> withFilters(SchedulingSearchFiltersDTO filters) {
        return ((root, _, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(filters.registration())) {
                var userJoin = root.join("user");
                predicates.add(builder.like(userJoin.get("registration"), filters.registration() + "%"));
            }

            if (Objects.nonNull(filters.status())) {
                predicates.add(builder.equal(root.get("status"), filters.status()));
            }

            if (Objects.nonNull(filters.initialDateTime())) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dateTime").get("value"), filters.initialDateTime().toLocalDateTime()));
            }

            if (Objects.nonNull(filters.endDateTime())) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dateTime").get("value"), filters.endDateTime().toLocalDateTime()));
            }

            if (predicates.isEmpty()) {
                return builder.conjunction();
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
