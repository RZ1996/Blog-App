package com.example.blogApp.mapper;

import com.example.blogApp.entity.Post;
import com.example.blogApp.entity.Tag;
import com.example.blogApp.service.JwtService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "tags", expression = "java(mapTagsToStrings(post.getTags()))")
    @Mapping(target = "authorName", expression = "java(post.getAuthor() != null ? post.getAuthor().getName() : null)")
    JwtService.PostDTO mapPostToPostDTO(Post post);

    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Post mapPostDTOToPost(JwtService.PostDTO postDTO);

    default List<String> mapTagsToStrings(List<Tag> tags) {
        if (tags == null) return new ArrayList<>();
        return tags.stream()
                .map(Tag::getName)
                .toList();
    }

}