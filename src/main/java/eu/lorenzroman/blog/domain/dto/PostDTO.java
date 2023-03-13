package eu.lorenzroman.blog.domain.dto;

import eu.lorenzroman.blog.domain.entity.Author;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostDTO extends BaseDTO {

    private String id;

    private String title;

    private String text;

    private Author author;

    private List<String> categories;

    private List<CommentDTO> comments;
}
