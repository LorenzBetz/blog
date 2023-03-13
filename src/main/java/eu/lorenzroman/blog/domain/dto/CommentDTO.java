package eu.lorenzroman.blog.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommentDTO extends BaseDTO {

    private String id;

    private String text;

    private String postId;

    private String authorId;
}
