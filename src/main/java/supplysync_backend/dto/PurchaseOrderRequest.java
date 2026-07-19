package supplysync_backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class PurchaseOrderRequest {
    private String poNumber;
    private Long supplierId;
    private List<PurchaseOrderItemRequest> items;
}