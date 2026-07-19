package supplysync_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import supplysync_backend.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductCode(String productCode);

    boolean existsByProductCode(String productCode);

    Optional<Product> findByName(String name);

    @Query("SELECT p FROM Product p WHERE p.quantity <= p.reorderLevel AND p.active = true")
    List<Product> findLowStockProducts();
}