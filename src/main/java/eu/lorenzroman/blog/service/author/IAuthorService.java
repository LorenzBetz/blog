package eu.lorenzroman.blog.service.author;

import eu.lorenzroman.blog.domain.entity.Author;
import eu.lorenzroman.blog.domain.entity.Comment;
import eu.lorenzroman.blog.domain.entity.Post;

import java.util.List;

public interface IAuthorService {
    Author addAuthor(Author author);

    void addPost(Author author, Post post);

    void addPost(String authorId, Post post);

    void addComment(Author author, Comment comment);

    void addComment(String authorId, Comment comment);

    Author updateAuthor(Author author);

    void deleteAuthor(Author author);

    void deleteAuthor(String authorId);

    List<Author> getAuthors();

    Author getAuthor(String authorId);
}
