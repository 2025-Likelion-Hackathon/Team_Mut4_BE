package team.mut4.trip.domain.chatbot.dto.request;

public record ChatBotRequest(
        String sessionId,
        ChatMessageRequest message
) {
}
