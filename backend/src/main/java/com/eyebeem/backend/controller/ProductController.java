package com.eyebeem.backend.controller;

import com.eyebeem.backend.dto.ProductDto;
import com.eyebeem.backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /** Fetch all three products (e-CAT, e-DOG, e-HAMSTER) with current quantity. */
    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Integer productId) {
        return ResponseEntity.ok(productService.getByProductId(productId));
    }
}
