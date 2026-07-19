package supplysync_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import supplysync_backend.dto.AiRequest;
import supplysync_backend.dto.AiResponse;
import supplysync_backend.service.AiService;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @PostMapping("/ask")
    public AiResponse ask(@RequestBody AiRequest request) {
        return aiService.ask(request);
    }
}