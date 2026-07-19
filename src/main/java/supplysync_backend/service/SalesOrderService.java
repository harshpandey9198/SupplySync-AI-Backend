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
public class SalesOrderService {

    private final SalesOrderRepository salesOrderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public SalesOrderResponse createSalesOrder(SalesOrderRequest request) {

        if (salesOrderRepository.existsByOrderNumber(request.getOrderNumber())) {
            throw new RuntimeException("Sales order number already exists");
        }

        SalesOrder salesOrder = SalesOrder.builder()
                .orderNumber(request.getOrderNumber())
                .customerName(request.getCustomerName())
                .customerPhone(request.getCustomerPhone())
                .customerEmail(request.getCustomerEmail())
                .status(SalesOrderStatus.PENDING)
                .build();

        List<SalesOrderItem> items = request.getItems().stream().map(itemReq -> {

            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            Integer currentQuantity = product.getQuantity() == null ? 0 : product.getQuantity();

            if (currentQuantity < itemReq.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            product.setQuantity(currentQuantity - itemReq.getQuantity());
            productRepository.save(product);

            BigDecimal lineTotal = itemReq.getUnitPrice()
                    .multiply(BigDecimal.valueOf(itemReq.getQuantity()));

            return SalesOrderItem.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .quantity(itemReq.getQuantity())
                    .unitPrice(itemReq.getUnitPrice())
                    .lineTotal(lineTotal)
                    .salesOrder(salesOrder)
                    .build();

        }).toList();

        BigDecimal totalAmount = items.stream()
                .map(SalesOrderItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        salesOrder.setItems(items);
        salesOrder.setTotalAmount(totalAmount);

        return mapToResponse(salesOrderRepository.save(salesOrder));
    }

    public List<SalesOrderResponse> getAllSalesOrders() {
        return salesOrderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public SalesOrderResponse getSalesOrderById(Long id) {
        SalesOrder salesOrder = salesOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sales order not found"));

        return mapToResponse(salesOrder);
    }

    public SalesOrderResponse updateStatus(Long id, SalesOrderStatus status) {
        SalesOrder salesOrder = salesOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sales order not found"));

        salesOrder.setStatus(status);

        return mapToResponse(salesOrderRepository.save(salesOrder));
    }

    private SalesOrderResponse mapToResponse(SalesOrder salesOrder) {
        return SalesOrderResponse.builder()
                .id(salesOrder.getId())
                .orderNumber(salesOrder.getOrderNumber())
                .customerName(salesOrder.getCustomerName())
                .customerPhone(salesOrder.getCustomerPhone())
                .customerEmail(salesOrder.getCustomerEmail())
                .status(salesOrder.getStatus())
                .totalAmount(salesOrder.getTotalAmount())
                .orderDate(salesOrder.getOrderDate())
                .items(salesOrder.getItems() == null ? List.of() :
                        salesOrder.getItems().stream().map(item ->
                                SalesOrderItemResponse.builder()
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