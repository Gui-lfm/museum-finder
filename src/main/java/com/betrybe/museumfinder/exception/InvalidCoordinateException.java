package com.betrybe.museumfinder.exception;

/**
 * Exceção lançada ao enviar coordenadas inválidas.
 */
public class InvalidCoordinateException extends RuntimeException {

  public InvalidCoordinateException() {
    super("Coordenada inválida!");
  }
}
