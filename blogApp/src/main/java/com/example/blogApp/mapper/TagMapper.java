package com.example.blogApp.mapper;

import com.example.blogApp.dto.TagDTO;
import com.example.blogApp.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    Tag mapTagDTOToTag(TagDTO tagDTO);
    TagDTO mapTagToTagDTO(Tag tag);
}
