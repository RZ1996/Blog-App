package com.example.blogApp.service;

import com.example.blogApp.dto.TagDTO;
import com.example.blogApp.entity.Tag;
import com.example.blogApp.mapper.TagMapper;
import com.example.blogApp.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    @Autowired
    TagRepository tagRepository;

    public List<TagDTO> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tag -> TagMapper.INSTANCE.mapTagToTagDTO(tag))
                .toList();
    }

    public TagDTO getTagById(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        return TagMapper.INSTANCE.mapTagToTagDTO(tag);
    }

    public TagDTO createTag(TagDTO tagDTO) {
        Tag tag = TagMapper.INSTANCE.mapTagDTOToTag(tagDTO);
        Tag savedTag = tagRepository.save(tag);
        return TagMapper.INSTANCE.mapTagToTagDTO(savedTag);
    }

    public void deleteTag(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new RuntimeException("Tag not found");
        }
        tagRepository.deleteById(id);
    }
}