import models.User;
import net.dev.squery.builders.QueryManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SqueryBuilderTest {
    @Test
    public void createQuery() {

        String createQuery = QueryManager.createQuery()
                .withEntity(new User("joe", 34, "joe@gmail.com"))
                .fromTable("users")
                .build();

        assertNotNull(createQuery);
        assertEquals("INSERT INTO users (name, age, email) VALUES ('joe', 34, 'joe@gmail.com')", createQuery);
    }

}
