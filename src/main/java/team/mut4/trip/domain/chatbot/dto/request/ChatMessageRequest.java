package team.mut4.trip.domain.chatbot.dto.request;

public record ChatMessageRequest(
        String role,
        String content
) {
}
