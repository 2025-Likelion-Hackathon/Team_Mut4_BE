package team.mut4.trip.domain.chatbot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "챗봇이 생성한 일정 항목 DTO")
public record ScheduleItemDto(

        @Schema(description = "순서", example = "1")
        int order,

        @Schema(description = "이름", example = "속초 중앙시장")
        String name,

        @Schema(description = "유형", example = "관광지 | 식당 | 숙소")
        String type,

        @Schema(description = "주소", example = "강원도 속초시 중앙로 12")
        String address,

        @Schema(description = "위도", example = "37.123456")
        double latitude,

        @Schema(description = "일정 항목 경도", example = "127.123456")
        double longitude,

        @Schema(description = "설명", example = "속초 중앙시장은 신선한 해산물과 다양한 먹거리가 가득한 전통 시장입니다.")
        String description,

        @Schema(description = "현지인 인증 등급", example = "A")
        String grade
) {
}
