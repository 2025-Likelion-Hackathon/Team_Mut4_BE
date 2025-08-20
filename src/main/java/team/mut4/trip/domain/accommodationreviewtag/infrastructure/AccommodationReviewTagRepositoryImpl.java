package team.mut4.trip.domain.accommodationreviewtag.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mut4.trip.domain.accommodationreviewtag.domain.AccommodationReviewTag;
import team.mut4.trip.domain.accommodationreviewtag.domain.AccommodationReviewTagRepository;

@RequiredArgsConstructor
@Repository
public class AccommodationReviewTagRepositoryImpl implements AccommodationReviewTagRepository {

    private final AccommodationReviewTagJpaRepository accommodationReviewTagJpaRepository;

    @Override
    public void save(AccommodationReviewTag accommodationReviewTag) {
        accommodationReviewTagJpaRepository.save(accommodationReviewTag);
    }

}
