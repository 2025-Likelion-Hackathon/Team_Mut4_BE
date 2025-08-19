package team.mut4.trip.domain.chatbot.domain;

import java.util.List;

public interface ChatBotRepository {

    void save(ChatBot chat);

    List<ChatBot> findBySessionIdOrderByCreatedAtAsc(String sessionId);

    List<String> findAllSessionIds();
}
