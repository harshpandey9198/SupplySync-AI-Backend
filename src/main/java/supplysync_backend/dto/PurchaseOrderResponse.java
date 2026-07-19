package supplysync_backend.dto;

import lombok.Builder;
import lombok.Data;
import supplysync_backend.entity.PurchaseOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PurchaseOrderResponse {
    private Long id;
    private String poNumber;
    private Long supplierId;
    private String supplierName;
    private PurchaseOrderStatus status;
    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private List<PurchaseOrderItemResponse> items;
}