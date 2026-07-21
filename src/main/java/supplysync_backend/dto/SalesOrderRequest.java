package supplysync_backend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Data
public class SalesOrderRequest {

    @NotBlank(message = "Order number is required")
    private String orderNumber;

    @NotBlank(message = "Customer name is required")
    private String customerName;

    private String customerPhone;

    @Email(message = "Customer email must be valid")
    private String customerEmail;

    @NotEmpty(message = "Sales order must have at least one item")
    @Valid
    private List<SalesOrderItemRequest> items;
}