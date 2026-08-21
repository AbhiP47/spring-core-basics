package com.abhinav.unitTesting.service;

import com.abhinav.unitTesting.entity.Product;
import com.abhinav.unitTesting.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private  ProductService productService = new ProductService(productRepository);

    @Test
    void shouldReturnProductIfExist()
    {

        // Arrange
        Product product = new Product(1L , "Laptop" , 50000 , 10);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        // Act
        Product result = productService.getProductById(1L);

        // Assertions
        Assertions.assertEquals(1L,result.getId());
        Assertions.assertEquals("Laptop",result.getName());

        verify(productRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenProductNotExists()
    {
        when(productRepository.findById(99L))
                .thenReturn(Optional.empty());

        RuntimeException exception =
                assertThrows(RuntimeException.class,
                        ()-> productService.getProductById(99L));

        Assertions.assertEquals("Product not found: 99",
                exception.getMessage());

        verify(productRepository).findById(99L);
    }

    @Test
    void shouldCreateProductWhenNameIsUnique()
    {
        Product request =
                new Product(null , "keyboard" , 200 , 5);
        Product savedProduct =
                new Product(10L , "Keyboard" , 200 , 5);

        when(productRepository.existsByName("keyboard"))
                .thenReturn(false);
        when(productRepository.save(request))
                .thenReturn(savedProduct);

        Product result =
                productService.createProduct(request);

        Assertions.assertEquals(savedProduct , result);
    }

    @Test
    void shouldRejectProductWhenNameAlreadyExists() {

        // Arrange
        Product request =
                new Product(null, "Keyboard", 200, 5);

        when(productRepository.existsByName("Keyboard"))
                .thenReturn(true);

        // Act and Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> productService.createProduct(request)
        );

        verify(productRepository)
                .existsByName("Keyboard");

        verify(productRepository, never())
                .save(any(Product.class));
    }
}
