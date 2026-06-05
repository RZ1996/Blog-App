package com.example.blogApp.mapper;

import com.example.blogApp.dto.PostDTO;
import com.example.blogApp.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post mapPostDTOToPost(PostDTO postDTO);
    PostDTO mapPostToPostDTO(Post post);

}
