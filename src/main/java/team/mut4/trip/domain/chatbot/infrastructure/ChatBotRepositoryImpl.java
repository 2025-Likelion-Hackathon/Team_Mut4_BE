package team.mut4.trip.domain.chatbot.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mut4.trip.domain.chatbot.domain.ChatBot;
import team.mut4.trip.domain.chatbot.domain.ChatBotRepository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ChatBotRepositoryImpl implements ChatBotRepository {

    private final ChatBotJpaRepository chatBotJpaRepository;

    @Override
    public void save(ChatBot chat) { chatBotJpaRepository.save(chat); }

    @Override
    public List<ChatBot> findBySessionIdOrderByCreatedAtAsc(String sessionId) {
        return chatBotJpaRepository.findBySessionIdOrderByCreatedAtAsc(sessionId);
    }

    @Override
    public List<String> findAllSessionIds() {
        return chatBotJpaRepository.findAllSessionIdsNative();
    }
}
