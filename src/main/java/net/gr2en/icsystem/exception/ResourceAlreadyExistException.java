package net.gr2en.icsystem.exception;

public class ResourceAlreadyExistException extends RuntimeException {

  public ResourceAlreadyExistException() {
    super();
  }

  public ResourceAlreadyExistException(String message) {
    super(message);
  }

  public ResourceAlreadyExistException(String message, Throwable cause) {
    super(message, cause);
  }

}
