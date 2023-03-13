package eu.lorenzroman.blog.service.comment;

import eu.lorenzroman.blog.domain.entity.Comment;
import eu.lorenzroman.blog.service.BaseService;
import eu.lorenzroman.blog.service.author.IAuthorService;
import eu.lorenzroman.blog.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService extends BaseService implements ICommentService {

    @Autowired
    protected IPostService postService;

    @Autowired
    protected IAuthorService authorService;

    @Override
    public Comment addComment(Comment comment) {
        if (comment.getId() != null && commentRepository.existsById(comment.getId())) {
            return updateComment(comment);
        } else {

            Comment commentWithId = commentRepository.save(comment);
            authorService.addComment(commentWithId.getAuthorId(), commentWithId);
            postService.addComment(commentWithId);
            return commentWithId;
        }
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public void deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment getComment(String commentId) {
        return commentRepository.findById(commentId).orElseThrow();
    }

    @Override
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment updateComment(Comment commentIn) {
        Comment commentOut = commentRepository.findById(commentIn.getId()).orElseThrow();
        commentMapper.updateComment(commentIn, commentOut);
        return commentRepository.save(commentOut);
    }


}
