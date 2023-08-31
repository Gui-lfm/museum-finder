package com.betrybe.museumfinder.dto;

import com.betrybe.museumfinder.model.Coordinate;

/**
 * Dto para a criação de museus na api.
 */
public record MuseumCreationDto(String name, String description, String address,
                                String collectionType, String subject, String url,
                                Coordinate coordinate) {

}
