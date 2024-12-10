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
    
  }
  
  @FXML
  void actionBoutonAfficherArtistesRealisateurs(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonAfficherFilmsActeurSelectionne(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonAfficherFilmsDuRealisateur(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonAfficherFilmsRealisateurSelectionne(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonAfficherTousActeursFilm(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonAfficherTousArtistes(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonAjouterActeurFilm(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonAjouterGenreFilm(ActionEvent event) {
    
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
