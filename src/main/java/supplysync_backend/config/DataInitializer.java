package supplysync_backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import supplysync_backend.entity.*;
import supplysync_backend.repository.*;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {

        // ==========================
        // Default Admin
        // ==========================

        if (!userRepository.existsByEmail("admin@supplysync.com")) {

            User admin = User.builder()
                    .name("Admin")
                    .email("admin@supplysync.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .active(true)
                    .build();

            userRepository.save(admin);

            System.out.println("=======================================");
            System.out.println(" Default Admin User Created");
            System.out.println(" Email : admin@supplysync.com");
            System.out.println(" Password : admin123");
            System.out.println("=======================================");
        }

        // Sample Categories
        // ==========================

        if (!categoryRepository.existsByName("Electronics")) {

            categoryRepository.save(Category.builder()
                    .name("Electronics")
                    .description("Electronic Devices")
                    .active(true)
                    .build());

            categoryRepository.save(Category.builder()
                    .name("Furniture")
                    .description("Office Furniture")
                    .active(true)
                    .build());

            categoryRepository.save(Category.builder()
                    .name("Groceries")
                    .description("Daily Grocery Items")
                    .active(true)
                    .build());

            categoryRepository.save(Category.builder()
                    .name("Clothing")
                    .description("Fashion Products")
                    .active(true)
                    .build());

            categoryRepository.save(Category.builder()
                    .name("Stationery")
                    .description("Office Stationery")
                    .active(true)
                    .build());

            System.out.println("✅ Categories Inserted");
        }

        // ==========================
        // Sample Suppliers
        // ==========================

        if (!supplierRepository.existsBySupplierCode("SUP001")) {

            supplierRepository.save(Supplier.builder()
                    .supplierCode("SUP001")
                    .name("ABC Traders")
                    .contactPerson("Rahul Sharma")
                    .phone("9876543210")
                    .email("abc@gmail.com")
                    .city("Delhi")
                    .state("Delhi")
                    .address("Sector 18")
                    .rating(4.8)
                    .active(true)
                    .build());

            supplierRepository.save(Supplier.builder()
                    .supplierCode("SUP002")
                    .name("Tech World")
                    .contactPerson("Amit Singh")
                    .phone("9876543211")
                    .email("tech@gmail.com")
                    .city("Noida")
                    .state("UP")
                    .address("Sector 62")
                    .rating(4.5)
                    .active(true)
                    .build());

            supplierRepository.save(Supplier.builder()
                    .supplierCode("SUP003")
                    .name("Global Supplies")
                    .contactPerson("Neha Verma")
                    .phone("9876543212")
                    .email("global@gmail.com")
                    .city("Lucknow")
                    .state("UP")
                    .address("Hazratganj")
                    .rating(4.7)
                    .active(true)
                    .build());

            supplierRepository.save(Supplier.builder()
                    .supplierCode("SUP004")
                    .name("Prime Wholesale")
                    .contactPerson("Karan Gupta")
                    .phone("9876543213")
                    .email("prime@gmail.com")
                    .city("Kanpur")
                    .state("UP")
                    .address("Civil Lines")
                    .rating(4.4)
                    .active(true)
                    .build());

            supplierRepository.save(Supplier.builder()
                    .supplierCode("SUP005")
                    .name("Supply Hub")
                    .contactPerson("Rohit Jain")
                    .phone("9876543214")
                    .email("hub@gmail.com")
                    .city("Agra")
                    .state("UP")
                    .address("MG Road")
                    .rating(4.6)
                    .active(true)
                    .build());

            System.out.println("✅ Suppliers Inserted");
        }

        // ==========================
        // Sample Warehouses
        // ==========================

        if (!warehouseRepository.existsByWarehouseCode("WH001")) {

            warehouseRepository.save(Warehouse.builder()
                    .warehouseCode("WH001")
                    .name("Central Warehouse")
                    .managerName("Harsh Pandey")
                    .phone("9999999991")
                    .city("Noida")
                    .state("UP")
                    .address("Sector 63")
                    .capacity(10000)
                    .active(true)
                    .build());

            warehouseRepository.save(Warehouse.builder()
                    .warehouseCode("WH002")
                    .name("North Warehouse")
                    .managerName("Ravi Kumar")
                    .phone("9999999992")
                    .city("Delhi")
                    .state("Delhi")
                    .address("Rohini")
                    .capacity(8000)
                    .active(true)
                    .build());

            warehouseRepository.save(Warehouse.builder()
                    .warehouseCode("WH003")
                    .name("South Warehouse")
                    .managerName("Ankit Sharma")
                    .phone("9999999993")
                    .city("Lucknow")
                    .state("UP")
                    .address("Aliganj")
                    .capacity(6000)
                    .active(true)
                    .build());

            System.out.println("✅ Warehouses Inserted");
        }

        
        // Sample Products
        // ==========================

        if (!productRepository.existsByProductCode("PRD001")) {

            productRepository.save(Product.builder()
                    .productCode("PRD001")
                    .name("Dell Laptop")
                    .description("Dell Inspiron i5")
                    .category("Electronics")
                    .brand("Dell")
                    .unitPrice(new BigDecimal("65000"))
                    .quantity(25)
                    .reorderLevel(5)
                    .supplier("ABC Traders")
                    .warehouse("Central Warehouse")
                    .active(true)
                    .build());

            productRepository.save(Product.builder()
                    .productCode("PRD002")
                    .name("HP Laptop")
                    .description("HP Pavilion")
                    .category("Electronics")
                    .brand("HP")
                    .unitPrice(new BigDecimal("72000"))
                    .quantity(15)
                    .reorderLevel(5)
                    .supplier("Tech World")
                    .warehouse("Central Warehouse")
                    .active(true)
                    .build());

            productRepository.save(Product.builder()
                    .productCode("PRD003")
                    .name("Office Chair")
                    .description("Comfort Chair")
                    .category("Furniture")
                    .brand("Nilkamal")
                    .unitPrice(new BigDecimal("4500"))
                    .quantity(40)
                    .reorderLevel(10)
                    .supplier("Prime Wholesale")
                    .warehouse("North Warehouse")
                    .active(true)
                    .build());

            productRepository.save(Product.builder()
                    .productCode("PRD004")
                    .name("Office Table")
                    .description("Wooden Table")
                    .category("Furniture")
                    .brand("Godrej")
                    .unitPrice(new BigDecimal("8500"))
                    .quantity(20)
                    .reorderLevel(5)
                    .supplier("Prime Wholesale")
                    .warehouse("North Warehouse")
                    .active(true)
                    .build());

            productRepository.save(Product.builder()
                    .productCode("PRD005")
                    .name("Rice 25Kg")
                    .description("Premium Rice")
                    .category("Groceries")
                    .brand("India Gate")
                    .unitPrice(new BigDecimal("1800"))
                    .quantity(100)
                    .reorderLevel(20)
                    .supplier("Supply Hub")
                    .warehouse("South Warehouse")
                    .active(true)
                    .build());

            productRepository.save(Product.builder()
                    .productCode("PRD006")
                    .name("Sugar 10Kg")
                    .description("Refined Sugar")
                    .category("Groceries")
                    .brand("Madhur")
                    .unitPrice(new BigDecimal("600"))
                    .quantity(80)
                    .reorderLevel(15)
                    .supplier("Supply Hub")
                    .warehouse("South Warehouse")
                    .active(true)
                    .build());

            productRepository.save(Product.builder()
                    .productCode("PRD007")
                    .name("T-Shirt")
                    .description("Cotton T-Shirt")
                    .category("Clothing")
                    .brand("Puma")
                    .unitPrice(new BigDecimal("999"))
                    .quantity(60)
                    .reorderLevel(10)
                    .supplier("Global Supplies")
                    .warehouse("Central Warehouse")
                    .active(true)
                    .build());

            productRepository.save(Product.builder()
                    .productCode("PRD008")
                    .name("Jeans")
                    .description("Blue Denim")
                    .category("Clothing")
                    .brand("Levis")
                    .unitPrice(new BigDecimal("2499"))
                    .quantity(45)
                    .reorderLevel(8)
                    .supplier("Global Supplies")
                    .warehouse("Central Warehouse")
                    .active(true)
                    .build());

            productRepository.save(Product.builder()
                    .productCode("PRD009")
                    .name("Notebook")
                    .description("200 Pages")
                    .category("Stationery")
                    .brand("Classmate")
                    .unitPrice(new BigDecimal("120"))
                    .quantity(200)
                    .reorderLevel(50)
                    .supplier("ABC Traders")
                    .warehouse("South Warehouse")
                    .active(true)
                    .build());

            productRepository.save(Product.builder()
                    .productCode("PRD010")
                    .name("Pen")
                    .description("Blue Ball Pen")
                    .category("Stationery")
                    .brand("Cello")
                    .unitPrice(new BigDecimal("20"))
                    .quantity(500)
                    .reorderLevel(100)
                    .supplier("ABC Traders")
                    .warehouse("South Warehouse")
                    .active(true)
                    .build());

            System.out.println("✅ Sample Products Inserted");
        }

        System.out.println("=======================================");
        System.out.println("✅ SupplySync Sample Data Loaded");
        System.out.println("=======================================");
    }
}