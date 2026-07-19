package supplysync_backend.dto;

import lombok.Data;

@Data
public class WarehouseRequest {
    private String warehouseCode;
    private String name;
    private String managerName;
    private String phone;
    private String address;
    private String city;
    private String state;
    private Integer capacity;
}