package eu.lorenzroman.blog.controller;


import eu.lorenzroman.blog.domain.dto.CommentDTO;
import eu.lorenzroman.blog.domain.entity.Author;
import eu.lorenzroman.blog.domain.entity.Comment;
import eu.lorenzroman.blog.domain.entity.Post;
import eu.lorenzroman.blog.service.author.IAuthorService;
import eu.lorenzroman.blog.service.comment.ICommentService;
import eu.lorenzroman.blog.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CommentController extends BaseController {

    @QueryMapping
    public List<CommentDTO> comments() {
        return commentService.getComments().stream().map(commentMapper::toCommentDTO).collect(Collectors.toList());
    }

    @QueryMapping
    public CommentDTO comment(@Argument String id) {
        return commentMapper.toCommentDTO(commentService.getComment(id));
    }

    @MutationMapping
    public CommentDTO upsertComment(@Argument String text, @Argument String authorId, @Argument String postId, @Argument String commentId){
        Comment comment = new Comment(text,postId,authorId);
        comment.setId(commentId);
        return commentMapper.toCommentDTO(commentService.addComment(comment));
    }
}