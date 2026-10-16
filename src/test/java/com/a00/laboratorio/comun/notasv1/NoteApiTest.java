package com.a00.laboratorio.comun.notasv1;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfig.class)
class NoteApiTest {

    @Autowired
    MockMvcTester mvc;

    @Test
    void creaUnaNotaYLaLee() {
        MvcTestResult created = mvc.post().uri("/api/v1/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"title": "Primera", "body": "Hola"}
                        """)
                .exchange();
        assertThat(created).hasStatus(HttpStatus.CREATED);

        String location = created.getResponse().getHeader("Location");
        assertThat(mvc.get().uri(location))
                .hasStatusOk()
                .bodyJson().extractingPath("$.title").isEqualTo("Primera");
    }

    @Test
    void unaNotaQueNoExisteDevuelveUnProblemDetail() {
        assertThat(mvc.get().uri("/api/v1/notes/{id}", UUID.randomUUID()))
                .hasStatus(HttpStatus.NOT_FOUND)
                .bodyJson().extractingPath("$.status").isEqualTo(404);
    }

    @Test
    void unTituloVacioEsUn400() {
        assertThat(mvc.post().uri("/api/v1/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"title": ""}
                        """))
                .hasStatus(HttpStatus.BAD_REQUEST);
    }
}
