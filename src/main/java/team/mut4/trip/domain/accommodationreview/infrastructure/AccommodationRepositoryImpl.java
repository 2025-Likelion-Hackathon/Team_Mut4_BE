package team.mut4.trip.domain.accommodationreview.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mut4.trip.domain.accommodationreview.domain.AccommodationReview;
import team.mut4.trip.domain.accommodationreview.domain.AccommodationReviewRepository;

@RequiredArgsConstructor
@Repository
public class AccommodationRepositoryImpl implements AccommodationReviewRepository {

    private final AccommodationReviewJpaRepository accommodationReviewJpaRepository;

    @Override
    public void save(AccommodationReview accommodationReview) {
        accommodationReviewJpaRepository.save(accommodationReview);
    }

}
