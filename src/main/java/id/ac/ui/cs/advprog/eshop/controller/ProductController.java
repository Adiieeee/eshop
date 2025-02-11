package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProduct = service.findAll();
        model.addAttribute("products", allProduct);
        return "productList";
    }

    @GetMapping("/view/{productId}")
    public String viewProduct(@PathVariable String productId, Model model) {
        Optional<Product> product = service.findById(productId);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "viewProduct";
        } else {
            return "redirect:/product/list";
        }
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product) {
        service.updateProduct(product.getProductId(), product);
        return "redirect:/product/list";
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable String productId, Model model) {
        Optional<Product> product = service.findById(productId);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "editProduct";
        } else {
            return "redirect:/product/list";
        }
    }

    @PostMapping("/edit/{productId}")
    public String editProductPost(@PathVariable String productId, @ModelAttribute Product product) {
        service.updateProduct(productId, product);
        return "redirect/product/list";
    }

}
