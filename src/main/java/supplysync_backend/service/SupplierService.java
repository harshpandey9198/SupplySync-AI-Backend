package supplysync_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import supplysync_backend.dto.SupplierRequest;
import supplysync_backend.dto.SupplierResponse;
import supplysync_backend.entity.Supplier;
import supplysync_backend.repository.SupplierRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierResponse addSupplier(SupplierRequest request) {
        if (supplierRepository.existsBySupplierCode(request.getSupplierCode())) {
            throw new RuntimeException("Supplier code already exists");
        }

        Supplier supplier = Supplier.builder()
                .supplierCode(request.getSupplierCode())
                .name(request.getName())
                .contactPerson(request.getContactPerson())
                .phone(request.getPhone())
                .email(request.getEmail())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .rating(request.getRating())
                .active(true)
                .build();

        return mapToResponse(supplierRepository.save(supplier));
    }

    public List<SupplierResponse> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public SupplierResponse getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        return mapToResponse(supplier);
    }

    public SupplierResponse updateSupplier(Long id, SupplierRequest request) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        supplier.setName(request.getName());
        supplier.setContactPerson(request.getContactPerson());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());
        supplier.setAddress(request.getAddress());
        supplier.setCity(request.getCity());
        supplier.setState(request.getState());
        supplier.setRating(request.getRating());

        return mapToResponse(supplierRepository.save(supplier));
    }

    public String deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        supplier.setActive(false);
        supplierRepository.save(supplier);

        return "Supplier deleted successfully";
    }

    private SupplierResponse mapToResponse(Supplier supplier) {
        return SupplierResponse.builder()
                .id(supplier.getId())
                .supplierCode(supplier.getSupplierCode())
                .name(supplier.getName())
                .contactPerson(supplier.getContactPerson())
                .phone(supplier.getPhone())
                .email(supplier.getEmail())
                .city(supplier.getCity())
                .state(supplier.getState())
                .rating(supplier.getRating())
                .active(supplier.getActive())
                .build();
    }
}