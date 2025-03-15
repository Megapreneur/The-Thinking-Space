package com.uncledemy.the_thinking_space.dto;

import com.uncledemy.the_thinking_space.model.Post;
import lombok.Value;

@Value
public class PostResponse {
    Post created;
}
