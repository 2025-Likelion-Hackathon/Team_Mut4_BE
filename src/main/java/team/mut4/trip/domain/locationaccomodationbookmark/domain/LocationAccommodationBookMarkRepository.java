package team.mut4.trip.domain.locationaccomodationbookmark.domain;

import team.mut4.trip.domain.accommodation.domain.Accommodation;
import team.mut4.trip.domain.location.domain.Location;

public interface LocationAccommodationBookMarkRepository {

    void save(LocationAccommodationBookMark locationAccommodationBookMark);

    LocationAccommodationBookMark findByLocationAndAccommodation(Location location, Accommodation accommodation);

}
