package supplysync_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import supplysync_backend.entity.PurchaseOrder;

import java.math.BigDecimal;
import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    boolean existsByPoNumber(String poNumber);

    @Query("SELECT COALESCE(SUM(p.totalAmount), 0) FROM PurchaseOrder p")
    BigDecimal getTotalPurchaseAmount();

    // Latest 5 Purchase Orders
    List<PurchaseOrder> findTop5ByOrderByIdDesc();

}