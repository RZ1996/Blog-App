package com.example.blogApp.service;
import com.example.blogApp.dto.PostDTO;
import com.example.blogApp.entity.Post;
import com.example.blogApp.mapper.PostMapper;
import com.example.blogApp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {


    @Autowired
    PostRepository postRepository;
    public List<PostDTO> getAllPosts(){

        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(post -> PostMapper.INSTANCE.mapPostToPostDTO(post))
                .toList();
    }

    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return PostMapper.INSTANCE.mapPostToPostDTO(post);
    }

    public PostDTO createPost(PostDTO postDTO){
        Post post = PostMapper.INSTANCE.mapPostDTOToPost(postDTO);
        postRepository.save(post);
        return PostMapper.INSTANCE.mapPostToPostDTO(post);
    }

    public List<PostDTO> getPostsByTag(String tagName) {
        List<Post> posts = postRepository.findByTags_Name(tagName);
        return posts.stream()
                .map(post -> PostMapper.INSTANCE.mapPostToPostDTO(post))
                .toList();
    }

    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        existingPost.setTitle(postDTO.getTitle());
        existingPost.setContent(postDTO.getContent());

        Post savedPost = postRepository.save(existingPost);
        return PostMapper.INSTANCE.mapPostToPostDTO(savedPost);
    }

    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found");
        }
        postRepository.deleteById(id);
    }
}
