package dk.kea.dat3js.hogwarts5.teacher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TeacherControllerIntegrationTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    void notNull(){
        assertNotNull(webClient);
    }

    @Test
    void createTeacherWithFullName(){
        webClient
                .post().uri("/teachers")
                .header("Content-Type", "application/json")
                .bodyValue(
                        """
                        {
                            "name": "Firstname Middlename Lastname",
                            "house": "Gryffindor"
                        }
                        """
                )
                .exchange()
                .expectStatus().isCreated()
                .expectBody(TeacherResponseDTO.class)
                .value(response -> {
                    assertEquals("Firstname", response.firstName());
                    assertEquals("Middlename", response.middleName());
                    assertEquals("Lastname", response.lastName());
                    assertEquals("Firstname Middlename Lastname", response.name());
                });
    }

    @Test
    void createTeacherWithNameParts(){
        webClient
                .post().uri("/teachers")
                .header("Content-Type", "application/json")
                .bodyValue(
                        """
                        {
                            "firstName": "Firstname",
                            "middleName": "Middlename",
                            "lastName": "Lastname",
                            "house": "Gryffindor"
                        }
                        """
                )
                .exchange()
                .expectStatus().isCreated()
                .expectBody(TeacherResponseDTO.class)
                .value(response -> {
                    assertEquals("Firstname", response.firstName());
                    assertEquals("Middlename", response.middleName());
                    assertEquals("Lastname", response.lastName());
                    assertEquals("Firstname Middlename Lastname", response.name());
                });
    }

    @Test
    void updateTeacherWithFullName(){
        webClient
                .put().uri("/teachers/1")
                .header("Content-Type", "application/json")
                .bodyValue(
                        """
                        {
                            "name": "Firstname Middlename Lastname",
                            "house": "Gryffindor"
                        }
                        """
                )
                .exchange()
                .expectStatus().isOk()
                .expectBody(TeacherResponseDTO.class)
                .value(response -> {
                    assertEquals("Firstname", response.firstName());
                    assertEquals("Middlename", response.middleName());
                    assertEquals("Lastname", response.lastName());
                    assertEquals("Firstname Middlename Lastname", response.name());
                });
    }

    @Test
    void updateTeacherWithNameParts(){
        webClient
                .put().uri("/teachers/1")
                .header("Content-Type", "application/json")
                .bodyValue(
                        """
                        {
                            "firstName": "Firstname",
                            "middleName": "Middlename",
                            "lastName": "Lastname",
                            "house": "Gryffindor"
                        }
                        """
                )
                .exchange()
                .expectStatus().isOk()
                .expectBody(TeacherResponseDTO.class)
                .value(response -> {
                    assertEquals("Firstname", response.firstName());
                    assertEquals("Middlename", response.middleName());
                    assertEquals("Lastname", response.lastName());
                    assertEquals("Firstname Middlename Lastname", response.name());
                });
    }

    @Test
    void partialUpdateTeacher(){
        webClient
                .patch().uri("/teachers/1")
                .header("Content-Type", "application/json")
                .bodyValue(
                        """
                        {
                            "middleName": "Middlename",
                            "house": "Gryffindor"
                        }
                        """
                )
                .exchange()
                .expectStatus().isOk()
                .expectBody(TeacherResponseDTO.class)
                .value(response -> {
                    assertEquals("Middlename", response.middleName());
                    assertEquals("Gryffindor", response.house());
                });
    }

    @Test
    void deleteTeacher(){
        webClient
                .delete().uri("/teachers/2")
                .exchange()
                .expectStatus().isOk()
                .expectBody(TeacherResponseDTO.class)
                .value(response -> {
                    assertEquals(2, response.id());
                });
    }


}
