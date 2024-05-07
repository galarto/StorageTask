package com.dandellion.services;

import com.dandellion.dto.RequestDto;
import com.dandellion.dto.SearchRequestDto;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FiltersSpecification<T> {

    public Specification<T> getSearchSpecification(SearchRequestDto searchRequestDto) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
    }

    public Specification<T> getSearchSpecification(List<SearchRequestDto> searchRequestDto,
                                                   RequestDto.GlobalOperator globalOperator) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            for (SearchRequestDto requestDto : searchRequestDto) {

                switch (requestDto.getOperation()) {

                    case EQUAL:
                        Predicate equal;
                        Path<?> columnPath = root.get(requestDto.getColumn());
                        if (columnPath.getJavaType().equals(Double.class)) {
                            double doubleValue = Double.parseDouble(requestDto.getValue());
                            equal = criteriaBuilder.equal(columnPath, doubleValue);
                        } else if (columnPath.getJavaType().equals(Boolean.class)) {
                            boolean boolValue = Boolean.parseBoolean(requestDto.getValue());
                            equal = criteriaBuilder.equal(columnPath, boolValue);
                        } else {
                            equal = criteriaBuilder.equal(columnPath, requestDto.getValue());
                        }
                        predicates.add(equal);
                        break;

                    case LIKE:
                        Predicate like = criteriaBuilder
                                .like(root.get(requestDto.getColumn()), "%" + requestDto.getValue() + "%");
                        predicates.add(like);
                        break;

                    case LESS_THAN:
                        Predicate lessThan = criteriaBuilder
                                .lessThanOrEqualTo(root.get(requestDto.getColumn()), requestDto.getValue());
                        predicates.add(lessThan);
                        break;

                    case GREATER_THAN:
                        Predicate greaterThan = criteriaBuilder
                                .greaterThanOrEqualTo(root.get(requestDto.getColumn()), requestDto.getValue());
                        predicates.add(greaterThan);
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value " + " ");
                }
            }

            if (globalOperator.equals(RequestDto.GlobalOperator.AND)) {
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            } else {
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            }

        };
    }
}
