package team.mut4.trip.domain.review.dto.response;

import lombok.Builder;
import team.mut4.trip.domain.review.domain.Grade;

@Builder
public record FoodGradeSummaryResponse(

        double averageScore,

        Grade averageGrade

) {
}
