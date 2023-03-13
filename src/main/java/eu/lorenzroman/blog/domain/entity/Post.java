package eu.lorenzroman.blog.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Document("Post")
public class Post extends BaseEntity {
    @NonNull
    private String title;

    @NonNull
    private String text;

    private List<String> categories;

    @NonNull
    @DBRef
    private Author author;

    @DBRef
    private List<Comment> comments;

    public void addComment(Comment comment) {
        if (comments == null) {
            comments = new ArrayList<Comment>();
            comments.add(comment);
        } else if (!comments.contains(comment)) {
            comments.add(comment);
        }
    }

    public void addCategory(String category) {
        if (categories == null) {
            categories = new ArrayList<>();
            categories.add(category);
        } else if (!categories.contains(category)) {
            categories.add(category);
        }
    }

    public boolean hasComment(Comment comment) {
        if(comments == null) {
            return false;
        }

        return comments.contains(comment);
    }
}

