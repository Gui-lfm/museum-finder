package com.betrybe.museumfinder.controller;


import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  /**
   * Procura o museu mais próximo à localização informada.
   */
  @GetMapping("/closest")
  public ResponseEntity<MuseumDto> getMuseumByLocation(
      @RequestParam("lat") double latitude,
      @RequestParam("lng") double longitude,
      @RequestParam("max_dist_km") double maxDistance) {

    Coordinate coordinate = new Coordinate(latitude, longitude);
    Museum response = service.getClosestMuseum(coordinate, maxDistance);
    return ResponseEntity.ok().body(ModelDtoConverter.modelToDto(response));
  }

  @GetMapping("/{id}")
  public ResponseEntity<MuseumDto> getMuseumById(@PathVariable Long id) {
    Museum response = service.getMuseum(id);
    return ResponseEntity.ok().body(ModelDtoConverter.modelToDto(response));
  }
}
