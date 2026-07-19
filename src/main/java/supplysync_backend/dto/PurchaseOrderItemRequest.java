package supplysync_backend.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PurchaseOrderItemRequest {
    private Long productId;
    private Integer quantity;
    private BigDecimal unitPrice;
}