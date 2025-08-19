package team.mut4.trip.domain.chatbot.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team.mut4.trip.domain.chatbot.domain.ChatBot;

import java.util.List;

@Repository
public interface ChatBotJpaRepository extends JpaRepository<ChatBot, Long> {

    List<ChatBot> findBySessionIdOrderByCreatedAtAsc(String sessionId);
    List<ChatBot> findTop50BySessionIdOrderByCreatedAtDesc(String sessionId);

    @Query(value = "SELECT DISTINCT sessionId FROM chatbot", nativeQuery = true)
    List<String> findAllSessionIdsNative();
}
