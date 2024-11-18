package location;

/**
 * Represents an actor.
 */
public class Actor extends Artist {
  /**
   * Constructs a new Actor with the specified last name and first name.
   *
   * @param lastName  the last name of the actor
   * @param firstName the first name of the actor
   */
  public Actor(String lastName, String firstName) {
    super(lastName, firstName);
  }

  /**s
   * Constructs a new Actor.
   */
  public Actor() {
    super();
  }
}