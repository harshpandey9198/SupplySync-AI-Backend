package supplysync_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_movements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private String movementType;

    @Column(nullable = false)
    private Integer quantity;

    private String remarks;

    private LocalDateTime movementDate;

    @PrePersist
    public void prePersist() {
        movementDate = LocalDateTime.now();
    }
}