package team.mut4.trip.domain.location.domain;

public interface LocationRepository {

    void save(Location location);

    Location findByLocationId(Long locationId);

}
