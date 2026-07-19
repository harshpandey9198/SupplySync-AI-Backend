package supplysync_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import supplysync_backend.dto.SalesOrderRequest;
import supplysync_backend.dto.SalesOrderResponse;
import supplysync_backend.entity.SalesOrderStatus;
import supplysync_backend.service.SalesOrderService;

import java.util.List;

@RestController
@RequestMapping("/api/sales-orders")
@RequiredArgsConstructor
public class SalesOrderController {

    private final SalesOrderService salesOrderService;

    @PostMapping
    public SalesOrderResponse createSalesOrder(@RequestBody SalesOrderRequest request) {
        return salesOrderService.createSalesOrder(request);
    }

    @GetMapping
    public List<SalesOrderResponse> getAllSalesOrders() {
        return salesOrderService.getAllSalesOrders();
    }

    @GetMapping("/{id}")
    public SalesOrderResponse getSalesOrderById(@PathVariable Long id) {
        return salesOrderService.getSalesOrderById(id);
    }

    @PutMapping("/{id}/status")
    public SalesOrderResponse updateStatus(
            @PathVariable Long id,
            @RequestParam SalesOrderStatus status
    ) {
        return salesOrderService.updateStatus(id, status);
    }
}