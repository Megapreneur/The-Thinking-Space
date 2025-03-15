package com.uncledemy.the_thinking_space.service;

import com.uncledemy.the_thinking_space.dto.PostDto;
import com.uncledemy.the_thinking_space.dto.PostResponse;
import com.uncledemy.the_thinking_space.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    PostResponse createPost(PostDto post, long userId);
    PostResponse updatePost(PostDto post, long userId);
    void deletePost(long id);
    Page<Post> getPosts(Pageable pageable);
    Post getPost(long id);
    Page<Post> getPostsByAuthor(String author, Pageable pageable);
    Page<Post> getPostsByCategoryOrTag(String input, Pageable pageable);
//    List<Post> getPostsByTag(String tag);

}
