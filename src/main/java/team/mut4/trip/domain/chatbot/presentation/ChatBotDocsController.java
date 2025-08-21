package team.mut4.trip.domain.chatbot.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import team.mut4.trip.domain.chatbot.domain.ChatBot;
import team.mut4.trip.domain.chatbot.dto.request.ChatBotRequest;
import team.mut4.trip.domain.chatbot.dto.response.ChatBotResponse;

import java.util.List;

@Tag(name = "ChatBot", description = "챗봇 API")
@RequestMapping("/chatbot")
public interface ChatBotDocsController{

    @Operation(summary = "챗봇 세션 목록 조회", description = "챗봇 세션의 목록을 조회합니다.")
    @GetMapping
    List<String> getAllSessions();

    @Operation(summary = "특정 챗봇 세션의 모든 대화 조회", description = "주어진 세션 ID에 대한 모든 대화를 조회합니다.")
    @GetMapping("/{sessionId}")
    List<ChatBot> getAll(
            @Parameter(description = "조회할 세션 ID") @PathVariable String sessionId
    );

    @Operation(summary = "챗봇과 대화하기", description = "챗봇과 대화하여 응답을 받습니다.")
    @PostMapping
    Mono<ChatBotResponse> chatWithAi(
            @Valid @RequestBody ChatBotRequest request
    );

}
