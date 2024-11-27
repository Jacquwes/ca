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
  private Set<Artist> artists ;

  /**
   * Constructs a new ArtistManager.
   */
  public ArtistManager() {
    this.artists = new HashSet<>();
  }

  /** 
   * @return the artists.
   */
  public Set<Artist> getArtists() {
    return this.artists;
  }

  /**
   * Retrieves the artists matching the specified predicate.
   * 
   * @param p the predicate to match
   * @return the artists matching the specified predicate.
   */
  public Set<Artist> getArtists(Predicate<Artist> p) {
    Set<Artist> result = new HashSet<>();

    for (Artist artist : this.artists) {
      if (p.test(artist)) {
        result.add(artist);
      }
    }

    return result;
  }
    
}
