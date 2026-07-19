package supplysync_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import supplysync_backend.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    boolean existsByWarehouseCode(String warehouseCode);
}