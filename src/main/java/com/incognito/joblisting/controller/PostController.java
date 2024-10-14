package com.incognito.joblisting.controller;


import com.incognito.joblisting.model.Post;
import com.incognito.joblisting.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    final PostRepo postRepo;

    public PostController(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @GetMapping("/")
    public String index() {
        return "Hello World";
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return postRepo.findAll();
    }

}
