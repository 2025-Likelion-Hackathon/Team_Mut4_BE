package team.mut4.trip.domain.chatbot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "챗봇 메시지 응답 DTO")
public record ChatMessageResponse(

        @Schema(description = "메시지 전송자 역할", example = "assistant")
        String role,

        @Schema(description = "메세지 내용")
        List<DayPlanDto> content
) {
}
