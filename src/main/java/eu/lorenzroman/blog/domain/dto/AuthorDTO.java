package eu.lorenzroman.blog.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthorDTO extends BaseDTO{

    private String id;

    private String accountId;

    private List<String> commentIds;

    private List<String> postIds;
}
