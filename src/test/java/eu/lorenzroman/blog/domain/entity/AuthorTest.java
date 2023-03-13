package eu.lorenzroman.blog.domain.entity;

import graphql.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class AuthorTest {
    Author _cut;
    static final String ACC_ID = "1";

    @BeforeEach
    public void init(){
        _cut = new Author(ACC_ID);
    }

    @Test
    public void AuthorInitializeTest(){
        Assert.assertTrue(_cut.getAccountId().equals(ACC_ID));
        Assert.assertNull(_cut.getCommentIds());
        Assert.assertNull(_cut.getPostIds());
    }

    @ParameterizedTest
    @ValueSource(strings = { "CommentId" })
    public void AddCommentIdTest(String commentId){
        Assert.assertNull(_cut.getCommentIds());
        Assert.assertFalse(_cut.hasComment(commentId));

        _cut.addCommentId(commentId);

        Assert.assertNotNull(_cut.getCommentIds());
        Assert.assertTrue(_cut.getCommentIds().size() == 1);
        Assert.assertTrue(_cut.hasComment(commentId));
    }

    @ParameterizedTest
    @ValueSource(strings = { "PostId" })
    public void AddPostIdTest(String postId){
        Assert.assertNull(_cut.getPostIds());
        Assert.assertFalse(_cut.hasPost(postId));

        _cut.addPostId(postId);

        Assert.assertNotNull(_cut.getPostIds());
        Assert.assertTrue(_cut.getPostIds().size() == 1);
        Assert.assertTrue(_cut.hasPost(postId));
    }

    @Test
    public void AuthorEqualsTest(){
        String equalPostId = "equalPostId";
        String equalCommentId = "equalCommentId";
        String equalId = "1";

        String differentPostId = "differentPostId";
        String differentCommentId = "differentCommentId";
        String differentId = "2";

        Author authorA = new Author(ACC_ID);
        authorA.setId(equalId);
        authorA.addPostId(differentPostId);
        authorA.addCommentId(differentCommentId);

        Author authorB = new Author(ACC_ID);
        authorB.setId(equalId);
        authorB.addPostId(equalPostId);
        authorB.addCommentId(equalCommentId);

        Author authorC = new Author(ACC_ID);
        authorC.setId(differentId);
        authorC.addPostId(equalPostId);
        authorC.addCommentId(equalCommentId);

        Assert.assertTrue(authorA.equals(authorB));
        Assert.assertFalse(authorB.equals(authorC));

    }

    @Test
    public void AuthorHashTest(){
        String equalPostId = "equalPostId";
        String equalCommentId = "equalCommentId";
        String equalId = "1";

        String differentPostId = "differentPostId";
        String differentCommentId = "differentCommentId";
        String differentId = "2";

        Author authorA = new Author(ACC_ID);
        authorA.setId(equalId);
        authorA.addPostId(differentPostId);
        authorA.addCommentId(differentCommentId);

        Author authorB = new Author(ACC_ID);
        authorB.setId(equalId);
        authorB.addPostId(equalPostId);
        authorB.addCommentId(equalCommentId);

        Author authorC = new Author(ACC_ID);
        authorC.setId(differentId);
        authorC.addPostId(equalPostId);
        authorC.addCommentId(equalCommentId);

        Assert.assertTrue(authorA.hashCode() == authorB.hashCode());
        Assert.assertFalse(authorB.hashCode() == authorC.hashCode());
    }
}
