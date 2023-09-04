package com.betrybe.museumfinder.solution;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MuseumControllerTest {

  @Autowired
  MockMvc mock;
  @MockBean
  MuseumService service;

  @Test
  @DisplayName("Deve retornar o museu esperado caso o id seja válido")
  public void getMuseumByIdTest() throws Exception {
    Museum museumMock = new Museum();
    museumMock.setId(999L);
    museumMock.setName("Museu Mock");

    Mockito.when(service.getMuseum(999L)).thenReturn(museumMock);

    mock.perform(get("/museums/999"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(999L))
        .andExpect(jsonPath("$.name").value("Museu Mock"));

    Mockito.verify(service).getMuseum(999L);
  }

  @Test
  @DisplayName("Caso não encontre o museu, retorna um erro 404")
  public void museumNotFound() throws Exception {
    Mockito.when(service.getMuseum(1000L)).thenThrow(new MuseumNotFoundException());

    mock.perform(get("/museums/1000")).andExpect(status().isNotFound());
  }
}
