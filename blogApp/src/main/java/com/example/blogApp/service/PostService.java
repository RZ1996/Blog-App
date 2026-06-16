package com.example.blogApp.service;

import com.example.blogApp.dto.PostDTO;
import com.example.blogApp.entity.Post;
import com.example.blogApp.entity.Tag;
import com.example.blogApp.entity.User;
import com.example.blogApp.mapper.PostMapper;
import com.example.blogApp.repository.PostRepository;
import com.example.blogApp.repository.TagRepository;
import com.example.blogApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TagRepository tagRepository;

    public Page<PostDTO> getAllPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findAll(pageable)
                .map(post -> PostMapper.INSTANCE.mapPostToPostDTO(post));
    }

    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return PostMapper.INSTANCE.mapPostToPostDTO(post);
    }

    public PostDTO createPost(PostDTO postDTO, String email) {
        Post post = PostMapper.INSTANCE.mapPostDTOToPost(postDTO);
        User author = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        post.setAuthor(author);

        if (postDTO.getTags() != null && !postDTO.getTags().isEmpty()) {
            List<Tag> tags = tagRepository.findByNameIn(postDTO.getTags());
            post.setTags(tags);
        }

        Post savedPost = postRepository.save(post);
        return PostMapper.INSTANCE.mapPostToPostDTO(savedPost);
    }

    public List<PostDTO> getPostsByTag(String tagName) {
        List<Post> posts = postRepository.findByTags_Name(tagName);
        return posts.stream()
                .map(post -> PostMapper.INSTANCE.mapPostToPostDTO(post))
                .toList();
    }

    public PostDTO updatePost(Long id, PostDTO postDTO, String email) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getAuthor().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized access");
        }

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setExcerpt(postDTO.getExcerpt());
        post.setStatus(post.getStatus());

        if (postDTO.getTags() != null) {
            List<Tag> tags = tagRepository.findByNameIn(postDTO.getTags());
            post.setTags(tags);
        }

        Post savedPost = postRepository.save(post);
        return PostMapper.INSTANCE.mapPostToPostDTO(savedPost);
    }

    public void deletePost(Long id, String email) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getAuthor().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized access");
        }

        postRepository.deleteById(id);
    }
}