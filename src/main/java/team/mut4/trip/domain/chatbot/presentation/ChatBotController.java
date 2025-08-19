package team.mut4.trip.domain.chatbot.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import team.mut4.trip.domain.chatbot.application.ChatBotService;
import team.mut4.trip.domain.chatbot.domain.ChatBot;
import team.mut4.trip.domain.chatbot.dto.request.ChatBotRequest;
import team.mut4.trip.domain.chatbot.dto.response.ChatBotResponse;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/chatbot")
@RestController
public class ChatBotController {

    private final ChatBotService chatBotService;

    @GetMapping
    public List<String> getAllSessions() {
        return chatBotService.getAllSessionIds();
    }

    @GetMapping("/{sessionId}")
    public List<ChatBot> getAll(@PathVariable String sessionId) {
        return chatBotService.getAllBySession(sessionId);
    }

    @PostMapping
    public Mono<ChatBotResponse> chatWithAi(@Valid @RequestBody ChatBotRequest request) {
        chatBotService.create(request);
        return chatBotService.askFastApi(request);
    }
}
