package ru.skypro.lessons.springboot.weblibrary1.service;


import static org.assertj.core.api.Assertions.assertThat;

        import java.sql.Connection;
        import java.sql.SQLException;

        import javax.sql.DataSource;

        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.DynamicPropertyRegistry;
        import org.springframework.test.context.DynamicPropertySource;
        import org.testcontainers.containers.PostgreSQLContainer;
        import org.testcontainers.junit.jupiter.Container;
        import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
public class IntefrationTest {


    @Autowired
    private DataSource dataSource;


    @Test
    void testPostgresql() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            assertThat(conn).isNotNull();
        }
    }
}