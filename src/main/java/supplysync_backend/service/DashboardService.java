package supplysync_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import supplysync_backend.dto.DashboardStatsResponse;
import supplysync_backend.dto.ProductResponse;
import supplysync_backend.entity.Product;
import supplysync_backend.entity.PurchaseOrder;
import supplysync_backend.repository.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;
    private final WarehouseRepository warehouseRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SalesOrderRepository salesOrderRepository;
    

    // Dashboard Statistics
    public DashboardStatsResponse getStats() {

        long lowStock = productRepository.findLowStockProducts().size();

        return DashboardStatsResponse.builder()
                .totalProducts(productRepository.count())
                .totalCategories(categoryRepository.count())
                .totalSuppliers(supplierRepository.count())
                .totalWarehouses(warehouseRepository.count())
                .lowStockProducts(lowStock)
                .totalPurchaseOrders(purchaseOrderRepository.count())
                .totalSalesOrders(salesOrderRepository.count())
                .totalPurchaseAmount(purchaseOrderRepository.getTotalPurchaseAmount())
                .totalSalesAmount(salesOrderRepository.getTotalSalesAmount())
                .build();
    }

    // Low Stock Products
    public List<ProductResponse> getLowStockProducts() {

        return productRepository.findLowStockProducts()
                .stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    // Recent Purchase Orders
    public List<PurchaseOrder> getRecentOrders() {

        return purchaseOrderRepository.findTop5ByOrderByIdDesc();

    }

    private ProductResponse mapToProductResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .productCode(product.getProductCode())
                .name(product.getName())
                .category(product.getCategory())
                .brand(product.getBrand())
                .unitPrice(product.getUnitPrice())
                .quantity(product.getQuantity())
                .active(product.getActive())
                .build();
    }
    public Map<String, Object> getAnalytics() {

    Map<String, Object> data = new HashMap<>();

    List<Map<String, Object>> monthly = new ArrayList<>();

    monthly.add(Map.of(
            "month", "Jan",
            "purchase", 40000,
            "sales", 55000));

    monthly.add(Map.of(
            "month", "Feb",
            "purchase", 52000,
            "sales", 67000));

    monthly.add(Map.of(
            "month", "Mar",
            "purchase", 35000,
            "sales", 60000));

    monthly.add(Map.of(
            "month", "Apr",
            "purchase", 48000,
            "sales", 72000));

    monthly.add(Map.of(
            "month", "May",
            "purchase", 70000,
            "sales", 88000));

    monthly.add(Map.of(
            "month", "Jun",
            "purchase", 62000,
            "sales", 91000));

    data.put("monthly", monthly);

    return data;
}
}