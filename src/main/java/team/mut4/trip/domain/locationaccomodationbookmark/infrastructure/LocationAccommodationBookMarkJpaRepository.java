package team.mut4.trip.domain.locationaccomodationbookmark.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import team.mut4.trip.domain.accommodation.domain.Accommodation;
import team.mut4.trip.domain.location.domain.Location;
import team.mut4.trip.domain.locationaccomodationbookmark.domain.LocationAccommodationBookMark;

public interface LocationAccommodationBookMarkJpaRepository extends JpaRepository<LocationAccommodationBookMark, Long> {

    LocationAccommodationBookMark findByLocationAndAccommodation(Location location, Accommodation accommodation);

}
