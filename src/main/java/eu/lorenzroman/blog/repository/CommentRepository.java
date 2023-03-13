package eu.lorenzroman.blog.repository;

import eu.lorenzroman.blog.domain.entity.Comment;
import eu.lorenzroman.blog.repository.customsave.CustomSave;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String>, CustomSave<Comment> {
}