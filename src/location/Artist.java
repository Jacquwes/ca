package location;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Represents an artist who has worked on a movie.
 */
public class Artist {
  /**
   * Represents all the artists.
   */
  private static Set<Artist> artists = new HashSet<>();
  
  /** 
   * @return the artists.
   */
  public static Set<Artist> getArtists() {
    return Artist.artists;
  }

  /**
   * Retrieves the artists matching the specified predicate.
   * 
   * @param p the predicate to match
   * @return the artists matching the specified predicate.
   */
  public static Set<Artist> getArtists(Predicate<Artist> p) {
    Set<Artist> result = new HashSet<>();

    for (Artist artist : artists) {
      if (p.test(artist)) {
        result.add(artist);
      }
    }

    return result;
  }

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
   * Constructs a new Artist.
   */
  public Artist() {
    artists.add(this);
  }

  /**
   * Constructs a new Artist with the specified last name and first name.
   *
   * @param lastName  the last name of the artist
   * @param firstName the first name of the artist
   */
  public Artist(String lastName, String firstName) {
    this.lastName = lastName;
    this.firstName = firstName;
    artists.add(this);
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
}
