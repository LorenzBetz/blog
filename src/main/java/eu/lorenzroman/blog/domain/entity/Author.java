package eu.lorenzroman.blog.domain.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Document("Author")
public class Author extends BaseEntity {

    @NonNull
    private String accountId;

    private List<String> commentIds;

    private List<String> postIds;

    public boolean hasComment(String commentId) {
        if (commentIds == null) {
            return false;
        }

        return commentIds.contains(commentId);
    }

    public boolean hasPost(String postId) {
        if (postIds == null) {
            return false;
        }

        return postIds.contains(postId);
    }

    public void addCommentId(String commentId) {
        if (commentIds == null) {
            commentIds = new ArrayList<>();
            commentIds.add(commentId);
        } else if (!commentIds.contains(commentId)) {
            commentIds.add(commentId);
        }
    }

    public void addPostId(String postId) {
        if (postIds == null) {
            postIds = new ArrayList<>();
            postIds.add(postId);
        } else if (!postIds.contains(postId)) {
            postIds.add(postId);
        }
    }

    public Author(String id, boolean deleted, LocalDateTime updated, String accountId, List<String> commentIds, List<String> postIds){
        this.setId(id);
        this.setDeleted(deleted);
        this.setUpdated(updated);
        this.setAccountId(accountId);
        this.setCommentIds(commentIds);
        this.setPostIds(postIds);
    }
}
