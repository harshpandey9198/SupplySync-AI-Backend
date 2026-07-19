package supplysync_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import supplysync_backend.entity.SalesOrderItem;

public interface SalesOrderItemRepository extends JpaRepository<SalesOrderItem,Long> {

}