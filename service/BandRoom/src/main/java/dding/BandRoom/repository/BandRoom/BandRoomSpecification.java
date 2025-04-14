package dding.BandRoom.repository.BandRoom;

import dding.BandRoom.entity.BandRoom;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class BandRoomSpecification {

    public static Specification<BandRoom> searchWithFilters(
            String name,
            String keyword,
            Boolean isOpen,
            Boolean available
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (keyword != null && !keyword.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("keywords")), "%" + keyword.toLowerCase() + "%"));
            }

            if (isOpen != null) {
                predicates.add(cb.equal(root.get("isOpen"), isOpen));
            }

            if (available != null) {
                predicates.add(cb.equal(root.get("isAvailable"), available));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
