package com.betrybe.museumfinder.solution;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
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
public class CollectionTypeControllerTest {

  @Autowired
  MockMvc mock;
  @MockBean
  CollectionTypeService service;

  @Test
  @DisplayName("Deve retornar o número correto de museus com a coleção indicada ")
  public void testTypesCount() throws Exception {
    String[] types = {"hist", "imag"};
    CollectionTypeCount collectionTypeMock = new CollectionTypeCount(types, 492);

    Mockito
        .when(service.countByCollectionTypes("hist,imag"))
        .thenReturn(collectionTypeMock);

    mock.perform(get("/collections/count/hist,imag")).andExpect(status().isOk());
    Mockito.verify(service).countByCollectionTypes("hist,imag");
  }

  @Test
  @DisplayName("Deve retornar um erro 404 ao não encontrar museus")
  public void testNoTypesFound() throws Exception {
    String[] type = {"fakeType"};
    CollectionTypeCount notFoundMock = new CollectionTypeCount(type, 0);

    Mockito
        .when(service.countByCollectionTypes("fakeType"))
        .thenReturn(notFoundMock);
    mock.perform(get("/collections/count/fakeType")).andExpect(status().isNotFound());
    Mockito.verify(service).countByCollectionTypes("fakeType");
  }
}
