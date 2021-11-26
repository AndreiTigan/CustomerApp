package com.example.poc.feign;

import java.util.List;

public interface ProductService {
    List<Post> getAllPosts();

    Post getPostById(Long id);

    Post createPost(Post post);

    Post deletePost(Long id);
}
