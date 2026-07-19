package supplysync_backend.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import supplysync_backend.entity.Product;
import supplysync_backend.repository.ProductRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelExportService {

    private final ProductRepository productRepository;

    public byte[] exportProducts() throws IOException {

        List<Product> products = productRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Products");

        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Code");
        header.createCell(2).setCellValue("Name");
        header.createCell(3).setCellValue("Category");
        header.createCell(4).setCellValue("Brand");
        header.createCell(5).setCellValue("Price");
        header.createCell(6).setCellValue("Quantity");

        int rowNo = 1;

        for (Product product : products) {

            Row row = sheet.createRow(rowNo++);

            row.createCell(0).setCellValue(product.getId());
            row.createCell(1).setCellValue(product.getProductCode());
            row.createCell(2).setCellValue(product.getName());
            row.createCell(3).setCellValue(product.getCategory());
            row.createCell(4).setCellValue(product.getBrand());
            row.createCell(5).setCellValue(product.getUnitPrice().doubleValue());
            row.createCell(6).setCellValue(product.getQuantity());

        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return out.toByteArray();
    }
}