package team.mut4.trip.domain.foodtag.dto.response;

import lombok.Builder;
import team.mut4.trip.domain.foodtag.domain.FoodTag;

@Builder
public record FoodTagInfoResponse(

        Long foodTagId,

        String tagName

) {
    public static FoodTagInfoResponse from(FoodTag foodTag) {
        return FoodTagInfoResponse.builder()
                .foodTagId(foodTag.getId())
                .tagName(foodTag.getTagName())
                .build();
    }
}
