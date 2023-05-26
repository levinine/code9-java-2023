package com.levi9.code9.monolithic.product;

import com.levi9.code9.monolithic.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAllAvailableProduct() {
        return productRepository.findByQuantityGreaterThan(0);
    }

    public boolean isAvailable(String name) {
        return productRepository.existsByNameAndQuantityGreaterThan(name, 0);
    }

    public void createProduct(Product product) {
        productRepository.save(product);
    }


    public void updateProduct(Product product) {
        Optional<Product> existingOptionalProduct = productRepository.findByName(product.getName());
        if (existingOptionalProduct.isPresent()) {
            Product existingProduct = existingOptionalProduct.get();
            Product updatedProduct = Product.builder()
                    .name(product.getName())
                    .quantity(product.getQuantity())
                    .price(product.getPrice())
                    .id(existingProduct.getId()).build();
            productRepository.save(updatedProduct);
        } else {
            throw new NotFoundException(String.format("Product with name '%s' not exists", product.getName()));
        }
    }

    public void deleteProductById(Long id) {
       productRepository.deleteById(id);
    }
}
