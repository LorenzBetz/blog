package eu.lorenzroman.blog.service.post;

import eu.lorenzroman.blog.domain.entity.Author;
import eu.lorenzroman.blog.domain.entity.Comment;
import eu.lorenzroman.blog.domain.entity.Post;

import java.util.List;

public interface IPostService {

    List<Post> getPosts(int page, int size);

    List<Post> getPosts();

    Post getPost(String id);

    Post updatePost(Post post);

    void deletePost(Post post);

    void deletePost(String postId);

    Post addPost(Post post);

    Post addComment(Comment comment);
}
