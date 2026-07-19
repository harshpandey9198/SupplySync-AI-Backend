package supplysync_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import supplysync_backend.entity.Product;
import supplysync_backend.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<Product>> getInventory() {
        return ResponseEntity.ok(inventoryService.getInventory());
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<Product>> getLowStock() {
        return ResponseEntity.ok(inventoryService.getLowStock());
    }
        @PostMapping("/{id}/stock-in")
    public ResponseEntity<Product> stockIn(
            @PathVariable Long id,
            @RequestParam Integer quantity) {

        return ResponseEntity.ok(
                inventoryService.stockIn(id, quantity)
        );
    }

    @PostMapping("/{id}/stock-out")
    public ResponseEntity<Product> stockOut(
            @PathVariable Long id,
            @RequestParam Integer quantity) {

        return ResponseEntity.ok(
                inventoryService.stockOut(id, quantity)
        );
    }
}