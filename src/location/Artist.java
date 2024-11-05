package location;

// TO BE COMPLETED

public class Artist {
  /**
   * The last name of the artist.
   */
  private String lastName;

  /**
   * The first name of the artist.
   */
  private String firstName;

  /**
   * Constructs a new Artist with the specified last name and first name.
   *
   * @param lastName  the last name of the artist
   * @param firstName the first name of the artist
   */
  Artist(String lastName, String firstName) {
    this.lastName = lastName;
    this.firstName = firstName;
  }

  /**
   * Retrieves the last name of the artist.
   *
   * @return the last name of the artist.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Retrieves the first name of the artist.
   *
   * @return the first name of the artist.
   */
  public String getFirstName() {
    return firstName;
  }
}
