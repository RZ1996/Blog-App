package com.example.blogApp.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private PostStatus status = PostStatus.DRAFT;
    private String content;
    private String excerpt;
    private User author;
    private List<Tag> tags = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime  updatedAt;
}

