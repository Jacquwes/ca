package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import location.LocationAdmin;
import location.Artist;
import location.Movie;
import location.Genre;
import java.util.Set;
import javafx.stage.FileChooser;
import java.io.File;

/**
 * Controleur JavaFX de la fenêtre d'administration.
 *
 * @author Eric Cariou
 *
 */
public class AdministrationControleur {

  private LocationAdmin locationAdmin = new LocationAdmin();
  
  @FXML
  private CheckBox checkBoxLocationFilm;
  
  @FXML
  private TextField entreeAffiche;
  
  @FXML
  private TextField entreeAnneeFilm;
  
  @FXML
  private TextField entreeNationaliteArtiste;
  
  @FXML
  private TextField entreeNomArtiste;
  
  @FXML
  private TextField entreeNomPrenomRealisateur;
  
  @FXML
  private TextField entreePrenomArtiste;
  
  @FXML
  private TextField entreeTitreFilm;
  
  @FXML
  private Label labelListeArtistes;
  
  @FXML
  private Label labelListeFilms;
  
  @FXML
  private ListView<String> listeArtistes;
  
  @FXML
  private ChoiceBox<String> listeChoixAgeLimite;
  
  @FXML
  private ListView<String> listeFilms;
  
  @FXML
  private ListView<String> listeGenresFilm;
  
  @FXML
  private ListView<String> listeTousGenres;
  
  @FXML
  void actionBoutonAfficherArtistesActeurs(ActionEvent event) {
    Set<Artist> actors = this.locationAdmin.getAllActors();
    System.out.println("Actors: " + actors);
    this.listeArtistes.getItems().clear();
    for (Artist actor : actors) {
      this.listeArtistes.getItems().add(actor.toString());
    }

    this.labelListeArtistes.setText("Liste des acteurs");
  }
  
  @FXML
  void actionBoutonAfficherArtistesRealisateurs(ActionEvent event) {
    Set<Artist> directors = this.locationAdmin.getAllDirectors();
    System.out.println("Directors: " + directors);
    this.listeArtistes.getItems().clear();
    for (Artist director : directors) {
      this.listeArtistes.getItems().add(director.toString());
    }    

    this.labelListeArtistes.setText("Liste des réalisateurs");
  }
  
  @FXML
  void actionBoutonAfficherFilmsActeurSelectionne(ActionEvent event) {
    String selectedActor = this.listeArtistes.getSelectionModel().getSelectedItem();
    String name = selectedActor.split(" ")[0];
    String firstName = selectedActor.split(" ")[1];
    Artist actor = this.locationAdmin.getArtist(name, firstName);
    System.out.println("Actor: " + actor);

    Set<Movie> movies = this.locationAdmin.getMoviesByActor(actor);
    System.out.println("Movies: " + movies);

    this.listeFilms.getItems().clear();
    for (Movie movie : movies) {
      this.listeFilms.getItems().add(movie.toString());
    }

    this.labelListeFilms.setText("Liste des films de l'acteur " + actor);
  }
  
  @FXML
  void actionBoutonAfficherFilmsDuRealisateur(ActionEvent event) {
    String selectedDirector = this.listeArtistes.getSelectionModel().getSelectedItem();
    String name = selectedDirector.split(" ")[0];
    String firstName = selectedDirector.split(" ")[1];
    Artist director = this.locationAdmin.getArtist(name, firstName);
    System.out.println("Director: " + director);

    Set<Movie> movies = this.locationAdmin.getMoviesByDirector(director);
    System.out.println("Movies: " + movies);

    this.listeFilms.getItems().clear();
    for (Movie movie : movies) {
      this.listeFilms.getItems().add(movie.toString());
    }
  }
  
  @FXML
  void actionBoutonAfficherFilmsRealisateurSelectionne(ActionEvent event) {
    String selectedDirector = this.listeArtistes.getSelectionModel().getSelectedItem();
    String name = selectedDirector.split(" ")[0];
    String firstName = selectedDirector.split(" ")[1];
    Artist director = this.locationAdmin.getArtist(name, firstName);
    System.out.println("Director: " + director);

    Set<Movie> movies = this.locationAdmin.getMoviesByDirector(director);
    System.out.println("Movies: " + movies);

    this.listeFilms.getItems().clear();
    for (Movie movie : movies) {
      this.listeFilms.getItems().add(movie.toString());
    }

    this.labelListeFilms.setText("Liste des films du réalisateur " + director);
  }
  
