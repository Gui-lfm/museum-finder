package com.betrybe.museumfinder.controller;


import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Camda controller da aplicação.
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {

  MuseumServiceInterface service;

  public MuseumController(MuseumServiceInterface service) {
    this.service = service;
  }

  /**
   * Adiciona um novo museu ao banco de dados.
   */
  @PostMapping
  public ResponseEntity<Museum> postMuseum(MuseumCreationDto newMuseum) {
    Museum request = ModelDtoConverter.dtoToModel(newMuseum);

    return ResponseEntity.status(HttpStatus.CREATED).body(service.createMuseum(request));
  }
}
