package net.parttimepolymath.sandbox.springboot.database;

import net.parttimepolymath.sandbox.springboot.model.EchoRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Note that https://reflectoring.io/spring-boot-data-jpa-test/ has good materials on testing against JPA
 */

@DataJpaTest
public class MessagesRepositoryTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MessagesRepository messagesRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertNotNull(dataSource);
        assertNotNull(jdbcTemplate);
        assertNotNull(entityManager);
        assertNotNull(messagesRepository);
    }

    @Test
    void testSelect() {
        Optional<Messages> result = messagesRepository.findById("afed28ca-b91f-11ea-b3de-0242ac130004");
        assertTrue(result.isPresent());
        assertEquals("afed28ca-b91f-11ea-b3de-0242ac130004", result.get().getId());
        assertEquals("message 17", result.get().getMessage());
    }

    @Test
    void testSelectBad() {
        Optional<Messages> result = messagesRepository.findById("no-such-id");
        assertFalse(result.isPresent());
    }

    @Test
    void testSelectAll() {
        List<Messages> result = messagesRepository.findAll();
        assertNotNull(result);
        assertEquals(20, result.size());
        result.forEach(Assertions::assertNotNull);
    }

    @Test
    void testInsert() {
        Messages instance = Messages.fromRequest(new EchoRequest("foo"));
        assertFalse(messagesRepository.existsById(instance.getId()));
        messagesRepository.save(instance);
        assertTrue(messagesRepository.existsById(instance.getId()));
    }
}
