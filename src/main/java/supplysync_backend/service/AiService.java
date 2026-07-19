package supplysync_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import supplysync_backend.dto.AiRequest;
import supplysync_backend.dto.AiResponse;
import supplysync_backend.repository.ProductRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AiService {

    private final ProductRepository productRepository;
    private final WebClient webClient;

    @Value("${gemini.api.key:test123}")
    private String apiKey;

    @Value("${gemini.api.url:https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent}")
    private String apiUrl;

    public AiResponse ask(AiRequest request) {

        long totalProducts = productRepository.count();
        long lowStock = productRepository.findLowStockProducts().size();

        if (apiKey == null || apiKey.isBlank() || apiKey.equals("test123")) {
            String fallbackResponse = "SupplySync AI: Total products = " + totalProducts
                    + ", low stock products = " + lowStock
                    + ". Gemini API key is not configured yet.";
            return new AiResponse(fallbackResponse);
        }

        String systemPrompt =
                "You are SupplySync AI, an inventory and supply chain assistant. "
                        + "Current business summary: total products = " + totalProducts
                        + ", low stock products = " + lowStock
                        + ". Answer in simple business language. User question: "
                        + request.getPrompt();

        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", systemPrompt)
                        ))
                )
        );

        try {
            Map response = webClient.post()
                    .uri(apiUrl + "?key=" + apiKey)
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            List candidates = (List) response.get("candidates");
            Map firstCandidate = (Map) candidates.get(0);
            Map content = (Map) firstCandidate.get("content");
            List parts = (List) content.get("parts");
            Map firstPart = (Map) parts.get(0);

            return new AiResponse(firstPart.get("text").toString());

        } catch (Exception e) {
            return new AiResponse("SupplySync AI is currently unavailable. Error: " + e.getMessage());
        }
    }
}