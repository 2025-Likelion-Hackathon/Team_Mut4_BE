package team.mut4.trip.domain.accommodation.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import team.mut4.trip.domain.accommodation.domain.Accommodation;

import java.util.Optional;

public interface AccommodationJpaRepository extends JpaRepository<Accommodation, Long> {

    Optional<Accommodation> findByNameAndAddress(String name, String address);

}
