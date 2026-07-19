package supplysync_backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class SalesOrderRequest {

    private String orderNumber;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private List<SalesOrderItemRequest> items;
}