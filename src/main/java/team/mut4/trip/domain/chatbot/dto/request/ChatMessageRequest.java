package team.mut4.trip.domain.chatbot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "챗봇 메시지 요청 DTO")
public record ChatMessageRequest(

        @Schema(description = "메시지 전송자 역할", example = "user")
        String role,

        @Schema(description = "메시지 내용", example = "속초, 3일, 한식")
        String content
) {
}
