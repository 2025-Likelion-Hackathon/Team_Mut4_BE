package team.mut4.trip.domain.accommodationreviewtag.domain;

import team.mut4.trip.domain.accommodation.domain.Accommodation;

import java.util.List;

public interface AccommodationReviewTagRepository {

    void save(AccommodationReviewTag accommodationReviewTag);

    List<Object[]> findTagUsageCountByAccommodation(Accommodation accommodation);

}
