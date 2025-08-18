package team.mut4.trip.domain.chatbot.dto.response;

public record ChatBotResponse(
        String sessionId,
        ChatMessageResponse message
) {
}
