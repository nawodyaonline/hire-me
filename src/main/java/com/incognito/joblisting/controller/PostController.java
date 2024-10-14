package com.incognito.joblisting.controller;


import com.incognito.joblisting.model.Post;
import com.incognito.joblisting.repo.PostRepo;
import com.incognito.joblisting.repo.SearchRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    final PostRepo postRepo;
    final SearchRepo searchRepo;

    public PostController(PostRepo postRepo, SearchRepo searchRepo) {
        this.postRepo = postRepo;
        this.searchRepo = searchRepo;
    }

    @GetMapping("/")
    public String index() {
        return "Hello World";
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return postRepo.findAll();
    }

    @PostMapping("/post")
    public Post addPost(@RequestBody Post post) {
        return postRepo.save(post);
    }

    @GetMapping("/posts/{text}")
    public List<Post> search(@PathVariable String text) {
        return searchRepo.findByText(text);
    }

}
