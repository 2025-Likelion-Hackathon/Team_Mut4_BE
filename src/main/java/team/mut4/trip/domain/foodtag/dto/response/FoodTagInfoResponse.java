package team.mut4.trip.domain.foodtag.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import team.mut4.trip.domain.foodtag.domain.FoodTag;

@Builder
public record FoodTagInfoResponse(

        @Schema(description = "음식 태그 ID", example = "1")
        Long foodTagId,

        @Schema(description = "태그 이름", example = "맛있어요")
        String tagName

) {
    public static FoodTagInfoResponse from(FoodTag foodTag) {
        return FoodTagInfoResponse.builder()
                .foodTagId(foodTag.getId())
                .tagName(foodTag.getTagName())
                .build();
    }
}
