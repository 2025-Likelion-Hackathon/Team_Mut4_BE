package team.mut4.trip.domain.accommodationreviewtag.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team.mut4.trip.domain.accommodation.domain.Accommodation;
import team.mut4.trip.domain.accommodationreviewtag.domain.AccommodationReviewTag;

import java.util.List;

public interface AccommodationReviewTagJpaRepository extends JpaRepository<AccommodationReviewTag, Long> {

    @Query("SELECT art.accommodationTag.id, art.accommodationTag.tagName, COUNT(art) " +
            "FROM AccommodationReviewTag art " +
            "WHERE art.accommodationReview.accommodation = :accommodation " +
            "GROUP BY art.accommodationTag.id, art.accommodationTag.tagName " +
            "ORDER BY COUNT(art) DESC")
    List<Object[]> findTagUsageCountByAccommodation(Accommodation accommodation);

}
