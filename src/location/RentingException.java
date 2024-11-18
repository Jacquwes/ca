package location;

/**
 * Exception thrown when there is a problem during the rental of a movie by a
 * user.
 *
 * @author Florent Delalande
 * @author Paul Hariel
 */
public class RentingException extends RuntimeException {

  /**
   * Serialization identifier.
   */
  private static final long serialVersionUID = -3365565475174636290L;

  /**
   * Constructs a new RentingException with the specified message.
   *
   * @param message the message of the exception
   */
  public RentingException(String message) {
    super(message);
  }

}
