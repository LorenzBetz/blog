package eu.lorenzroman.blog.repository;

import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.mongo.transitions.Mongod;
import de.flapdoodle.embed.mongo.transitions.RunningMongodProcess;
import de.flapdoodle.reverse.TransitionWalker;
import de.flapdoodle.reverse.transitions.Start;
import eu.lorenzroman.blog.domain.entity.Author;
import graphql.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;



@ActiveProfiles(profiles = "test")
@DataMongoTest()
@ExtendWith(SpringExtension.class)
public class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;

    private TransitionWalker.ReachedState<RunningMongodProcess> running;

    private static final Logger logger = LoggerFactory.getLogger(AuthorRepositoryTest.class);

    @BeforeEach
    public void setup() {
        try {
              running = Mongod.instance().withNet(Start.to(Net.class).initializedWith(Net.of("localhost", 27018, true))).start(Version.Main.V6_0);
        } catch (Exception e) {
            logger.info("Test mongod instance already running.");
        }

        authorRepository.deleteAll();
    }

    @Test
    public void shoudBeEmpty() {
        List<Author> author = authorRepository.findAll();

        Assert.assertNotNull(author);
        Assert.assertTrue(author.size() == 0);
    }

    @Test
    public void shoudNotBeEmpty() {
        LocalDateTime updated = LocalDateTime.now();
        String accountId = "482929";
        Author authorInsert = new Author(
                "1",
                false,
                updated,
                accountId,
                new ArrayList<>(),
                new ArrayList<>()
        );
        authorRepository.save(authorInsert);

        List<Author> existingAuthors = authorRepository.findAll();

        Assert.assertNotNull(existingAuthors);
        Assert.assertTrue(existingAuthors.size() == 1);

        Author existingAuthor = existingAuthors.get(0);

        Assert.assertTrue(existingAuthor.getId().equals("1"));
        Assert.assertTrue(existingAuthor.getUpdated().truncatedTo(ChronoUnit.SECONDS).equals(updated.truncatedTo(ChronoUnit.SECONDS)));
        Assert.assertTrue(existingAuthor.getAccountId().equals(accountId));
        Assert.assertTrue(existingAuthor.getCommentIds().equals(new ArrayList<>()));
        Assert.assertTrue(existingAuthor.getPostIds().equals(new ArrayList<>()));
    }
}
