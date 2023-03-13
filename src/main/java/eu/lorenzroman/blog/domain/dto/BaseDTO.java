package eu.lorenzroman.blog.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDTO {

    private String version;

    private LocalDateTime created;

    private LocalDateTime updated;
}
