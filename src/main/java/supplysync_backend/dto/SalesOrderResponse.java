package supplysync_backend.dto;

import lombok.Builder;
import lombok.Data;
import supplysync_backend.entity.SalesOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SalesOrderResponse {
    private Long id;
    private String orderNumber;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private SalesOrderStatus status;
    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private List<SalesOrderItemResponse> items;
}