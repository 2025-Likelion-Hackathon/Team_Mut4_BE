package team.mut4.trip.domain.chatbot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "AI로 부터 메시지를 응답 받는 DTO")
public record ChatBotResponse(

        @Schema(description = "세션 ID", example = "session123")
        String sessionId,

        @Schema(description = "응답 메시지")
        ChatMessageResponse message
) {
}
