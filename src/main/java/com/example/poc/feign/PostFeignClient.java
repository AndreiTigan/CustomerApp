package com.example.poc.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "postFeignClient", url = "https://jsonplaceholder.typicode.com/")
public interface PostFeignClient {
    @GetMapping(value = "/posts")
    List<Post> getAllPosts();

    @GetMapping("/posts/{id}")
    Post getPostById(@PathVariable Long id);

    @PostMapping("/posts")
    Post createPost(@RequestBody Post post);

    @DeleteMapping("/posts/{id}")
    Post deletePost(@PathVariable Long id);
}
