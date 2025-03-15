package com.uncledemy.the_thinking_space.repo;

import com.uncledemy.the_thinking_space.model.Post;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepo extends JpaRepository<Post, Long> {
    Optional<Post> findByTitleAndSlug(@NotBlank String title, @NotBlank String slug);
    Page<Post> findAllByAuthor(String author, Pageable sortedPageable);
    @Query("SELECT p FROM Post p WHERE p.category = :input OR p.tag = :input")
    Page<Post> findAllByCategoryOrTag(@Param("input") String input, Pageable pageable);

}
