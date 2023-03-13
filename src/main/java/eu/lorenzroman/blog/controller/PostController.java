package eu.lorenzroman.blog.controller;


import eu.lorenzroman.blog.domain.dto.AuthorDTO;
import eu.lorenzroman.blog.domain.dto.CommentDTO;
import eu.lorenzroman.blog.domain.dto.PostDTO;
import eu.lorenzroman.blog.domain.entity.Author;
import eu.lorenzroman.blog.domain.entity.Comment;
import eu.lorenzroman.blog.domain.entity.Post;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PostController extends BaseController {

    @QueryMapping
    public List<PostDTO> posts(@Argument int page, @Argument int size) {
        return postService.getPosts(page, size).stream().map(postMapper::toPostDTO).collect(Collectors.toList());
    }

    @QueryMapping
    public PostDTO post(@Argument String id) {
        return postMapper.toPostDTO(postService.getPost(id));
    }

    @SchemaMapping
    public AuthorDTO author(PostDTO post) {
        return authorMapper.toAuthorDTO(authorService.getAuthor(post.getAuthor().getId()));
    }

    @SchemaMapping
    public List<PostDTO> posts(AuthorDTO author) {
        List<String> postIds = author.getPostIds();

        List<Post> result = new ArrayList<>();

        postIds.forEach(postId -> result.add(postService.getPost(postId)));

        return result.stream().map(postMapper::toPostDTO).collect(Collectors.toList());
    }


    @SchemaMapping
    public List<CommentDTO> comments(AuthorDTO author) {
        List<String> commentIds = author.getCommentIds();

        List<Comment> result = new ArrayList<>();

        commentIds.forEach(id -> result.add(commentService.getComment(id)));

        return result.stream().map(commentMapper::toCommentDTO).collect(Collectors.toList());
    }

    @SchemaMapping
    public AuthorDTO author(CommentDTO comment) {
        return authorMapper.toAuthorDTO(authorService.getAuthor(comment.getAuthorId()));
    }

    @MutationMapping
    public PostDTO upsertPost(@Argument String title, @Argument String text, @Argument String authorId, @Argument String postId) {
        Author author = authorService.getAuthor(authorId);

        Post post = new Post(title, text, author);
        post.setId(postId);

        return postMapper.toPostDTO(postService.addPost(post));
    }
}