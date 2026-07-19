package supplysync_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supplysync_backend.entity.StockMovement;
import supplysync_backend.service.StockMovementService;

import java.util.List;

@RestController
@RequestMapping("/api/stock-movements")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StockMovementController {

    private final StockMovementService stockMovementService;

    @GetMapping
    public ResponseEntity<List<StockMovement>> getAllMovements() {

        return ResponseEntity.ok(
                stockMovementService.getAllMovements()
        );

    }

}