package eu.lorenzroman.blog.service;

import eu.lorenzroman.blog.domain.mapper.AuthorMapper;
import eu.lorenzroman.blog.domain.mapper.CommentMapper;
import eu.lorenzroman.blog.domain.mapper.PostMapper;
import eu.lorenzroman.blog.repository.AuthorRepository;
import eu.lorenzroman.blog.repository.CommentRepository;
import eu.lorenzroman.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    // Repos
    @Autowired
    protected PostRepository postRepository;

    @Autowired
    protected AuthorRepository authorRepository;

    @Autowired
    protected CommentRepository commentRepository;

    // Mapper
    @Autowired
    protected PostMapper postMapper;

    @Autowired
    protected AuthorMapper authorMapper;

    @Autowired
    protected CommentMapper commentMapper;
}
