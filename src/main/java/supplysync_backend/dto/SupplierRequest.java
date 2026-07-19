package supplysync_backend.dto;

import lombok.Data;

@Data
public class SupplierRequest {
    private String supplierCode;
    private String name;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String state;
    private Double rating;
}