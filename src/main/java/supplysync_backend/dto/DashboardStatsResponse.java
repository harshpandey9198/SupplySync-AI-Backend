package supplysync_backend.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DashboardStatsResponse {

    private long totalProducts;
    private long totalCategories;
    private long totalSuppliers;
    private long totalWarehouses;
    private long lowStockProducts;

    private long totalPurchaseOrders;
    private long totalSalesOrders;

    private BigDecimal totalPurchaseAmount;
    private BigDecimal totalSalesAmount;
}