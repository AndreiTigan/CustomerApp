package com.example.poc.feign;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements ProductService{
    private  final PostFeignClient feignClient;

    public PostServiceImpl(PostFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @Override
    public List<Post> getAllPosts() {
        return feignClient.getAllPosts();
    }

    @Override
    public Post getPostById(Long id) {
        return feignClient.getPostById(id);
    }

    @Override
    public Post createPost(Post post) {
        return feignClient.createPost(post);
    }

    @Override
    public Post deletePost(Long id) {
        return feignClient.deletePost(id);
    }
}
