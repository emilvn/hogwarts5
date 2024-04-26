package dk.kea.dat3js.hogwarts5.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class StudentControllerIntegrationTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    void notNull(){
        assertNotNull(webClient);
    }

    @Test
    void createStudentWithFullName(){
        webClient
                .post().uri("/students")
                .header("Content-Type", "application/json")
                .bodyValue(
                        """
                        {
                            "name": "Firstname Middlename Lastname",
                            "isMale": true,
                            "house": "Gryffindor",
                            "schoolYear": 7
                        }
                        """
                )
                .exchange()
                .expectStatus().isCreated()
                .expectBody(StudentResponseDTO.class)
                .value(response -> {
                    assertEquals("Firstname", response.firstName());
                    assertEquals("Middlename", response.middleName());
                    assertEquals("Lastname", response.lastName());
                    assertEquals("Firstname Middlename Lastname", response.name());
                });
    }

    @Test
    void createStudentWithNameParts(){
        webClient
                .post().uri("/students")
                .header("Content-Type", "application/json")
                .bodyValue(
                        """
                        {
                            "firstName": "Firstname",
                            "middleName": "Middlename",
                            "lastName": "Lastname",
                            "isMale": true,
                            "house": "Gryffindor",
                            "schoolYear": 7
                        }
                        """
                )
                .exchange()
                .expectStatus().isCreated()
                .expectBody(StudentResponseDTO.class)
                .value(response -> {
                    assertEquals("Firstname", response.firstName());
                    assertEquals("Middlename", response.middleName());
                    assertEquals("Lastname", response.lastName());
                    assertEquals("Firstname Middlename Lastname", response.name());
                });
    }
}
