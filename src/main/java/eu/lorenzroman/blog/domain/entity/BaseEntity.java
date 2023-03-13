package eu.lorenzroman.blog.domain.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BaseEntity {
    @Id
    @EqualsAndHashCode.Include
    private String id;

    private boolean deleted = false;

    private LocalDateTime created;

    private LocalDateTime updated;

    public void setUpdated() {
        this.updated = LocalDateTime.now();
    }

    public void setCreated() {
        this.created = LocalDateTime.now();
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
