package eu.lorenzroman.blog.controller;


import eu.lorenzroman.blog.domain.dto.AuthorDTO;
import eu.lorenzroman.blog.domain.dto.CommentDTO;
import eu.lorenzroman.blog.domain.dto.PostDTO;
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
public class AuthorController extends BaseController{

    @QueryMapping
    public List<AuthorDTO> authors() {
        return authorService.getAuthors().stream().map(authorMapper::toAuthorDTO).collect(Collectors.toList());
    }

    @QueryMapping
    public AuthorDTO author(@Argument String id) {
        return authorMapper.toAuthorDTO(authorService.getAuthor(id));
    }

    @SchemaMapping
    public List<CommentDTO> comments(PostDTO post) {
        List<String> commentIds = post.getAuthor().getCommentIds();

        List<Comment> result = new ArrayList<>();

        commentIds.forEach(commentId -> result.add(commentService.getComment(commentId)));

        return result.stream().map(commentMapper::toCommentDTO).collect(Collectors.toList());
    }

    @MutationMapping
    public AuthorDTO upsertAuthor(@Argument String accountId, @Argument String authorId){
        Author author = new Author(accountId);
        author.setAccountId(accountId);
        author.setId(authorId);

        return authorMapper.toAuthorDTO(authorService.addAuthor(author));
    }
}