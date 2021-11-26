package com.example.poc.feign;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@OpenAPIDefinition
@Tag(name = "Posts")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public List<Post> getPosts() {
        return productService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable  Long id) {
        return productService.getPostById(id);
    }
}
