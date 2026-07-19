package supplysync_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import supplysync_backend.dto.*;
import supplysync_backend.entity.*;
import supplysync_backend.repository.*;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public PurchaseOrderResponse createPurchaseOrder(PurchaseOrderRequest request) {

        if (purchaseOrderRepository.existsByPoNumber(request.getPoNumber())) {
            throw new RuntimeException("Purchase order number already exists");
        }

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .poNumber(request.getPoNumber())
                .supplierId(supplier.getId())
                .supplierName(supplier.getName())
                .status(PurchaseOrderStatus.PENDING)
                .build();

        List<PurchaseOrderItem> items = request.getItems().stream().map(itemReq -> {

            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            BigDecimal lineTotal = itemReq.getUnitPrice()
                    .multiply(BigDecimal.valueOf(itemReq.getQuantity()));

            return PurchaseOrderItem.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .quantity(itemReq.getQuantity())
                    .unitPrice(itemReq.getUnitPrice())
                    .lineTotal(lineTotal)
                    .purchaseOrder(purchaseOrder)
                    .build();

        }).toList();

        BigDecimal totalAmount = items.stream()
                .map(PurchaseOrderItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        purchaseOrder.setItems(items);
        purchaseOrder.setTotalAmount(totalAmount);

        PurchaseOrder saved = purchaseOrderRepository.save(purchaseOrder);

        return mapToResponse(saved);
    }

    public List<PurchaseOrderResponse> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public PurchaseOrderResponse getPurchaseOrderById(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase order not found"));

        return mapToResponse(purchaseOrder);
    }

    @Transactional
    public PurchaseOrderResponse updateStatus(Long id, PurchaseOrderStatus status) {

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase order not found"));

        if (status == PurchaseOrderStatus.RECEIVED
                && purchaseOrder.getStatus() != PurchaseOrderStatus.RECEIVED) {

            for (PurchaseOrderItem item : purchaseOrder.getItems()) {

                Product product = productRepository.findById(item.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                Integer currentQuantity = product.getQuantity() == null ? 0 : product.getQuantity();
                product.setQuantity(currentQuantity + item.getQuantity());

                productRepository.save(product);
            }
        }

        purchaseOrder.setStatus(status);

        PurchaseOrder saved = purchaseOrderRepository.save(purchaseOrder);

        return mapToResponse(saved);
    }

    private PurchaseOrderResponse mapToResponse(PurchaseOrder po) {
        return PurchaseOrderResponse.builder()
                .id(po.getId())
                .poNumber(po.getPoNumber())
                .supplierId(po.getSupplierId())
                .supplierName(po.getSupplierName())
                .status(po.getStatus())
                .totalAmount(po.getTotalAmount())
                .orderDate(po.getOrderDate())
                .items(po.getItems() == null ? List.of() :
                        po.getItems().stream().map(item ->
                                PurchaseOrderItemResponse.builder()
                                        .productId(item.getProductId())
                                        .productName(item.getProductName())
                                        .quantity(item.getQuantity())
                                        .unitPrice(item.getUnitPrice())
                                        .lineTotal(item.getLineTotal())
                                        .build()
                        ).toList()
                )
                .build();
    }
}