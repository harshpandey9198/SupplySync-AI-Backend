package supplysync_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "purchase_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String poNumber;
    private Long supplierId;
    private String supplierName;

    @Enumerated(EnumType.STRING)
    private PurchaseOrderStatus status;

    private BigDecimal totalAmount;
    private LocalDateTime orderDate;

    @OneToMany(
        mappedBy = "purchaseOrder",
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER
)
    private List<PurchaseOrderItem> items;

    @PrePersist
    public void prePersist() {
        orderDate = LocalDateTime.now();
        if (status == null) status = PurchaseOrderStatus.PENDING;
    }
}