package supplysync_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import supplysync_backend.entity.Product;
import supplysync_backend.entity.PurchaseOrder;
import supplysync_backend.entity.SalesOrder;
import supplysync_backend.repository.ProductRepository;
import supplysync_backend.repository.PurchaseOrderRepository;
import supplysync_backend.repository.SalesOrderRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ProductRepository productRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SalesOrderRepository salesOrderRepository;

    public Map<String, Object> getDashboardReport() {

        Map<String, Object> report = new HashMap<>();

        List<Product> products = productRepository.findAll();
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        List<SalesOrder> salesOrders = salesOrderRepository.findAll();

        report.put("totalProducts", products.size());
        report.put("totalPurchaseOrders", purchaseOrders.size());
        report.put("totalSalesOrders", salesOrders.size());

        report.put(
                "lowStockProducts",
                productRepository.findLowStockProducts().size()
        );

        return report;
    }

}