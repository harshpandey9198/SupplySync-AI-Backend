package supplysync_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import supplysync_backend.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    boolean existsBySupplierCode(String supplierCode);
}