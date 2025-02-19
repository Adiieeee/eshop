package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = new Product();
        sampleProduct.setProductId("1");
        sampleProduct.setProductName("Test Product");
        sampleProduct.setProductQuantity(10);
    }

    @Test
    void testCreate() {
        // Arrange
        when(productRepository.create(any(Product.class))).thenReturn(sampleProduct);

        // Act
        Product createdProduct = productService.create(sampleProduct);

        // Assert
        assertNotNull(createdProduct);
        assertEquals(sampleProduct.getProductName(), createdProduct.getProductName());
        verify(productRepository, times(1)).create(sampleProduct);
    }

    @Test
    void testFindAll() {
        // Arrange
        List<Product> productList = Arrays.asList(sampleProduct);
        Iterator<Product> iterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(iterator);

        // Act
        List<Product> retrievedProducts = productService.findAll();

        // Assert
        assertEquals(1, retrievedProducts.size());
        assertEquals(sampleProduct.getProductName(), retrievedProducts.get(0).getProductName());
    }

    @Test
    void testFindById_ProductExists() {
        // Arrange
        when(productRepository.findById("1")).thenReturn(Optional.of(sampleProduct));

        // Act
        Optional<Product> foundProduct = productService.findById("1");

        // Assert
        assertTrue(foundProduct.isPresent());
        assertEquals(sampleProduct.getProductName(), foundProduct.get().getProductName());
    }

    @Test
    void testFindById_ProductNotExists() {
        // Arrange
        when(productRepository.findById("99")).thenReturn(Optional.empty());

        // Act
        Optional<Product> foundProduct = productService.findById("99");

        // Assert
        assertFalse(foundProduct.isPresent());
    }

    @Test
    void testUpdateProduct_ProductExists() {
        // Arrange
        Product updatedProduct = new Product();
        updatedProduct.setProductId("1");
        updatedProduct.setProductName("Updated Name");
        updatedProduct.setProductQuantity(20);

        when(productRepository.findById("1")).thenReturn(Optional.of(sampleProduct));

        // Act
        Product result = productService.updateProduct("1", updatedProduct);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Name", result.getProductName());
        assertEquals(20, result.getProductQuantity());
    }

    @Test
    void testUpdateProduct_ProductNotExists() {
        // Arrange
        Product updatedProduct = new Product();
        updatedProduct.setProductId("99");
        updatedProduct.setProductName("Updated Name");
        updatedProduct.setProductQuantity(20);

        when(productRepository.findById("99")).thenReturn(Optional.empty());

        // Act
        Product result = productService.updateProduct("99", updatedProduct);

        // Assert
        assertNull(result);
    }

    @Test
    void testDeleteProduct_Success() {
        // Arrange
        when(productRepository.delete("1")).thenReturn(true);

        // Act
        boolean isDeleted = productService.delete("1");

        // Assert
        assertTrue(isDeleted);
        verify(productRepository, times(1)).delete("1");
    }

    @Test
    void testDeleteProduct_Failure() {
        // Arrange
        when(productRepository.delete("99")).thenReturn(false);

        // Act
        boolean isDeleted = productService.delete("99");

        // Assert
        assertFalse(isDeleted);
    }
}
