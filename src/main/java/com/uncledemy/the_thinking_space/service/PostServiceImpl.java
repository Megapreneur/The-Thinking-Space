package com.uncledemy.the_thinking_space.service;

import com.uncledemy.the_thinking_space.dto.PostDto;
import com.uncledemy.the_thinking_space.dto.PostResponse;
import com.uncledemy.the_thinking_space.exception.TtsException;
import com.uncledemy.the_thinking_space.model.Post;
import com.uncledemy.the_thinking_space.model.User;
import com.uncledemy.the_thinking_space.repo.PostRepo;
import com.uncledemy.the_thinking_space.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    PostRepo postRepo;
    UserRepository userRepository;

    /**
     * @param postDto
     */
    @Override
    @SneakyThrows
    public PostResponse createPost(PostDto postDto, long userId) {
        Optional<Post> post = postRepo.findByTitleAndSlug(postDto.getTitle(),postDto.getSlug());
        if (post.isPresent())
            throw new TtsException("");
        Post newPost = postDto.toEntity(userId);
        postRepo.save(newPost);
        return new PostResponse(newPost);
    }

    /**
     * @param postDto
     */
    @Override
    @SneakyThrows
    public PostResponse updatePost(PostDto postDto, long userId) {
        Optional<Post> post = postRepo.findByTitleAndSlug(postDto.getTitle(),postDto.getSlug());
        if (post.isPresent() && post.get().getAuthor().getId().equals(userId)){
          Post updatePost = postDto.toEntity(post.get(),userId);
          postRepo.save(updatePost);
          return new PostResponse(updatePost);
        }
        else {
            throw new TtsException("");
        }
    }

    /**
     * @param id
     */
    @Override
    public void deletePost(long id) {

    }

    /**
     * @return
     */
    @Override
    public Page<Post> getPosts(Pageable pageable) {
        // Add sorting to the provided pageable
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort
        );
        return postRepo.findAll(sortedPageable);
    }

    /**
     * @param id
     * @return
     */
    @Override
    @SneakyThrows
    public Post getPost(long id) {
        return postRepo.findById(id).orElseThrow(()-> new TtsException("Post does not exist!!!"));
    }

//    /**
//     * @param author
//     * @return
//     */
//    @Override
//    public Page<Post> getPostsByAuthor(String author) {
//        return null;
//    }

    /**
     * @param author
     * @return
     */
    @Override
    public Page<Post> getPostsByAuthor(String author, Pageable pageable) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort
        );
        return postRepo.findAllByAuthor(author,sortedPageable);
    }

    /**
     * @param input
     * @return
     */
    @Override
    public Page<Post> getPostsByCategoryOrTag(String input, Pageable pageable) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort
        );
        return postRepo.findAllByCategoryOrTag(input,sortedPageable);
    }
}
