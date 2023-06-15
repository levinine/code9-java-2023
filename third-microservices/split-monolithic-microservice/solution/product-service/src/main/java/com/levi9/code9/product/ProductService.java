package com.levi9.code9.product;

import com.levi9.code9.product.exception.NotFoundException;
import com.levi9.code9.product.messaging.MessageFactory;
import com.levi9.code9.product.messaging.MessageService;
import com.levi9.code9.product.messaging.ProductsUserMessage;
import com.levi9.code9.users.dto.UserDto;
import com.levi9.code9.users.user.client.UserClient;
import feign.Feign;
import feign.gson.GsonDecoder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserClient userClient = Feign.builder()
            .decoder(new GsonDecoder())
            .target(UserClient.class, "http://localhost:8082");
    private final MessageService messageService;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findOneById(Long id) {
        return productRepository.getById(id);
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
    public void triggerWeeklyAvailableProductUpdate() {
        List<Product> allAvailableProduct = findAllAvailableProduct();
        List<UserDto> allUsersWhichAreSubscribed = userClient.getAllUsersWhichAreSubscribed();
        allUsersWhichAreSubscribed.forEach(userDto -> {
            ProductsUserMessage message = MessageFactory.createProductUserMessage(allAvailableProduct, userDto.getEmail());
            messageService.sendMessageToProductTopic(message);
        });

    }

}
