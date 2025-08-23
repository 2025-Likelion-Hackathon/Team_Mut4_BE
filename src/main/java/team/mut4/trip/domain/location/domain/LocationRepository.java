package team.mut4.trip.domain.location.domain;

import java.util.Optional;

public interface LocationRepository {

    void save(Location location);

    Location findByLocationId(Long locationId);

    Optional<Location> findByAddress(String address);

}
