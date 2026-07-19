package supplysync_backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    private String productCode;
    private String name;
    private String description;
    private String category;
    private String brand;
    private BigDecimal unitPrice;
    private Integer quantity;
    private Integer reorderLevel;
    private String supplier; 
    private String warehouse;
}