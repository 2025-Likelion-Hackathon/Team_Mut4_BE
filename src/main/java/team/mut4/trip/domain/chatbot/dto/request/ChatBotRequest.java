package team.mut4.trip.domain.chatbot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "세션을 생성하고 AI로 메시지를 전송하기 위한 요청 DTO")
public record ChatBotRequest(

        @Schema(description = "세션 ID", example = "session123")
        String sessionId,

        @Schema(description = "요청 메시지")
        ChatMessageRequest message
) {
}
