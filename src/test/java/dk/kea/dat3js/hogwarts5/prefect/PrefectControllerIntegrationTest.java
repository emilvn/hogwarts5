package dk.kea.dat3js.hogwarts5.prefect;

import dk.kea.dat3js.hogwarts5.student.StudentResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PrefectControllerIntegrationTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    void notNull(){
        assertNotNull(webClient);
    }

    @Test
    void setPrefect(){
        webClient
                .post().uri("/prefects")
                .header("Content-Type", "application/json")
                .bodyValue(
                        """
                        {
                            "id": 2
                        }
                        """
                )
                .exchange()
                .expectStatus().isOk()
                .expectBody(StudentResponseDTO.class)
                .value(response -> {
                    assertTrue(response.isPrefect());
                });
    }

    @Test
    void removePrefectStatus(){
        webClient
                .delete().uri("/prefects/2")
                .exchange()
                .expectStatus().isNoContent();
    }
}
