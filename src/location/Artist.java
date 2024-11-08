package location;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

// TO BE COMPLETED

public class Artist {
  private static Set<Artist> artists = new HashSet<>();
  private Set<Movie> movies = new HashSet<>();

  public static Set<Artist> getArtists() {
    return Artist.artists;
  }

  public static Set<Artist> getArtists(Predicate<Artist> p) {
    Set<Artist> result = new HashSet<>();

    for (Artist artist : artists)
      if (p.test(artist))
        result.add(artist);

    return result;
  }

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
   * @param firstName the first name of the artist.
   * @return the artist.
   */
  public Artist setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Retrieves the movies of the artist.
   * @return the movies of the artist.
   */
  public Set<Movie> getMovies() {
    return movies;
  }
  
  /**
   * Adds a movie to the artist.
   * @param movie the movie to add.
   * @return the artist.
   */
  public Artist addMovie(Movie movie) {
    movies.add(movie);
    return this;
  }
}
