package com.betrybe.museumfinder.dto;

import com.betrybe.museumfinder.model.Coordinate;

/**
 * DTO da claseu Museum.
 */
public record MuseumDto(long id, String name, String description, String address,
                        String collectionType, String subject, String url, Coordinate coordinate) {

}
