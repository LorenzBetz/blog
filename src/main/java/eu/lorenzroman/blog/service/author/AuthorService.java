package eu.lorenzroman.blog.service.author;

import eu.lorenzroman.blog.domain.entity.Author;
import eu.lorenzroman.blog.domain.entity.Comment;
import eu.lorenzroman.blog.domain.entity.Post;
import eu.lorenzroman.blog.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService extends BaseService implements IAuthorService {


    @Override
    public Author addAuthor(Author author) {
        if (author.getId() != null && authorRepository.existsById(author.getId())) {
            return updateAuthor(author);
        } else {
            return authorRepository.save(author);
        }
    }

    @Override
    public void addPost(Author author, Post post) {
        if(author.getId() == null) {
            throw new IllegalArgumentException("Author Id must not be null.");
        } else if (post == null) {
            throw new IllegalArgumentException("Post must not be null.");
        }

        addPost(author.getId(), post);
    }

    @Override
    public void addPost(String authorId, Post post) {
        Author authorOut = authorRepository.findById(authorId).orElseThrow();
        if(!authorOut.hasPost(post.getId())){
            authorOut.addPostId(post.getId());
        }

        updateAuthor(authorOut);
    }

    @Override
    public void addComment(Author author, Comment comment) {
        Author authorOut = authorRepository.findById(author.getId()).orElseThrow();
        if(!authorOut.getCommentIds().contains(comment.getId())){
            authorOut.getCommentIds().add(comment.getId());
        }

        updateAuthor(authorOut);
    }

    @Override
    public void addComment(String authorId, Comment comment) {
        Author authorOut = authorRepository.findById(authorId).orElseThrow();
        authorOut.addCommentId(comment.getId());

        updateAuthor(authorOut);
    }

    @Override
    public Author updateAuthor(Author author) {
        Author authorOut = authorRepository.findById(author.getId()).orElseThrow();
        authorMapper.updateAuthor(author, authorOut);

        return authorRepository.save(authorOut);
    }

    @Override
    public void deleteAuthor(Author author) {
        authorRepository.delete(author);
    }

    @Override
    public void deleteAuthor(String authorId) {
        authorRepository.deleteById(authorId);
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthor(String authorId) {
        return authorRepository.findById(authorId).orElseThrow();
    }
}
