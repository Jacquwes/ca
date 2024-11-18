package location;

import java.io.IOException;
import java.util.Set;

/**
 * Services for managing movies from the administrator's point of view.
 *
 * @author Florent Delalande
* @author Paul Hariel
 */
public interface AdministrationInterface {
  
  /**
   * Creates a new artist. There must not already be an artist with the same lastName and firstName.
   *
   * @param lastName the last name of the artist (non-empty string)
   * @param firstName the first name of the artist (empty string "" if the artist only has a last name and no first name)
   * @param nationality the nationality of the artist
   * @return the created artist or <code>null</code> in case of error (invalid parameters or an artist with the same values already exists)
   */
  Artist createArtist(String lastName, String firstName, String nationality);
  
  /**
   * Deletes an artist from the set of artists. Cannot be done if the artist is associated with at least one movie as an actor or director.
   *
   * @param artist the artist to delete
   * @return <code>true</code> if the deletion was successful, <code>false</code> otherwise
   */
  boolean deleteArtist(Artist artist);
  
  /**
   * Creates a new movie. There must not already be a movie with the same title.
   *
   * @param title the title of the movie (non-empty string)
   * @param director the director of the movie (non <code>null</code>)
   * @param year the year the movie was made
   * @param ageLimit the minimum age to watch the movie (0 if no age limit)
   * @return the created movie or <code>null</code> in case of problem (a movie with the same title already exists or the parameters were not valid)
   */
  Movie createMovie(String title, Artist director, int year, int ageLimit);
  
  /**
   * Adds actors to a movie.
   *
   * @param movie the movie to modify
   * @param actors the list of actors to add. If actors from the list are already associated with the movie, they are not added again.
   * @return <code>true</code> if at least one actor from the list was added to the movie's actors, <code>false</code> otherwise
   */
  boolean addActors(Movie movie, Artist... actors);
  
  /**
   * Adds genres to a movie.
   *
   * @param movie the movie to modify
   * @param genres the list of genres to add. If genres from the list are already associated with the movie, they are not added again.
   * @return <code>true</code> if at least one genre from the list was added to the movie's genres, <code>false</code> otherwise
   */
  boolean addGenres(Movie movie, Genre... genres);
  
  /**
   * Adds a poster to a movie. If the movie already had a poster, it is replaced by the new one.
   *
   * @param movie the movie to which to add a poster
   * @param file the path of the file containing the poster image (in JPG, PNG format, etc.)
   * @return <code>true</code> if the poster was modified (the format and size were valid)
   * @throws IOException in case of file reading error
   */
  boolean addPoster(Movie movie, String file) throws IOException;
  
  /**
   * Deletes a movie from the set of movies.
   *
   * @param movie the movie to delete
   * @return <code>true</code> if the movie was deleted or <code>false</code> in case of problem (the movie did not exist or the parameter was <code>null</code>)
   */
  boolean deleteMovie(Movie movie);
  
  /**
   * Returns the set of movies.
   *
   * @return the set of movies
   */
  Set<Movie> getAllMovies();
  
  /**
   * Returns the set of actors.
   *
   * @return the set of actors
   */
  Set<Artist> getAllActors();
  
  /**
   * Returns the set of directors.
   *
   * @return the set of directors
   */
  Set<Artist> getAllDirectors();
  
  /**
   * Returns the set of movies by a director.
   *
   * @param director the director whose movies are wanted
   * @return the set of movies by the director or <code>null</code> if none exist
   */
  Set<Movie> getMoviesByDirector(Artist director);
  
  /**
   * Returns the set of movies by an actor.
   *
   * @param actor the actor whose movies are wanted
   * @return the set of movies by the actor or <code>null</code> if none exist
   */
  Set<Movie> getMoviesByActor(Artist actor);
  
  /**
   * Searches for an artist by their last name and first name.
   *
   * @param lastName the last name of the artist
   * @param firstName the first name of the artist
   * @return the artist if found or <code>null</code> otherwise
   */
  Artist getArtist(String lastName, String firstName);
  
  /**
   * Searches for a movie by its title.
   *
   * @param title the title of the movie
   * @return the movie if found or <code>null</code> otherwise
   */
  Movie getMovie(String title);
  
  /**
   * Opens a movie for rental. Does nothing if the movie was already open for rental.
   *
   * @param movie the movie to open for rental
   * @return <code>true</code> if the movie is open for rental, <code>false</code> in case of problem (the movie was not found or value <code>null</code>)
   */
  boolean openRental(Movie movie);
  
  /**
   * Closes the rental of a movie. Does nothing if the movie was not open for rental.
   *
   * @param movie the movie to close for rental
   * @return <code>true</code> if the movie is closed for rental, <code>false</code> in case of problem (the movie was not found or value <code>null</code>)
   */
  boolean closeRental(Movie movie);
}
