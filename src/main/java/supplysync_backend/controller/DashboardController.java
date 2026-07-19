package supplysync_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import supplysync_backend.dto.DashboardStatsResponse;
import supplysync_backend.dto.ProductResponse;
import supplysync_backend.entity.PurchaseOrder;
import supplysync_backend.service.DashboardService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")   // Optional: agar CORS globally configure nahi hai
public class DashboardController {

    private final DashboardService dashboardService;

    // Dashboard Statistics
    @GetMapping("/stats")
    public DashboardStatsResponse getStats() {
        return dashboardService.getStats();
    }

    // Low Stock Products
    @GetMapping("/low-stock")
    public List<ProductResponse> getLowStockProducts() {
        return dashboardService.getLowStockProducts();
    }

    // Recent Purchase Orders
    @GetMapping("/recent-orders")
    public List<PurchaseOrder> getRecentOrders() {
        return dashboardService.getRecentOrders();
    }
    @GetMapping("/analytics")
public Map<String, Object> getAnalytics() {
    return dashboardService.getAnalytics();
}

}