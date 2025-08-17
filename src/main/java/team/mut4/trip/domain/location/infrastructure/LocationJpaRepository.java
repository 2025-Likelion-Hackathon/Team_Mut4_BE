package team.mut4.trip.domain.location.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import team.mut4.trip.domain.location.domain.Location;

public interface LocationJpaRepository extends JpaRepository<Location, Long> {
}
