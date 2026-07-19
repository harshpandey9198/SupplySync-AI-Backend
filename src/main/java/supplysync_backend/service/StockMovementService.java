package supplysync_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import supplysync_backend.entity.Product;
import supplysync_backend.entity.StockMovement;
import supplysync_backend.repository.StockMovementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;

    // Save Stock Movement
    public void saveMovement(
            Product product,
            String movementType,
            Integer quantity,
            String remarks
    ) {

        StockMovement movement = StockMovement.builder()
                .product(product)
                .movementType(movementType)
                .quantity(quantity)
                .remarks(remarks)
                .build();

        stockMovementRepository.save(movement);
    }

    // Get All Stock Movements
    public List<StockMovement> getAllMovements() {
        return stockMovementRepository.findAllByOrderByMovementDateDesc();
    }

}