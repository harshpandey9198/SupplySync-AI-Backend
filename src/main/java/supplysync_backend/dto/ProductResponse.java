package supplysync_backend.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductResponse {

    private Long id;
    private String productCode;
    private String name;
    private String category;
    private String brand;
    private BigDecimal unitPrice;
    private Integer quantity;
    private Boolean active;
}