package supplysync_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import supplysync_backend.dto.WarehouseRequest;
import supplysync_backend.dto.WarehouseResponse;
import supplysync_backend.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping
    public WarehouseResponse addWarehouse(@RequestBody WarehouseRequest request) {
        return warehouseService.addWarehouse(request);
    }

    @GetMapping
    public List<WarehouseResponse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/{id}")
    public WarehouseResponse getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id);
    }

    @PutMapping("/{id}")
    public WarehouseResponse updateWarehouse(@PathVariable Long id, @RequestBody WarehouseRequest request) {
        return warehouseService.updateWarehouse(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteWarehouse(@PathVariable Long id) {
        return warehouseService.deleteWarehouse(id);
    }
}