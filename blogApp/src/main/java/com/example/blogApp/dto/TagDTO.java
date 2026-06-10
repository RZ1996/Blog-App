package com.example.blogApp.dto;
import lombok.Data;

@Data
public class TagDTO {

    private Long id;
    private String name;
    private Integer postCount;

    public TagDTO() {}

    public TagDTO(Long id, String name, Integer postCount) {
        this.id = id;
        this.name = name;
        this.postCount = postCount;
    }

}