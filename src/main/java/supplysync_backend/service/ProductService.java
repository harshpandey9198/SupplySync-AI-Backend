package supplysync_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import supplysync_backend.dto.ProductRequest;
import supplysync_backend.dto.ProductResponse;
import supplysync_backend.entity.Product;
import supplysync_backend.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse addProduct(ProductRequest request) {
        if (productRepository.existsByProductCode(request.getProductCode())) {
            throw new RuntimeException("Product code already exists");
        }

        Product product = Product.builder()
                .productCode(request.getProductCode())
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .brand(request.getBrand())
                .unitPrice(request.getUnitPrice())
                .quantity(request.getQuantity())
                .reorderLevel(request.getReorderLevel())
                .supplier(request.getSupplier())
                .warehouse(request.getWarehouse())
                .active(true)
                .build();

        return mapToResponse(productRepository.save(product));
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToResponse(product);
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategory(request.getCategory());
        product.setBrand(request.getBrand());
        product.setUnitPrice(request.getUnitPrice());
        product.setQuantity(request.getQuantity());
        product.setReorderLevel(request.getReorderLevel());
        product.setSupplier(request.getSupplier());
        product.setWarehouse(request.getWarehouse());

        return mapToResponse(productRepository.save(product));
    }

    public String deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setActive(false);
        productRepository.save(product);

        return "Product deleted successfully";
    }

    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .productCode(product.getProductCode())
                .name(product.getName())
                .category(product.getCategory())
                .brand(product.getBrand())
                .unitPrice(product.getUnitPrice())
                .quantity(product.getQuantity())
                .active(product.getActive())
                .build();
    }
}