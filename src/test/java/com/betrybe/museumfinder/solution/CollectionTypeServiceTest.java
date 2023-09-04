package com.betrybe.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CollectionTypeServiceTest {

  @MockBean
  MuseumFakeDatabase database;
  @Autowired
  CollectionTypeService service;

  @Test
  @DisplayName("Deve retornar o objeto correspondente ao receber mais de um tipo válido")
  public void collectionTypesTest() {
    String[] types = {"hist", "imag"};
    CollectionTypeCount collectionTypeMock = new CollectionTypeCount(types, 492);

    Mockito
        .when(database.countByCollectionType("hist"))
        .thenReturn(300L);

    Mockito
        .when(database.countByCollectionType("imag"))
        .thenReturn(192L);

    CollectionTypeCount collectionTypeCount = service.countByCollectionTypes("hist,imag");

    assertEquals(collectionTypeCount.collectionTypes()[0], collectionTypeMock.collectionTypes()[0]);
    assertEquals(collectionTypeCount.count(), collectionTypeMock.count());

    Mockito.verify(database).countByCollectionType("hist");
    Mockito.verify(database).countByCollectionType("imag");
  }

  @Test
  @DisplayName("Deve retornar o objeto correspondente ao receber um tipo válido")
  public void collectionTypeTest() {
    String[] type = {"hist"};
    CollectionTypeCount collectionTypeMock = new CollectionTypeCount(type, 300);

    Mockito
        .when(database.countByCollectionType("hist"))
        .thenReturn(300L);

    CollectionTypeCount collectionTypeCount = service.countByCollectionTypes("hist");

    assertEquals(collectionTypeCount.collectionTypes()[0], collectionTypeMock.collectionTypes()[0]);
    assertEquals(collectionTypeCount.count(), collectionTypeMock.count());

    Mockito.verify(database).countByCollectionType("hist");
  }

  @Test
  @DisplayName("Deve retornar um objeto com count igual a zero caso não encontre um museu")
  public void notFoundCollectionTest() {
    String[] type = {"fakeType"};
    CollectionTypeCount collectionTypeMock = new CollectionTypeCount(type, 0);

    Mockito
        .when(database.countByCollectionType("fakeType"))
        .thenReturn(0L);

    CollectionTypeCount collectionTypeCount = service.countByCollectionTypes("fakeType");

    assertEquals(collectionTypeCount.collectionTypes()[0], collectionTypeMock.collectionTypes()[0]);
    assertEquals(collectionTypeCount.count(), collectionTypeMock.count());

    Mockito.verify(database).countByCollectionType("fakeType");
  }
}
