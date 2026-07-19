package supplysync_backend.dto;

import lombok.Builder;
import lombok.Data;
import supplysync_backend.entity.MovementType;

import java.time.LocalDateTime;

@Data
@Builder
public class StockMovementResponse {

    private Long id;
    private Long productId;
    private String productCode;
    private String productName;
    private MovementType movementType;
    private Integer quantity;
    private String reason;
    private String referenceNo;
    private LocalDateTime movementDate;

}