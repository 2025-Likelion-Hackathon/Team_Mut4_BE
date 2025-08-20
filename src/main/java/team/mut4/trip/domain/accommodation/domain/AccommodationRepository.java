package team.mut4.trip.domain.accommodation.domain;

import java.util.Optional;

public interface AccommodationRepository {

    Accommodation save(Accommodation accommodation);

    Optional<Accommodation> findByNameAndAddress(String name, String address);

    Accommodation findByAccommodationId(Long accommodationId);

}
