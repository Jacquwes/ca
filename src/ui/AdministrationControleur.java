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
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    String name = this.entreeNomArtiste.getText();
    String firstName = this.entreePrenomArtiste.getText();
    Artist artist = this.locationAdmin.getArtist(name, firstName);
    if (artist != null) {
      this.entreeNationaliteArtiste.setText(artist.getNationality());
    }
    this.listeArtistes.getItems().clear();
    this.listeArtistes.getItems().add(artist.toString());
  }
  
  @FXML
  void actionBoutonChercherFilm(ActionEvent event) {
    String title = this.entreeTitreFilm.getText();
    Movie movie = this.locationAdmin.getMovie(title);
    if (movie != null) {
      this.entreeAnneeFilm.setText(String.valueOf(movie.getYear()));
      this.listeChoixAgeLimite.setValue(String.valueOf(movie.getMinimumAge()));
      this.entreeNomPrenomRealisateur.setText(movie.getDirector().toString());
      this.checkBoxLocationFilm.setSelected(movie.isAvailable());
      this.listeGenresFilm.getItems().clear();
      for (Genre genre : movie.getGenres()) {
        this.listeGenresFilm.getItems().add(genre.toString());
      }
    }
    this.listeFilms.getItems().clear();
    this.listeFilms.getItems().add(movie.toString());

    this.labelListeFilms.setText("Résultat de la recherche");
  }
  
  @FXML
  void actionBoutonChoisirArtisteSelectionneRealisateur(ActionEvent event) {
    String selectedArtist = this.listeArtistes.getSelectionModel().getSelectedItem();
    this.entreeNomPrenomRealisateur.setText(selectedArtist);
  }
  
  @FXML
  void actionBoutonEnregistrerArtiste(ActionEvent event) {
    if (!this.entreeNomArtiste.getText().isEmpty() && 
        !this.entreePrenomArtiste.getText().isEmpty() && 
        !this.entreeNationaliteArtiste.getText().isEmpty()) 
    {
      Artist artist = this.locationAdmin.createArtist(this.entreeNomArtiste.getText(), this.entreePrenomArtiste.getText(), this.entreeNationaliteArtiste.getText());
      this.listeArtistes.getItems().add(artist.toString());
      // TODO: Ajouter l'artiste à la liste des artistes
    }

  }
  
  @FXML
  void actionBoutonEnregistrerFilm(ActionEvent event) {
    if (!this.entreeTitreFilm.getText().isEmpty() && 
        !this.entreeAnneeFilm.getText().isEmpty() && 
        !this.entreeNomPrenomRealisateur.getText().isEmpty()) 
    {
      Artist director = this.locationAdmin.getArtist(this.entreeNomPrenomRealisateur.getText().split(" ")[0], this.entreeNomPrenomRealisateur.getText().split(" ")[1]);
      Movie movie = this.locationAdmin.createMovie(this.entreeTitreFilm.getText(), null, Integer.parseInt(this.entreeAnneeFilm.getText()), Integer.parseInt(this.listeChoixAgeLimite.getValue()));
      this.locationAdmin.addDirector(movie, director);
    }
  }
  
  @FXML
  void actionBoutonNouveauArtiste(ActionEvent event) {
    this.entreeNomArtiste.setText("");
    this.entreePrenomArtiste.setText("");
    this.entreeNationaliteArtiste.setText("");
  }
  
  @FXML
  void actionBoutonNouveauFilm(ActionEvent event) {
    this.entreeTitreFilm.setText("");
    this.entreeAnneeFilm.setText("");
    this.listeChoixAgeLimite.setValue("0");
    this.entreeNomPrenomRealisateur.setText("");
    this.listeGenresFilm.getItems().clear();
  }
  
  @FXML
  void actionBoutonParcourirAffiche(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une affiche");
    File file = fileChooser.showOpenDialog(null);
    if (file != null) {
      this.entreeAffiche.setText(file.getAbsolutePath());
    }
    
  }
  
  @FXML
  void actionBoutonSupprimerArtiste(ActionEvent event) {
    String selectedArtist = this.listeArtistes.getSelectionModel().getSelectedItem();
    String name = selectedArtist.split(" ")[0];
    String firstName = selectedArtist.split(" ")[1];
    Artist artist = this.locationAdmin.getArtist(name, firstName);
    this.locationAdmin.deleteArtist(artist);

    actionBoutonAfficherTousArtistes(event);
  }
  
  @FXML
  void actionBoutonSupprimerFilm(ActionEvent event) {
    String selectedMovie = this.listeFilms.getSelectionModel().getSelectedItem();
    String title = selectedMovie.split(" \\(")[0];
    Movie movie = this.locationAdmin.getMovie(title);
    this.locationAdmin.deleteMovie(movie);
  }
  
  @FXML
  void actionMenuApropos(ActionEvent event) {
    StackPane secondaryLayout = new StackPane();
    Label label = new Label("Application créée par\nFlorent Delalande et Paul Hariel");
    label.setStyle("-fx-font-size: 16px;");
    secondaryLayout.getChildren().add(label);

    Scene secondScene = new Scene(secondaryLayout, 300, 150);

    Stage newWindow = new Stage();
    newWindow.setTitle("À propos");
    newWindow.setScene(secondScene);

    newWindow.setX(200);
    newWindow.setY(100);

    newWindow.show();
    
  }
  
  @FXML
  void actionMenuCharger(ActionEvent event) {
    System.out.println("actionMenuCharger");
  }
  
  @FXML
  void actionMenuQuitter(ActionEvent event) {
    System.out.println("actionMenuQuitter");
  }
  
  @FXML
  void actionMenuSauvegarder(ActionEvent event) {
    System.out.println("actionMenuSauvegarder");
  }
  
  @FXML
  void actionListeSelectionArtiste(MouseEvent event) {
    String selectedArtist = this.listeArtistes.getSelectionModel().getSelectedItem();
    String name = selectedArtist.split(" ")[0];
    String firstName = selectedArtist.split(" ")[1];
    Artist artist = this.locationAdmin.getArtist(name, firstName);

    this.entreeNomArtiste.setText(artist.getLastName());
    this.entreePrenomArtiste.setText(artist.getFirstName());
    this.entreeNationaliteArtiste.setText(artist.getNationality());
  }
  
  @FXML
  void actionListeSelectionFilm(MouseEvent event) {
    String selectedMovie = this.listeFilms.getSelectionModel().getSelectedItem();
    String title = selectedMovie.split(" \\(")[0];
    Movie movie = this.locationAdmin.getMovie(title);

    this.entreeTitreFilm.setText(movie.getTitle());
    this.entreeAnneeFilm.setText(String.valueOf(movie.getYear()));
    this.listeChoixAgeLimite.setValue(String.valueOf(movie.getMinimumAge()));
    System.out.println("Minimum age: " + movie.getMinimumAge());
    this.entreeNomPrenomRealisateur.setText(movie.getDirector().toString());
    this.checkBoxLocationFilm.setSelected(movie.isAvailable());
    this.listeGenresFilm.getItems().clear();
    for (Genre genre : movie.getGenres()) {
      this.listeGenresFilm.getItems().add(genre.toString());
    }
  }

  void initializeGenreList(){
    for (Genre genre : Genre.values()) {
      this.listeTousGenres.getItems().add(genre.toString());
    }
  }
  
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
