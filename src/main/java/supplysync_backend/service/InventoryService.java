package supplysync_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import supplysync_backend.entity.Product;
import supplysync_backend.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final ProductRepository productRepository;
    private final StockMovementService stockMovementService;

    // Get Complete Inventory
    public List<Product> getInventory() {
        return productRepository.findAll();
    }

    // Get Low Stock Products
    public List<Product> getLowStock() {
        return productRepository.findLowStockProducts();
    }

    // Stock In
    public Product stockIn(Long productId, Integer quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        int currentQty = product.getQuantity() == null
                ? 0
                : product.getQuantity();

        product.setQuantity(currentQty + quantity);

        Product savedProduct = productRepository.save(product);

        // Save Stock Movement History
        stockMovementService.saveMovement(
                savedProduct,
                "STOCK_IN",
                quantity,
                "Stock Added"
        );

        return savedProduct;
    }

    // Stock Out
    public Product stockOut(Long productId, Integer quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        int currentQty = product.getQuantity() == null
                ? 0
                : product.getQuantity();

        if (currentQty < quantity) {
            throw new RuntimeException("Insufficient Stock");
        }

        product.setQuantity(currentQty - quantity);

        Product savedProduct = productRepository.save(product);

        // Save Stock Movement History
        stockMovementService.saveMovement(
                savedProduct,
                "STOCK_OUT",
                quantity,
                "Stock Removed"
        );

        return savedProduct;
    }
}