package supplysync_backend.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SalesOrderItemRequest {

    private Long productId;
    private Integer quantity;
    private BigDecimal unitPrice;

}