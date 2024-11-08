package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import location.Artist;
import location.Movie;

public class ArtistTest {
  private Artist artist;
  private Artist artist2;
  
  @Before
  public void setUp() {
    Artist.getArtists().clear();
    artist = new Artist("Doe", "John");
    artist2 = new Artist("Smith", "Jane");
  }
  
  @Test
  public void testConstructor() {
    assertEquals("Doe", artist.getLastName());
    assertEquals("John", artist.getFirstName());
    assertTrue(Artist.getArtists().contains(artist));
  }
  
  @Test 
  public void testDefaultConstructor() {
    Artist emptyArtist = new Artist();
    assertNull(emptyArtist.getLastName());
    assertNull(emptyArtist.getFirstName());
    assertTrue(Artist.getArtists().contains(emptyArtist));
  }
  
  @Test
  public void testSetters() {
    artist.setLastName("Brown").setFirstName("Bob");
    assertEquals("Brown", artist.getLastName());
    assertEquals("Bob", artist.getFirstName());
  }
  
  @Test
  public void testMovies() {
    Movie movie = new Movie();
    assertTrue(artist.getMovies().isEmpty());
    artist.addMovie(movie);
    assertTrue(artist.getMovies().contains(movie));
    assertEquals(1, artist.getMovies().size());
  }
  
  @Test
  public void testGetArtists() {
    assertEquals(2, Artist.getArtists().size());
    assertTrue(Artist.getArtists().contains(artist));
    assertTrue(Artist.getArtists().contains(artist2));
  }
  
  @Test
  public void testGetArtistsWithPredicate() {
    Set<Artist> filtered = Artist.getArtists(a -> a.getLastName().equals("Doe"));
    assertEquals(1, filtered.size());
    assertTrue(filtered.contains(artist));
  }


}