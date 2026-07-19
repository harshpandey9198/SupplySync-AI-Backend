package supplysync_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import supplysync_backend.dto.PurchaseOrderRequest;
import supplysync_backend.dto.PurchaseOrderResponse;
import supplysync_backend.entity.PurchaseOrderStatus;
import supplysync_backend.service.PurchaseOrderService;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @PostMapping
    public PurchaseOrderResponse createPurchaseOrder(@RequestBody PurchaseOrderRequest request) {
        return purchaseOrderService.createPurchaseOrder(request);
    }

    @GetMapping
    public List<PurchaseOrderResponse> getAllPurchaseOrders() {
        return purchaseOrderService.getAllPurchaseOrders();
    }

    @GetMapping("/{id}")
    public PurchaseOrderResponse getPurchaseOrderById(@PathVariable Long id) {
        return purchaseOrderService.getPurchaseOrderById(id);
    }

    @PutMapping("/{id}/status")
    public PurchaseOrderResponse updateStatus(
            @PathVariable Long id,
            @RequestParam PurchaseOrderStatus status
    ) {
        return purchaseOrderService.updateStatus(id, status);
    }
}