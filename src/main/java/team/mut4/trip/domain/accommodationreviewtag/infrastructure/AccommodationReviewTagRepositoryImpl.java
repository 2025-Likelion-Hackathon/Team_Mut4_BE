package team.mut4.trip.domain.accommodationreviewtag.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mut4.trip.domain.accommodation.domain.Accommodation;
import team.mut4.trip.domain.accommodationreviewtag.domain.AccommodationReviewTag;
import team.mut4.trip.domain.accommodationreviewtag.domain.AccommodationReviewTagRepository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class AccommodationReviewTagRepositoryImpl implements AccommodationReviewTagRepository {

    private final AccommodationReviewTagJpaRepository accommodationReviewTagJpaRepository;

    @Override
    public void save(AccommodationReviewTag accommodationReviewTag) {
        accommodationReviewTagJpaRepository.save(accommodationReviewTag);
    }

    @Override
    public List<Object[]> findTagUsageCountByAccommodation(Accommodation accommodation) {
        return accommodationReviewTagJpaRepository.findTagUsageCountByAccommodation(accommodation);
    }

}
