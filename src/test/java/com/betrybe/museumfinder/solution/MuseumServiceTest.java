package com.betrybe.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class MuseumServiceTest {

  @MockBean
  MuseumFakeDatabase database;

  @Autowired
  MuseumService service;

  @Test
  @DisplayName("Retorna um museu caso o id seja válido")
  public void getMuseumByIdTest() {
    Museum museumMock = new Museum();
    museumMock.setId(999L);
    museumMock.setName("Museu Mock");
    Mockito.when(database.getMuseum(999L)).thenReturn(Optional.of(museumMock));

    Museum result = service.getMuseum(999L);
    assertEquals(museumMock, result);
  }

  @Test
  @DisplayName("Lança um exceção caso não encontre um museu")
  public void getMuseumByIdTestError() {
    Mockito.when(database.getMuseum(1000L)).thenReturn(Optional.empty());

    assertThrows(MuseumNotFoundException.class, () -> service.getMuseum(1000L));
  }
}
