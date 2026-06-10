package com.example.blogApp.mapper;

import com.example.blogApp.dto.TagDTO;
import com.example.blogApp.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    @Mapping(target = "postCount", expression = "java(tag.getPosts() != null ? (Integer) tag.getPosts().size() : 0)")
    TagDTO mapTagToTagDTO(Tag tag);

    @Mapping(target = "posts", ignore = true)
    Tag mapTagDTOToTag(TagDTO tagDTO);
}