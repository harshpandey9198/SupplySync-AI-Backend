package supplysync_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import supplysync_backend.entity.SalesOrder;

import java.math.BigDecimal;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {

    boolean existsByOrderNumber(String orderNumber);

    @Query("SELECT COALESCE(SUM(s.totalAmount), 0) FROM SalesOrder s")
    BigDecimal getTotalSalesAmount();
}