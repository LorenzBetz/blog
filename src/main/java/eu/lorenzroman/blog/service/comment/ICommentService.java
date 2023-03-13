package eu.lorenzroman.blog.service.comment;

import eu.lorenzroman.blog.domain.entity.Author;
import eu.lorenzroman.blog.domain.entity.Comment;
import eu.lorenzroman.blog.domain.entity.Post;

import java.util.List;

public interface ICommentService {

    Comment addComment(Comment comment);

    void deleteComment(Comment comment);

    void deleteComment(String commentId);

    Comment getComment(String commentId);
    
    List<Comment> getComments();

    Comment updateComment(Comment comment);
}
