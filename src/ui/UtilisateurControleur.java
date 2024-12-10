package ui;

import location.Artist;
import location.Genre;
import location.Location;
import location.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * Controleur JavaFX de la fenêtre utilisateur.
 *
 * @author Eric Cariou
 *
 */
public class UtilisateurControleur {

  @FXML
  private CheckBox checkFilmLouable;

  @FXML
  private TextField entreeAdresseUtilisateur;

  @FXML
  private TextField entreeAgeLimiteFilm;

  @FXML
  private TextField entreeAgeUtilisateur;

  @FXML
  private TextField entreeAnneeFilm;

  @FXML
  private TextField entreeAuteurEvaluation;

  @FXML
  private TextField entreeEvaluationMoyenne;

  @FXML
  private TextField entreeGenresFilm;

  @FXML
  private TextField entreeMotDePasseUtilisateur;

  @FXML
  private TextField entreeNationaliteArtiste;

  @FXML
  private TextField entreeNomArtiste;

  @FXML
  private TextField entreeNomPrenomRealisateurFilm;

  @FXML
  private TextField entreeNomUtilisateur;

  @FXML
  private TextField entreePrenomArtiste;

  @FXML
  private TextField entreePrenomUtilisateur;

  @FXML
  private TextField entreePseudoUtilisateur;

  @FXML
  private TextField entreeTitreFilm;

  @FXML
  private Label labelListeFilms;

  @FXML
  private Label labelListeArtistes;

  @FXML
  private ListView<String> listeArtistes;

  @FXML
  private ListView<String> listeEvaluations;

  @FXML
  private ListView<String> listeFilms;

  @FXML
  private ListView<String> listeFilmsEnLocation;

  @FXML
  private ListView<String> listeGenresFilm;

  @FXML
  private ChoiceBox<Integer> listeNoteEvaluation;

  @FXML
  private TextArea texteCommentaire;

  @FXML
  private StackPane paneAffiche;

  private Location location = new Location();

  /**
   * Retourne le film sélectionné dans la liste des films.
   *
   * @return le film sélectionné dans la liste des films.
   */
  private Movie getSelectedMovie() {
    // Retourne le film sélectionné dans la liste des films
    String selected = listeFilms.getSelectionModel().getSelectedItem();
    if (selected == null) {
      return null;
    }
    return location.getMovie(selected);
  }

  private Artist getSelectedActor() {
    // Retourne l'artiste sélectionné dans la liste des artistes
    String selected = listeArtistes.getSelectionModel().getSelectedItem();
    if (selected == null) {
      return null;
    }
    String[] parts = selected.split(" ");
    return location.getActor(parts[0], parts[1]);
  }

  private Artist getSelectedDirector() {
    // Retourne l'artiste sélectionné dans la liste des artistes
    String selected = listeArtistes.getSelectionModel().getSelectedItem();
    if (selected == null) {
      return null;
    }
    String[] parts = selected.split(" ");
    return location.getDirector(parts[0], parts[1]);
  }

  private Genre getSelectedGenre() {
    // Retourne le genre sélectionné dans la liste des genres
    String selected = listeGenresFilm.getSelectionModel().getSelectedItem();
    if (selected == null) {
      return null;
    }
    return Genre.valueOf(selected);
  }

  /**
   * Action du bouton "Film->Afficher les acteurs du film".
   *
   * @param event l'événement de clic sur le bouton.
   */
  @FXML
  void actionBoutonAfficherActeursFilmSelectionne(ActionEvent event) {
    // Supprime les éléments de la liste des artistes
    this.listeArtistes.getItems().clear();
    // Récupère le film sélectionné
    Movie movie = this.getSelectedMovie();
    if (movie == null) {
      return;
    }

    // Ajoute les acteurs du film sélectionné à la liste des artistes
    movie.getActors().forEach(actor -> this.listeArtistes.getItems().add(actor.toString()));

    // Affiche la liste des artistes
    this.labelListeArtistes.setText("Acteurs du film " + movie.getTitle());
  }

