package com.uncledemy.the_thinking_space.service;

import com.uncledemy.the_thinking_space.model.Post;

import java.util.List;

public interface PostService {
    void createPost(Post post);
    void updatePost(Post post);
    void deletePost(Post post);
    List<Post> getPosts();
    Post getPost(int id);
    List<Post> getPostsByAuthor(String author);
    List<Post> getPostsByCategoryOrTag(String category);
//    List<Post> getPostsByTag(String tag);

}
