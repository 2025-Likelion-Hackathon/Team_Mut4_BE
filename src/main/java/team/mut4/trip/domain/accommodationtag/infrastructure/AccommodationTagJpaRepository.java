package team.mut4.trip.domain.accommodationtag.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import team.mut4.trip.domain.accommodationtag.domain.AccommodationTag;

import java.util.List;

public interface AccommodationTagJpaRepository extends JpaRepository<AccommodationTag, Long> {

    List<AccommodationTag> findByIdIn(List<Long> accommodationTagIds);

}
