package team.mut4.trip.domain.accommodationreviewtag.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.accommodationreview.domain.AccommodationReview;
import team.mut4.trip.domain.accommodationreviewtag.domain.AccommodationReviewTag;
import team.mut4.trip.domain.accommodationreviewtag.domain.AccommodationReviewTagRepository;
import team.mut4.trip.domain.accommodationtag.domain.AccommodationTag;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccommodationReviewTagService {

    private final AccommodationReviewTagRepository accommodationReviewTagRepository;

    @Transactional
    public void saveTagsForAccommodationReview(
            AccommodationReview accommodationReview,
            List<AccommodationTag> tags
    ) {
        tags.forEach(tag -> {
           AccommodationReviewTag reviewTag = AccommodationReviewTag.builder()
                   .accommodationReview(accommodationReview)
                   .accommodationTag(tag)
                   .build();
           accommodationReviewTagRepository.save(reviewTag);
        });
    }

}
