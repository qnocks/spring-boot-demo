package ru.rostanin.springbootdemo.rest;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.rostanin.springbootdemo.domain.Meditation;
import ru.rostanin.springbootdemo.services.MeditationsService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MeditationsRestController.class)
public class MeditationsRestControllerTest {

    @MockBean
    private MeditationsService meditationsService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/v1/meditations")
    @SneakyThrows
    void shouldGetAllMeditations() {
        when(meditationsService.getAll()).thenReturn(List.of(
                new Meditation(1L, "title1", "desc1", "path1", 1.0),
                new Meditation(2L, "title2", "desc2", "path2", 2.0),
                new Meditation(3L, "title3", "desc3", "path3", 3.0)
        ));
        mockMvc.perform(get("http://localhost:8080/api/v1/meditations")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[\n" +
                        "  {\n" +
                        "    \"id\": 1,\n" +
                        "    \"title\": \"title1\",\n" +
                        "    \"description\": \"description1\",\n" +
                        "    \"sourcePath\": \"path1\",\n" +
                        "    \"rating\": 1.0\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 2,\n" +
                        "    \"title\": \"title2\",\n" +
                        "    \"description\": \"description2\",\n" +
                        "    \"sourcePath\": \"path2\",\n" +
                        "    \"rating\": 2.0\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 3,\n" +
                        "    \"title\": \"title3\",\n" +
                        "    \"description\": \"description3\",\n" +
                        "    \"sourcePath\": \"path3\",\n" +
                        "    \"rating\": 3.0\n" +
                        "  }\n" +
                        "]"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, "application/json"));
    }


    @ParameterizedTest
    @DisplayName("GET /api/v1/meditations/{id}")
    @ValueSource(longs = {1, 2, 3})
    @SneakyThrows
    void shouldGetMeditationById() {
        long id = 2;
        when(meditationsService.getById(id))
                .thenReturn(new Meditation(id, "title", "desc", "path", 1.0));
        mockMvc.perform(get("http://localhost:8080/api/v1/meditations/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"id\": " + id + ",\n" +
                        "  \"title\": \"title1\",\n" +
                        "  \"description\": \"desc1\",\n" +
                        "  \"sourcePath\": \"path1\",\n" +
                        "  \"rating\": 1.0\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, "application/json"));
    }

    @Test
    @DisplayName("POST /api/v1/meditations")
    @SneakyThrows
    void shouldCreateNewMeditation() {
        mockMvc.perform(post("http://localhost:8080/api/v1/meditations"))
                .andExpect(status().isCreated());
    }




}
