package com.uncledemy.the_thinking_space.dto;

import com.uncledemy.the_thinking_space.exception.TtsException;
import com.uncledemy.the_thinking_space.model.Post;
import com.uncledemy.the_thinking_space.model.User;
import com.uncledemy.the_thinking_space.repo.UserRepository;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Value
public class PostDto {
    UserRepository userRepository;

    @NotBlank
    String title;
    @NotBlank
    String slug;
    @NotBlank
    String content;
    String coverImage;
    List<String> categories;

    @SneakyThrows
    public Post toEntity(long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new TtsException("User not Registered"));
        return Post.builder()
                .author(user)
                .createdAt(LocalDateTime.now())
                .title(title)
                .content(content)
                .slug(slug)
                .coverImage(coverImage)
                .updatedAt(LocalDateTime.now())
                .build();

    }
    @SneakyThrows
    public Post toEntity(Post post,long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new TtsException("User not Registered"));
        return post.toBuilder()
                .title(title)
                .content(content)
                .coverImage(coverImage)
                .updatedAt(LocalDateTime.now())
                .author(user)
                .build();

    }
}