  @FXML
  void actionBoutonAfficherTousActeursFilm(ActionEvent event) {
    String selectedMovie = this.listeFilms.getSelectionModel().getSelectedItem();
    String title = selectedMovie.split(" \\(")[0];
    Movie movie = this.locationAdmin.getMovie(title);
    System.out.println("Movie: " + movie);

    this.listeArtistes.getItems().clear();
    for (Artist actor : movie.getActors()) {
      this.listeArtistes.getItems().add(actor.toString());
    }

    this.labelListeArtistes.setText("Liste des acteurs du film " + movie);
  }
  
  @FXML
  void actionBoutonAfficherTousArtistes(ActionEvent event) {
    Set<Artist> artists = this.locationAdmin.getAllArtists();
    System.out.println("Artists: " + artists);
    this.listeArtistes.getItems().clear();
    for (Artist artist : artists) {
      this.listeArtistes.getItems().add(artist.toString());
    }

    this.labelListeArtistes.setText("Liste de tous les artistes");  
  }
  
  @FXML
  void actionBoutonAjouterActeurFilm(ActionEvent event) {
    String selectedArtist = this.listeArtistes.getSelectionModel().getSelectedItem();
    String name = selectedArtist.split(" ")[0];
    String firstName = selectedArtist.split(" ")[1];
    Artist actor = this.locationAdmin.getArtist(name, firstName);
    System.out.println("Actor: " + actor);

    String selectedMovie = this.listeFilms.getSelectionModel().getSelectedItem();
    String title = selectedMovie.split(" \\(")[0];
    Movie movie = this.locationAdmin.getMovie(title);
    System.out.println("Movie: " + movie);

    this.locationAdmin.addActors(movie, actor);
  }
  
  @FXML
  void actionBoutonAjouterGenreFilm(ActionEvent event) {
    String selectedGenre = this.listeTousGenres.getSelectionModel().getSelectedItem();
    Genre genre = Genre.valueOf(selectedGenre);
    System.out.println("Genre: " + genre);

    String selectedMovie = this.listeFilms.getSelectionModel().getSelectedItem();
    String title = selectedMovie.split(" \\(")[0];
    Movie movie = this.locationAdmin.getMovie(title);
    System.out.println("Movie: " + movie);

    movie.addGenre(genre);
    this.listeGenresFilm.getItems().add(genre.toString());
  }
  
  @FXML
  void actionBoutonChercherArtiste(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonChercherFilm(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonChoisirArtisteSelectionneRealisateur(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonEnregistrerArtiste(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonEnregistrerFilm(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonNouveauArtiste(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonNouveauFilm(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonParcourirAffiche(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonSupprimerArtiste(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonSupprimerFilm(ActionEvent event) {
    
  }
  
  @FXML
  void actionMenuApropos(ActionEvent event) {
    
  }
  
  @FXML
  void actionMenuCharger(ActionEvent event) {
    
  }
  
  @FXML
  void actionMenuQuitter(ActionEvent event) {
    
  }
  
  @FXML
  void actionMenuSauvegarder(ActionEvent event) {
    
  }
  
  @FXML
  void actionListeSelectionArtiste(MouseEvent event) {
    
  }
  
  @FXML
  void actionListeSelectionFilm(MouseEvent event) {
  /**
   * Initialise les données de l'application.
   *
   */
  void initializeLocationAdmin(){
    // Initialise des acteurs/directeurs/films etc
    Artist reeves = this.locationAdmin.createArtist("Reeves", "Keanu", "USA");
    Artist fishburne = this.locationAdmin.createArtist("Fishburne", "Laurence", "USA");
    Artist director = this.locationAdmin.createArtist("Wachowski", "Lana", "USA");
    Artist director2 = this.locationAdmin.createArtist("Wachowski", "Lilly", "USA");
    Movie matrix = this.locationAdmin.createMovie("The Matrix", null, 1999, 16);
    Movie matrix2 = this.locationAdmin.createMovie("The Matrix Reloaded", null, 2003, 16);
    this.locationAdmin.addActors(matrix, reeves);
    this.locationAdmin.addActors(matrix, fishburne);
    this.locationAdmin.addActors(matrix2, reeves);
    this.locationAdmin.addActors(matrix2, fishburne);
    this.locationAdmin.addDirector(matrix, director);
    this.locationAdmin.addDirector(matrix2, director2);
    // Ajout de genre 
    matrix.addGenre(Genre.ScienceFiction);
    matrix2.addGenre(Genre.Action);
  
    initializeGenreList();
  }
  
  @FXML
  void initialize() {
    // Mettre ici le code d'initialisation du contenu de la fenêtre
    initializeLocationAdmin();
  }
}
