package supplysync_backend.dto;

import lombok.Data;
import supplysync_backend.entity.MovementType;

@Data
public class StockMovementRequest {
    private Long productId;
    private MovementType movementType;
    private Integer quantity;
    private String reason;
    private String referenceNo;
}