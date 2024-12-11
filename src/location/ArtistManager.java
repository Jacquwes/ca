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
  private static Set<Artist> artists = new HashSet<>();

  /**
   * Represents all the actors.
   */
  private static Set<Artist> actors = new HashSet<>();
  
  /**
   * Represents all the directors.
   */
  private static Set<Artist> directors = new HashSet<>();

  /**
   * Constructs a new ArtistManager.
   */
  public ArtistManager() {
  }

  /** 
   * @return the artists.
   */
  public Set<Artist> getArtists() {
    return ArtistManager.artists;
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
    return ArtistManager.actors;
  }

  public Set<Artist> getDirectors() {
    return ArtistManager.directors;
  }

  public boolean deleteArtist(Artist artist) {
    return ArtistManager.actors.remove(artist) || ArtistManager.directors.remove(artist) || ArtistManager.artists.remove(artist);
  }

  public void addActor(Artist artist) {
    if (!ArtistManager.artists.contains(artist)) {
      ArtistManager.artists.add(artist);
    }
    ArtistManager.actors.add(artist);
  }

  public void addDirector(Artist artist) {
    if (!ArtistManager.artists.contains(artist)) {
      ArtistManager.artists.add(artist);
    }
    ArtistManager.directors.add(artist);
  }

  public boolean addArtist(Artist artist) {
    return ArtistManager.artists.add(artist);
  }

}
