package team.mut4.trip.domain.locationaccomodationbookmark.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.accommodation.domain.Accommodation;
import team.mut4.trip.domain.accommodation.domain.AccommodationRepository;
import team.mut4.trip.domain.location.domain.Location;
import team.mut4.trip.domain.location.domain.LocationRepository;
import team.mut4.trip.domain.locationaccomodationbookmark.domain.LocationAccommodationBookMark;
import team.mut4.trip.domain.locationaccomodationbookmark.domain.LocationAccommodationBookMarkRepository;
import team.mut4.trip.domain.locationaccomodationbookmark.dto.response.LocationAccommodationBookMarkSaveResponse;

@RequiredArgsConstructor
@Service
public class LocationAccommodationBookMarkService {

    private final LocationAccommodationBookMarkRepository locationAccommodationBookMarkRepository;
    private final LocationRepository locationRepository;
    private final AccommodationRepository accommodationRepository;

    @Transactional
    public LocationAccommodationBookMarkSaveResponse saveAccommodationBookMark(Long locationId, Long accommodationId) {
        Location location = locationRepository.findByLocationId(locationId);
        Accommodation accommodation = accommodationRepository.findByAccommodationId(accommodationId);

        LocationAccommodationBookMark existing = locationAccommodationBookMarkRepository.findByLocationAndAccommodation(location, accommodation);
        if (existing != null) {
            return LocationAccommodationBookMarkSaveResponse.builder()
                    .locationAccommodationBookMarkId(existing.getId())
                    .build();
        }

        LocationAccommodationBookMark locationAccommodationBookMark = LocationAccommodationBookMark.builder()
                .location(location)
                .accommodation(accommodation)
                .build();
        locationAccommodationBookMarkRepository.save(locationAccommodationBookMark);

        return LocationAccommodationBookMarkSaveResponse.builder()
                .locationAccommodationBookMarkId(locationAccommodationBookMark.getId())
                .build();
    }

}
