package eu.lorenzroman.blog.controller;

import eu.lorenzroman.blog.domain.mapper.AuthorMapper;
import eu.lorenzroman.blog.domain.mapper.CommentMapper;
import eu.lorenzroman.blog.domain.mapper.PostMapper;
import eu.lorenzroman.blog.service.author.IAuthorService;
import eu.lorenzroman.blog.service.comment.ICommentService;
import eu.lorenzroman.blog.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseController {
    @Autowired
    protected IPostService postService;

    @Autowired
    protected IAuthorService authorService;

    @Autowired
    protected ICommentService commentService;

    @Autowired
    protected PostMapper postMapper;

    @Autowired
    protected AuthorMapper authorMapper;

    @Autowired
    protected CommentMapper commentMapper;
}
