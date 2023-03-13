package eu.lorenzroman.blog.service.post;

import eu.lorenzroman.blog.domain.entity.Comment;
import eu.lorenzroman.blog.domain.entity.Post;
import eu.lorenzroman.blog.service.BaseService;
import eu.lorenzroman.blog.service.author.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService extends BaseService implements IPostService {

    @Autowired
    protected IAuthorService authorService;

    @Override
    public List<Post> getPosts(int page, int size) {
        return postRepository.findAll(PageRequest.of(page, size, Sort.by("created"))).stream().toList();
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll(Sort.by("created"));
    }

    @Override
    public Post getPost(String id) {
        return postRepository.findById(id).orElseThrow();
    }

    @Override
    public Post updatePost(Post postIn) {
        Post postOut = postRepository.findById(postIn.getId()).orElseThrow();
        postMapper.updatePost(postIn, postOut);
        return postRepository.save(postOut);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    @Override
    public void deletePost(String postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public Post addPost(Post post) {
        if(post.getId() != null && postRepository.existsById(post.getId())){
            return updatePost(post);
        } else {
            Post postWithId = postRepository.save(post);
            authorService.addPost(post.getAuthor(), post);
            return postWithId;
        }
    }

    @Override
    public Post addComment(Comment comment) {
        Post post = postRepository.findById(comment.getPostId()).orElseThrow();

        if(!post.hasComment(comment)) {
            post.addComment(comment);
        }

        return postRepository.save(post);
    }
}
