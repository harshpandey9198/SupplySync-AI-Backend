package supplysync_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="sales_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;

    private String customerName;

    private String customerPhone;

    private String customerEmail;

    @Enumerated(EnumType.STRING)
    private SalesOrderStatus status;

    private BigDecimal totalAmount;

    private LocalDateTime orderDate;

    @OneToMany(
            mappedBy = "salesOrder",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<SalesOrderItem> items;

    @PrePersist
    public void prePersist(){

        orderDate = LocalDateTime.now();

        if(status==null){
            status=SalesOrderStatus.PENDING;
        }

    }

}