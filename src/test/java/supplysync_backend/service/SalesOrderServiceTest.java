package supplysync_backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import supplysync_backend.dto.SalesOrderItemRequest;
import supplysync_backend.dto.SalesOrderRequest;
import supplysync_backend.entity.Product;
import supplysync_backend.repository.ProductRepository;
import supplysync_backend.repository.SalesOrderRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for SalesOrderService.
 * Focuses on the core business rules: duplicate order numbers,
 * insufficient stock handling, and correct stock deduction on order creation.
 */
@ExtendWith(MockitoExtension.class)
class SalesOrderServiceTest {

    @Mock
    private SalesOrderRepository salesOrderRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SalesOrderService salesOrderService;

    private Product product;
    private SalesOrderRequest request;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .id(1L)
                .productCode("PRD001")
                .name("Test Product")
                .unitPrice(new BigDecimal("100.00"))
                .quantity(10)
                .build();

        SalesOrderItemRequest itemRequest = new SalesOrderItemRequest();
        itemRequest.setProductId(1L);
        itemRequest.setQuantity(2);
        itemRequest.setUnitPrice(new BigDecimal("100.00"));

        request = new SalesOrderRequest();
        request.setOrderNumber("SO2001");
        request.setCustomerName("Test Customer");
        request.setCustomerPhone("9999999999");
        request.setCustomerEmail("test@example.com");
        request.setItems(List.of(itemRequest));
    }

    @Test
    void createSalesOrder_ShouldThrowException_WhenOrderNumberAlreadyExists() {
        when(salesOrderRepository.existsByOrderNumber("SO2001")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> salesOrderService.createSalesOrder(request));

        // Product stock must never be touched if the order itself is rejected
        verify(productRepository, never()).save(any());
    }

    @Test
    void createSalesOrder_ShouldThrowException_WhenInsufficientStock() {
        SalesOrderItemRequest bigOrderItem = new SalesOrderItemRequest();
        bigOrderItem.setProductId(1L);
        bigOrderItem.setQuantity(999); // way more than available stock (10)
        bigOrderItem.setUnitPrice(new BigDecimal("100.00"));
        request.setItems(List.of(bigOrderItem));

        when(salesOrderRepository.existsByOrderNumber("SO2001")).thenReturn(false);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        assertThrows(RuntimeException.class, () -> salesOrderService.createSalesOrder(request));

        // Stock should remain unchanged since the order should fail before saving
        assertEquals(10, product.getQuantity());
        verify(salesOrderRepository, never()).save(any());
    }

    @Test
    void createSalesOrder_ShouldDeductStock_WhenOrderIsValid() {
        when(salesOrderRepository.existsByOrderNumber("SO2001")).thenReturn(false);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(salesOrderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        salesOrderService.createSalesOrder(request);

        // Ordered 2 units out of 10 -> 8 should remain
        assertEquals(8, product.getQuantity());
        verify(productRepository, times(1)).save(product);
        verify(salesOrderRepository, times(1)).save(any());
    }
}
