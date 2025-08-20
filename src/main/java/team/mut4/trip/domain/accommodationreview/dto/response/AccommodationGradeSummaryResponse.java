package team.mut4.trip.domain.accommodationreview.dto.response;

import lombok.Builder;
import team.mut4.trip.domain.accommodationreview.domain.AccommodationGrade;

@Builder
public record AccommodationGradeSummaryResponse(

        double averageScore,

        AccommodationGrade averageAccommodationGrade

) {
}
