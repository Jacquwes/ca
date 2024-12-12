package location;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Manages all the artists.
 */
public class ArtistManager implements Serializable {

  /**
   * Represents all the artists.
   */
  private Set<Artist> artists = new HashSet<>();

  /**
   * Represents all the actors.
   */
  private Set<Artist> actors = new HashSet<>();
  
  /**
   * Represents all the directors.
   */
  private Set<Artist> directors = new HashSet<>();

  /**
   * Constructs a new artist manager.
   */
  public ArtistManager() {
  }

  /**
   * Retrieves the artists.
   *
   * @return the artists.
   */
  public Set<Artist> getArtists() {
    return this.artists;
  }

  /**
   * Retrieves the artists matching the specified predicate.
   *
   * @param p the predicate to match.
   *
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

  /**
   * Retrieves the actors.
   *
   * @return the actors.
   */
  public Set<Artist> getActors() {
    return this.actors;
  }

  /**
   * Retrieves the directors.
   *
   * @return the directors
   */
  public Set<Artist> getDirectors() {
    return this.directors;
  }

  /**
   * Deletes an artist.
   *
   * @param artist the artist to delete.
   *
   * @return true if the artist was deleted, false otherwise.
   */
  public boolean deleteArtist(Artist artist) {
    boolean result = false;
    result = result || this.artists.remove(artist);
    result = result || this.actors.remove(artist);
    result = result || this.directors.remove(artist);
    return result;
  }

  /**
   * Adds an actor.
   *
   * @param artist the actor to add.
   */
  public void addActor(Artist artist) {
    if (!this.artists.contains(artist)) {
      this.artists.add(artist);
    }
    this.actors.add(artist);
  }

  /**
   * Adds a director.
   *
   * @param artist the director to add.
   */
  public void addDirector(Artist artist) {
    if (!this.artists.contains(artist)) {
      this.artists.add(artist);
    }
    this.directors.add(artist);
  }

  /**
   * Adds an artist.
   *
   * @param artist the artist to add.
   *
   * @return true if the artist was added, false otherwise.
   */
  public boolean addArtist(Artist artist) {
    return this.artists.add(artist);
  }

  /**
   * Serializes the artists.
   *
   * @return the serialized artists.
   */
  public String serialize() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (Artist artist : this.getArtists()) {
      sb.append(artist.serialize());
      sb.deleteCharAt(sb.length() - 1);
      sb.append(",\"isActor\":\"" + this.getActors().contains(artist) + "\",");
      sb.append("\"isDirector\":\"" + this.getDirectors().contains(artist) + "\"},");
    }
    sb.deleteCharAt(sb.length() - 1);
    sb.append("]");
    return sb.toString();
  }

  /**
   * Deserializes the artists.
   *
   * @param artists the artists to deserialize.
   */
  public void parse(String artists) {
    String[] artistStrings = artists.split("},");
    for (String artistString : artistStrings) {
      Artist artist = Artist.parse(artistString);
      this.addArtist(artist);
      if (artistString.contains("\"isActor\":\"true\"")) {
        this.addActor(artist);
      }
      if (artistString.contains("\"isDirector\":\"true\"")) {
        this.addDirector(artist);
      }
    }
  }

}
