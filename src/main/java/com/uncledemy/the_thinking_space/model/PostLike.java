package com.uncledemy.the_thinking_space.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "post_likes")
@Data
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

