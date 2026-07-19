package supplysync_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import supplysync_backend.service.ExcelExportService;

@RestController
@RequestMapping("/api/excel")
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelExportService excelExportService;

    @GetMapping("/products")
    public ResponseEntity<byte[]> exportProducts() throws Exception {

        byte[] excel = excelExportService.exportProducts();

        return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=products.xlsx")
        .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
        .body(excel);
    }
}