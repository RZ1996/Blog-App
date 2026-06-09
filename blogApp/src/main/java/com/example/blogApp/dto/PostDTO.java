package com.example.blogApp.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Long id;
    private String title;
    private String excerpt;
    private String content;
    private String authorName;
    private List<String> tags;
    private String status;
    private LocalDateTime createdAt;
}
