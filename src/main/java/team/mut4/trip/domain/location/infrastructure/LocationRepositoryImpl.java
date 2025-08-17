package team.mut4.trip.domain.location.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mut4.trip.domain.location.domain.Location;
import team.mut4.trip.domain.location.domain.LocationRepository;

@RequiredArgsConstructor
@Repository
public class LocationRepositoryImpl implements LocationRepository {

    private final LocationJpaRepository locationJpaRepository;

    @Override
    public void save(Location location) {
        locationJpaRepository.save(location);
    }

}
