package eu.lorenzroman.blog;

import eu.lorenzroman.blog.domain.entity.Author;
import eu.lorenzroman.blog.domain.entity.Comment;
import eu.lorenzroman.blog.domain.entity.Post;
import eu.lorenzroman.blog.repository.AuthorRepository;
import eu.lorenzroman.blog.repository.CommentRepository;
import eu.lorenzroman.blog.repository.PostRepository;
import eu.lorenzroman.blog.service.author.AuthorService;
import eu.lorenzroman.blog.service.comment.CommentService;
import eu.lorenzroman.blog.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

    @Autowired
    PostService postService;

    @Autowired
    AuthorService authorService;

    @Autowired
    CommentService commentService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CommentRepository commentRepository;

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    public void run(String... args) {
        postRepository.deleteAll();
        commentRepository.deleteAll();
        authorRepository.deleteAll();
        provisionWithTestData();
    }

    void provisionWithTestData() {
        Random random = new Random();
        List<Author> authorList = new ArrayList<>();
        List<Post> postsList = new ArrayList<>();
        List<Comment> commentList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            try {
                Author author = new Author("accountId_" + i);
                authorList.add(authorService.addAuthor(author));
            } catch (Exception ex) {
                throw ex;
            }
        }

        for (int i = 0; i < 100; i++) {

            Post randomPost = new Post("Title_" + i, "Text_" + random.nextInt(0, 10), authorList.get(random.nextInt(0, authorList.size())));
            postsList.add(postService.addPost(randomPost));
        }

        for (int i = 0; i < 500; i++) {
            Comment comment = new Comment("Text_" + i, postsList.get(random.nextInt(0, postsList.size())).getId(), authorList.get(random.nextInt(0, authorList.size())).getId());
            commentList.add(commentService.addComment(comment));
        }
    }
}
