package location;

public class Director extends Artist {
  /**
   * Constructs a new Director with the specified last name and first name.
   *
   * @param lastName  the last name of the director
   * @param firstName the first name of the director
   */
  public Director(String lastName, String firstName) {
    super(lastName, firstName);
  }

  /**
   * Constructs a new Director.
   */
  public Director() {
    super();
  }
}
