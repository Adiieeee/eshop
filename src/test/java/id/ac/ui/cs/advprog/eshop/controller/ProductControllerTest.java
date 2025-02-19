package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
        assertEquals("createProduct", viewName);
    }

    @Test
    void testCreateProductPost_WithValidProduct() {
        Product product = new Product();
        product.setProductQuantity(10);
        String viewName = productController.createProductPost(product, model);
        verify(productService, times(1)).create(product);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testCreateProductPost_WithNegativeQuantity() {
        Product product = new Product();
        product.setProductQuantity(-1);
        String viewName = productController.createProductPost(product, model);
        verify(model, times(1)).addAttribute("error", "Quantity tidak boleh negatif");
        assertEquals("createProduct", viewName);
    }

    @Test
    void testProductListPage() {
        when(productService.findAll()).thenReturn(List.of(new Product()));
        String viewName = productController.productListPage(model);
        verify(model, times(1)).addAttribute(eq("products"), any(List.class));
        assertEquals("productList", viewName);
    }

    @Test
    void testViewProduct_WithValidId() {
        Product product = new Product();
        when(productService.findById("1")).thenReturn(Optional.of(product));
        String viewName = productController.viewProduct("1", model);
        verify(model, times(1)).addAttribute("product", product);
        assertEquals("viewProduct", viewName);
    }

    @Test
    void testViewProduct_WithInvalidId() {
        when(productService.findById("1")).thenReturn(Optional.empty());
        String viewName = productController.viewProduct("1", model);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setProductId("1");
        String viewName = productController.updateProduct(product);
        verify(productService, times(1)).updateProduct("1", product);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProductPage_WithValidId() {
        Product product = new Product();
        when(productService.findById("1")).thenReturn(Optional.of(product));
        String viewName = productController.editProductPage("1", model);
        verify(model, times(1)).addAttribute("product", product);
        assertEquals("editProduct", viewName);
    }

    @Test
    void testEditProductPage_WithInvalidId() {
        when(productService.findById("1")).thenReturn(Optional.empty());
        String viewName = productController.editProductPage("1", model);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProductPost_WithValidData() {
        Product product = new Product();
        product.setProductQuantity(10);
        String viewName = productController.editProductPost("1", product, model);
        verify(productService, times(1)).updateProduct("1", product);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProductPost_WithNegativeQuantity() {
        Product product = new Product();
        product.setProductQuantity(-1);
        String viewName = productController.editProductPost("1", product, model);
        verify(model, times(1)).addAttribute("error", "Quantity tidak boleh negatif");
        assertEquals("editProduct", viewName);
    }

    @Test
    void testDeleteProductPost() {
        String viewName = productController.deleteProductPost("1");
        verify(productService, times(1)).delete("1");
        assertEquals("redirect:/product/list", viewName);
    }
}
