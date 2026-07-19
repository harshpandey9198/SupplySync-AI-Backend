package supplysync_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import supplysync_backend.entity.StockMovement;

import java.util.List;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {

    List<StockMovement> findAllByOrderByMovementDateDesc();

}