package team.mut4.trip.domain.accommodationreview.domain;

import team.mut4.trip.domain.accommodation.domain.Accommodation;

import java.util.List;

public interface AccommodationReviewRepository {

    void save(AccommodationReview accommodationReview);

    List<AccommodationReview> findAllByAccommodation(Accommodation accommodation);

}
