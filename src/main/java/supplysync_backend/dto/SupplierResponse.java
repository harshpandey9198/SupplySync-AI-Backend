package supplysync_backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierResponse {
    private Long id;
    private String supplierCode;
    private String name;
    private String contactPerson;
    private String phone;
    private String email;
    private String city;
    private String state;
    private Double rating;
    private Boolean active;
}