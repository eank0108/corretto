package com.ean.corretto.repository;

import com.ean.corretto.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ArticleSpecification implements Specification<Article> {
    private final Timestamp startDateTime;
    private final Timestamp endDateTime;
    private final String boardName;
    @Override
    public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (startDateTime != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdDatetime"), startDateTime));
        }
        if (endDateTime != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdDatetime"), endDateTime));
        }

        if (boardName != null) {
            predicates.add(criteriaBuilder.like(root.get("board").get("name"), "%"+boardName+"%"));
        }

        final Predicate[] predicateArray = new Predicate[predicates.size()];
        return query.where(criteriaBuilder.and(predicates.toArray(predicateArray)))
                .distinct(true)
                .getRestriction();
    }

}
