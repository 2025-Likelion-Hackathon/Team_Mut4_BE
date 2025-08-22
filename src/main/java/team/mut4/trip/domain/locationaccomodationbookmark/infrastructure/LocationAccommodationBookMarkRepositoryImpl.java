package team.mut4.trip.domain.locationaccomodationbookmark.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mut4.trip.domain.accommodation.domain.Accommodation;
import team.mut4.trip.domain.location.domain.Location;
import team.mut4.trip.domain.locationaccomodationbookmark.domain.LocationAccommodationBookMark;
import team.mut4.trip.domain.locationaccomodationbookmark.domain.LocationAccommodationBookMarkRepository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class LocationAccommodationBookMarkRepositoryImpl implements LocationAccommodationBookMarkRepository {

    private final LocationAccommodationBookMarkJpaRepository locationAccommodationBookMarkJpaRepository;

    @Override
    public void save(LocationAccommodationBookMark locationAccommodationBookMark) {
        locationAccommodationBookMarkJpaRepository.save(locationAccommodationBookMark);
    }

    @Override
    public LocationAccommodationBookMark findByLocationAndAccommodation(Location location, Accommodation accommodation) {
        return locationAccommodationBookMarkJpaRepository.findByLocationAndAccommodation(location, accommodation);
    }

    @Override
    public List<LocationAccommodationBookMark> findAllByLocation(Location location) {
        return locationAccommodationBookMarkJpaRepository.findAllByLocation(location);
    }

}
