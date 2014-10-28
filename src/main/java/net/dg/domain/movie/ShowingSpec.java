package net.dg.domain.movie;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ShowingSpec {

    public static Specification<Showing> movie(final Long movieId) {
        return new Specification<Showing>() {
            @Override
            public Predicate toPredicate(Root<Showing> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if(movieId == null) {
                    return null;
                }
                return cb.equal(root.get("movie"), movieId);
            }
        };
    }

}
