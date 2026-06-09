package com.example.blogApp.controller;

import com.example.blogApp.dto.PostDTO;
import com.example.blogApp.entity.Post;
import com.example.blogApp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping("/tag")
    public ResponseEntity<List<PostDTO>> getPostsByTag(@RequestParam String tag) {
        return ResponseEntity.ok(postService.getPostsByTag(tag));
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        return ResponseEntity.status(201).body(postService.createPost(postDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.updatePost(id, postDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}