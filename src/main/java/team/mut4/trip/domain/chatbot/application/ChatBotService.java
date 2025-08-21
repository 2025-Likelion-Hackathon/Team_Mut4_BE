package team.mut4.trip.domain.chatbot.application;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import team.mut4.trip.domain.chatbot.domain.ChatBot;
import team.mut4.trip.domain.chatbot.domain.ChatBotRepository;
import team.mut4.trip.domain.chatbot.dto.request.ChatBotRequest;
import team.mut4.trip.domain.chatbot.dto.response.ChatBotResponse;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatBotService {

    private final WebClient webClient;
    private final ChatBotRepository chatBotRepository;

    public void create(@Valid @RequestBody ChatBotRequest request) {

        ChatBot chat = ChatBot.builder()
                .sessionId(request.sessionId())
                .role(request.message().role())
                .content(request.message().content())
                .build();

        chatBotRepository.save(chat);
    }

    public List<String> getAllSessionIds() {
        return chatBotRepository.findAllSessionIds();
    }

    public List<ChatBot> getAllBySession(String sessionId) {
        return chatBotRepository.findBySessionIdOrderByCreatedAtAsc(sessionId);
    }

    public Mono<ChatBotResponse> askFastApi(ChatBotRequest request) {
        return webClient.post()
                .uri("/chat")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChatBotResponse.class)
                .doOnSuccess(aiResponse -> {
                    create(aiResponse);
                });

    }

    private void create(ChatBotResponse aiResponse) {
        ChatBot chat = ChatBot.builder()
                .sessionId(aiResponse.sessionId())
                .role(aiResponse.message().role())
                .content(aiResponse.message().content())
                .build();

        chatBotRepository.save(chat);
    }
}
