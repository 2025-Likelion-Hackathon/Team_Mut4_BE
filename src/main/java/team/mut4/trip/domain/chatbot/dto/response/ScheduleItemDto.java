package team.mut4.trip.domain.chatbot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "챗봇이 생성한 일정 항목 DTO")
public record ScheduleItemDto(

        @Schema(description = "일정 항목 순서", example = "1")
        int order,

        @Schema(description = "일정 항목 이름", example = "속초 중앙시장")
        String name,

        @Schema(description = "일정 항목 유형", example = "관광지 | 식당 | 숙소")
        String type,

        @Schema(description = "일정 항목 주소", example = "강원도 속초시 중앙로 12")
        String address
) {
}
