package location;

public class Actor extends Artist {
  /**
   * Constructs a new Actor with the specified last name and first name.
   *
   * @param lastName the last name of the actor
   * @param firstName the first name of the actor
   */
  Actor(String lastName, String firstName) {
    super(lastName, firstName);
  }

  /**
   * Constructs a new Actor.
   */
  Actor() {
    super();
  }
}