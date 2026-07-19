package supplysync_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import supplysync_backend.dto.WarehouseRequest;
import supplysync_backend.dto.WarehouseResponse;
import supplysync_backend.entity.Warehouse;
import supplysync_backend.repository.WarehouseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseResponse addWarehouse(WarehouseRequest request) {
        if (warehouseRepository.existsByWarehouseCode(request.getWarehouseCode())) {
            throw new RuntimeException("Warehouse code already exists");
        }

        Warehouse warehouse = Warehouse.builder()
                .warehouseCode(request.getWarehouseCode())
                .name(request.getName())
                .managerName(request.getManagerName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .capacity(request.getCapacity())
                .active(true)
                .build();

        return mapToResponse(warehouseRepository.save(warehouse));
    }

    public List<WarehouseResponse> getAllWarehouses() {
        return warehouseRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public WarehouseResponse getWarehouseById(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        return mapToResponse(warehouse);
    }

    public WarehouseResponse updateWarehouse(Long id, WarehouseRequest request) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        warehouse.setName(request.getName());
        warehouse.setManagerName(request.getManagerName());
        warehouse.setPhone(request.getPhone());
        warehouse.setAddress(request.getAddress());
        warehouse.setCity(request.getCity());
        warehouse.setState(request.getState());
        warehouse.setCapacity(request.getCapacity());

        return mapToResponse(warehouseRepository.save(warehouse));
    }

    public String deleteWarehouse(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        warehouse.setActive(false);
        warehouseRepository.save(warehouse);

        return "Warehouse deleted successfully";
    }

    private WarehouseResponse mapToResponse(Warehouse warehouse) {
        return WarehouseResponse.builder()
                .id(warehouse.getId())
                .warehouseCode(warehouse.getWarehouseCode())
                .name(warehouse.getName())
                .managerName(warehouse.getManagerName())
                .phone(warehouse.getPhone())
                .city(warehouse.getCity())
                .state(warehouse.getState())
                .capacity(warehouse.getCapacity())
                .active(warehouse.getActive())
                .build();
    }
}