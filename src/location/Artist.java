package location;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents an artist who has worked on a movie.
 */
public class Artist implements Serializable {
  /**
   * The movies of the artist.
   */
  private Set<Movie> movies = new HashSet<>();

  /**
   * The last name of the artist.
   */
  private String lastName;

  /**
   * The first name of the artist.
   */
  private String firstName;

  /**
   * The nationality of the artist.
   */
  private String nationality;

  /**
   * Constructs a new artist.
   */
  public Artist() {
  }

  /**
   * Constructs a new artist with the specified last name
   * and first name.
   *
   * @param lastName the last name of the artist.
   * @param firstName the first name of the artist.
   * @param nationality the nationality of the artist.
   */
  public Artist(String lastName, String firstName, String nationality) {
    this.lastName = lastName;
    this.firstName = firstName;
    this.nationality = nationality;
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
   * Sets the last name of the artist.
   * 
   * @param lastName the last name of the artist.
   * @return the artist.
   */
  public Artist setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Retrieves the first name of the artist.
   *
   * @return the first name of the artist.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name of the artist.
   * 
   * @param firstName the first name of the artist.
   * @return the artist.
   */
  public Artist setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Retrieves the nationality of the artist.
   *
   * @return the nationality of the artist.
   */
  public String getNationality() {
    return this.nationality;
  }

  /**
   * Retrieves the movies of the artist.
   * 
   * @return the movies of the artist.
   */
  public Set<Movie> getMovies() {
    return movies;
  }

  /**
   * Adds a movie to the artist.
   * 
   * @param movie the movie to add.
   * @return the artist.
   */
  public Artist addMovie(Movie movie) {
    movies.add(movie);
    return this;
  }

  /**
   * Returns a string representation of the artist.
   *
   * @return a string representation of the artist.
   */
  @Override
  public String toString() {
    return lastName + " " + firstName;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   * 
   * @param obj the reference object with which to compare.
   * @return {@code true} if this object is the same as the obj argument;
   *         {@code false} otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj){
      return true;
    }
    if (obj == null){
      return false;
    }
    if (getClass() != obj.getClass()){
      return false;
    }
    Artist other = (Artist) obj;
    if (movies == null) {
      if (other.movies != null){
        return false;
      }
    } else if (!movies.equals(other.movies)){
      return false;
    }
    if (lastName == null) {
      if (other.lastName != null){
        return false;
      }
    } else if (!lastName.equals(other.lastName)){
      return false;
    }
    if (firstName == null) {
      if (other.firstName != null){
        return false;
      }
    } else if (!firstName.equals(other.firstName)){
      return false;
    }
    return true;
  }

  /**
   * @brief Serialize the artist to a JSON string.
   * 
   * @return the JSON string.
   */
  public String serialize(){
    return "{"
      + "\"lastName\":\"" + lastName + "\","
      + "\"firstName\":\"" + firstName + "\","
      + "\"nationality\":\"" + nationality + "\""
      + "}";
  }

  /**
   * @brief Deserialize the artist from a JSON string.
   * 
   * @param json the JSON string.
   * @return the artist.
   */
  public static Artist parse(String json){
    Artist artist = new Artist();
    // Récupère la donnée qui suit "lastName":
    int index = json.indexOf("\"lastName\":\"") + "\"lastName\":\"".length();
    int index2 = json.indexOf("\"", index);
    artist.lastName = json.substring(index, index2);
    // Récupère la donnée qui suit "firstName":
    index = json.indexOf("\"firstName\":\"") + "\"firstName\":\"".length();
    index2 = json.indexOf("\"", index);
    artist.firstName = json.substring(index, index2);
    // Récupère la donnée qui suit "nationality":
    index = json.indexOf("\"nationality\":\"") + "\"nationality\":\"".length();
    index2 = json.indexOf("\"", index);
    artist.nationality = json.substring(index, index2);
    return artist;
  }
}
