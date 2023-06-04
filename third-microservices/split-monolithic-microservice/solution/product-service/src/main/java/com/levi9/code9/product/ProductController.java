package com.levi9.code9.product;

import com.levi9.code9.product.dto.ProductDto;
import com.levi9.code9.product.dto.ProductMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/products")
@Slf4j
@AllArgsConstructor
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAll() {
        return productService.findAll()
                .stream()
                .map(productMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto getOne(@PathVariable("id") Long id) {
        return productMapper.entityToDto(productService.findOneById(id));
    }

    @GetMapping("/available")
    public List<ProductDto> getAllAvailableProducts() {
        return productService.findAllAvailableProduct()
                .stream()
                .map(productMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/available/{name}")
    public ResponseEntity<?> isAvailableProduct(@PathVariable("name") String name) {
        boolean available = productService.isAvailable(name);
        if (available)
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public void createProduct(@RequestBody final ProductDto productDetails) {
        productService.createProduct(productMapper.dtoToEntity(productDetails));
    }


    @PutMapping
    public void updateProduct(@RequestBody final ProductDto updateDetails) {
        productService.updateProduct(productMapper.dtoToEntity(updateDetails));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") final Long id) {
        productService.deleteProductById(id);
    }

    @PostMapping("/weeklyOverview")
    public void triggerWeeklyAvailableProductOverview() {
        productService.triggerWeeklyAvailableProductUpdate();
    }
}
