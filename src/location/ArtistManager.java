package location;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Manages all the artists.
 */
public class ArtistManager {
  /**
   * Represents all the artists.
   */
  private Set<Artist> actors = new HashSet<>();
  
  /**
   * Represents all the directors.
   */
  private Set<Artist> directors = new HashSet<>();

  /**
   * Constructs a new ArtistManager.
   */
  public ArtistManager() {
  }

  /** 
   * @return the artists.
   */
  public Set<Artist> getArtists() {
    Set<Artist> result = new HashSet<Artist>();
    result.addAll(this.actors);
    result.addAll(this.directors);
    return result;
  }

  /**
   * Retrieves the artists matching the specified predicate.
   * 
   * @param p the predicate to match
   * @return the artists matching the specified predicate.
   */
  public Set<Artist> getArtists(Predicate<Artist> p) {
    Set<Artist> result = new HashSet<>();

    for (Artist artist : this.getArtists()) {
      if (p.test(artist)) {
        result.add(artist);
      }
    }

    return result;
  }

  public Set<Artist> getActors() {
    return this.actors;
  }

  public Set<Artist> getDirectors() {
    return this.directors;
  }

  public boolean deleteArtist(Artist artist) {
    return this.actors.remove(artist) || this.directors.remove(artist);
  }

  public void addActor(Artist artist) {
    this.actors.add(artist);
  }

  public void addDirector(Artist artist) {
    this.directors.add(artist);
  }
}