  /**
   * Action du bouton "Artiste->Afficher dans la liste->Les acteurs".
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherArtistesActeurs(ActionEvent event) {
    // Supprime les éléments de la liste des artistes
    this.listeArtistes.getItems().clear();
    // Récupère les acteurs
    location.allActors().forEach(actor -> this.listeArtistes.getItems().add(actor.toString()));

    // Affiche la liste des artistes
    this.labelListeArtistes.setText("Tous les acteurs");
  }

  /**
   * Action du bouton "Artiste->Afficher dans la liste->Les réalisateurs".
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherArtistesRealisateurs(ActionEvent event) {
    // Supprime les éléments de la liste des artistes
    this.listeArtistes.getItems().clear();
    // Récupère les réalisateurs
    location.allDirectors().forEach(director -> this.listeArtistes.getItems().add(director.toString()));

    // Affiche la liste des artistes
    this.labelListeArtistes.setText("Tous les réalisateurs");
  }

  @FXML
  void actionBoutonAfficherFilmLoue(ActionEvent event) {

  }

  /**
   * Action du bouton "Artiste->Afficher les films de l'artiste sélectionné en
   * tant que->Réalisateur".
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherFilmRealisateurSelectionne(ActionEvent event) {
    // Supprime les éléments de la liste des films
    this.listeFilms.getItems().clear();
    // Récupère le réalisateur sélectionné
    Artist director = this.getSelectedDirector();
    if (director == null) {
      return;
    }

    // Ajoute les films du réalisateur sélectionné à la liste des films
    director.getMovies().forEach(movie -> this.listeFilms.getItems().add(movie.getTitle()));
    
    // Affiche la liste des films
    this.labelListeFilms.setText("Films du réalisateur " + director.toString());
  }

  /**
   * Action du bouton "Artiste->Afficher les films de l'artiste sélectionné en
   * tant que->Acteur".
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherFilmsActeurSelectionne(ActionEvent event) {
    // Supprime les éléments de la liste des films
    this.listeFilms.getItems().clear();
    // Récupère l'acteur sélectionné
    Artist actor = this.getSelectedActor();
    if (actor == null) {
      return;
    }

    // Ajoute les films de l'acteur sélectionné à la liste des films
    actor.getMovies().forEach(movie -> this.listeFilms.getItems().add(movie.getTitle()));

    // Affiche la liste des films
    this.labelListeFilms.setText("Films de l'acteur " + actor.toString());
  }

  @FXML
  void actionBoutonAfficherFilmsGenre(ActionEvent event) {

  }

  @FXML
  void actionBoutonAfficherFilmsRealisateurSelectionne(ActionEvent event) {

  }

  @FXML
  void actionBoutonAfficherMonEvaluation(ActionEvent event) {

  }

  @FXML
  void actionBoutonAfficherTousArtistes(ActionEvent event) {

  }

  @FXML
  void actionBoutonAfficherTousFilms(ActionEvent event) {

  }

  @FXML
  void actionBoutonChercherActeur(ActionEvent event) {

  }

  @FXML
  void actionBoutonChercherFilm(ActionEvent event) {

  }

  @FXML
  void actionBoutonChercherRealisateur(ActionEvent event) {

  }

  @FXML
  void actionBoutonConnexion(ActionEvent event) {

  }

  @FXML
  void actionBoutonCreerMonEvaluation(ActionEvent event) {

  }

  @FXML
  void actionBoutonDeconnexion(ActionEvent event) {

  }

  @FXML
  void actionBoutonFinLocation(ActionEvent event) {

  }

  @FXML
  void actionBoutonInscription(ActionEvent event) {

  }

  @FXML
  void actionBoutonLouerFilmSelectionne(ActionEvent event) {

  }

  @FXML
  void actionBoutonModifierMonEvaluation(ActionEvent event) {

  }

  @FXML
  void actionSelectionArtiste(MouseEvent event) {

  }

  @FXML
  void actionSelectionEvaluation(MouseEvent event) {

  }

  @FXML
  void actionSelectionFilm(MouseEvent event) {

  }

  @FXML
  void initialize() {
    // Mettre ici le code d'initialisation du contenu de la fenêtre
  }
}
