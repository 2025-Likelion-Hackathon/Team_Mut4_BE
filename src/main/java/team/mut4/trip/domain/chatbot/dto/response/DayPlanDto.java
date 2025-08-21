package team.mut4.trip.domain.chatbot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "챗봇이 생성한 일정 DTO")
public record DayPlanDto(

        @Schema(description = "여행 n일차", example = "1")
        int day,

        @Schema(description = "일정 항목 리스트")
        List<ScheduleItemDto> schedule
) {
}
