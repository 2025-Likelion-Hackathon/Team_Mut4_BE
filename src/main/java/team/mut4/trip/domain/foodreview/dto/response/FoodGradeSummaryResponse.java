package team.mut4.trip.domain.foodreview.dto.response;

import lombok.Builder;
import team.mut4.trip.domain.foodreview.domain.FoodGrade;

@Builder
public record FoodGradeSummaryResponse(

        double averageScore,

        FoodGrade averageFoodGrade

) {
}
