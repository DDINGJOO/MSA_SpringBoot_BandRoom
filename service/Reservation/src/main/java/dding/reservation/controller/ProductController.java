package dding.reservation.controller;


import dding.reservation.dto.request.ProductCreateRequest;
import dding.reservation.dto.request.ProductUpdateRequest;
import dding.reservation.dto.response.ProductResponse;
import dding.reservation.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @PostMapping("/{BandRoomId}")
    public ResponseEntity<ProductResponse> createProduct(@PathVariable String BandRoomId, @RequestBody ProductCreateRequest req)
    {
        return ResponseEntity.ok(productService.createProduct(BandRoomId, req));
    }

    @GetMapping("/{ProductId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable String ProductId)
    {
        return ResponseEntity.ok(productService.getProductByBandRoomId(ProductId));
    }

    @GetMapping("/all/{BandRoomId}")
    public ResponseEntity<List<ProductResponse>> getAllProduct(@PathVariable String BandRoomId )
    {
        return ResponseEntity.ok(productService.getAllProductsByBandRoomId(BandRoomId));
    }

    @PutMapping("/update/{ProductId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable String ProductId, @RequestBody ProductUpdateRequest req)
    {
        return ResponseEntity.ok(productService.updateProduct(ProductId, req));
    }
}
