package eu.lorenzroman.blog.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Document("Comment")
public class Comment extends BaseEntity {

    @NonNull
    private String text;

    @NonNull
    private String postId;

    @NonNull
    private String authorId;
}