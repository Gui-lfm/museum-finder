package com.betrybe.museumfinder.exception;

/**
 * Exceção lançada ao não encontrar um museu.
 */
public class MuseumNotFoundException extends RuntimeException {

  public MuseumNotFoundException(String message) {
    super(message);
  }
}
