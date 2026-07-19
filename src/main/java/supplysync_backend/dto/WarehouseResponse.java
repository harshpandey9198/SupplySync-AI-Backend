package supplysync_backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WarehouseResponse {
    private Long id;
    private String warehouseCode;
    private String name;
    private String managerName;
    private String phone;
    private String city;
    private String state;
    private Integer capacity;
    private Boolean active;
}