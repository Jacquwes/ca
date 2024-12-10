package ui;

import location.Artist;
import location.Genre;
import location.Location;
import location.Movie;
import location.NotLoggedInException;
import location.PersonalInformation;
import location.Review;

import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

  /**
   * Retourne le film sélectionné dans la liste des films loués.
   *
   * @return le film sélectionné dans la liste des films loués.
   */
  private Movie getSelectedRentedMovie() {
    // Retourne le film sélectionné dans la liste des films loués
    String selected = listeFilmsEnLocation.getSelectionModel().getSelectedItem();
    if (selected == null) {
      return null;
    }
    return location.getMovie(selected);
  }

  /**
   * Retourne l'artiste sélectionné dans la liste des artistes.
   *
   * @return l'artiste sélectionné dans la liste des artistes.
   */
  private Artist getSelectedActor() {
    // Retourne l'artiste sélectionné dans la liste des artistes
    String selected = listeArtistes.getSelectionModel().getSelectedItem();
    if (selected == null) {
      return null;
    }
    String[] parts = selected.split(" ");
    return location.getActor(parts[0], parts[1]);
  }

  /**
   * Retourne l'artiste sélectionné dans la liste des artistes.
   *
   * @return l'artiste sélectionné dans la liste des artistes.
   */
  private Artist getSelectedDirector() {
    // Retourne l'artiste sélectionné dans la liste des artistes
    String selected = listeArtistes.getSelectionModel().getSelectedItem();
    if (selected == null) {
      return null;
    }
    String[] parts = selected.split(" ");
    return location.getDirector(parts[0], parts[1]);
  }

  /**
   * Retourne le genre sélectionné dans la liste des genres.
   *
   * @return le genre sélectionné dans la liste des genres.
   */
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

  /**
   * Action du bouton "Utilisateur->Afficher les détails du film sélectionné".
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherFilmLoue(ActionEvent event) {
    // Get selected movie
    Movie movie = this.getSelectedRentedMovie();
    if (movie == null) {
      return;
    }

    // Update fields
    this.entreeTitreFilm.setText(movie.getTitle());
    this.entreeAnneeFilm.setText(String.valueOf(movie.getYear()));
    this.entreeAgeLimiteFilm.setText(String.valueOf(movie.getMinimumAge()));
    this.entreeNomPrenomRealisateurFilm.setText(movie.getDirector().toString());
    this.entreeGenresFilm
        .setText(String.join(", ", movie.getGenres().stream().map(Genre::toString).toArray(String[]::new)));
    this.checkFilmLouable.setSelected(movie.isAvailable());
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

  /**
   * Action du bouton "Film->Afficher les genres des films".
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherFilmsGenre(ActionEvent event) {
    // Supprime les éléments de la liste des films
    this.listeFilms.getItems().clear();
    // Récupère le genre sélectionné
    Genre genre = getSelectedGenre();

    // Ajoute les films du genre sélectionné à la liste des films
    location.moviesByGenre(genre).forEach(movie -> this.listeFilms.getItems().add(movie.getTitle()));

    // Affiche la liste des films
    this.labelListeFilms.setText("Films du genre " + genre);
  }

  /**
   * Action du bouton "Artiste->Afficher les films du réalisateur".
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherFilmsRealisateurSelectionne(ActionEvent event) {
    // Supprime les éléments de la liste des films
    this.listeFilms.getItems().clear();
    // Récupère le film sélectionné
    Movie movie = this.getSelectedMovie();
    if (movie == null) {
      return;
    }
    Artist director = movie.getDirector();
    if (director == null) {
      return;
    }

    // Ajoute les films du réalisateur sélectionné à la liste des films
    director.getMovies().forEach(film -> this.listeFilms.getItems().add(film.getTitle()));

    // Affiche la liste des films
    this.labelListeFilms.setText("Films du réalisateur " + director.toString());
  }

  /**
   * Action du bouton "Evaluation->Afficher mon évaluation".
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherMonEvaluation(ActionEvent event) {
    // Clear fields
    this.listeNoteEvaluation.setValue(null);
    this.texteCommentaire.clear();
    this.entreeAuteurEvaluation.clear();

    // Get selected movie
    Movie movie = this.getSelectedMovie();
    if (movie == null) {
      return;
    }

    // Get and display review of the user
    movie.getReviews().stream()
        .filter(review -> review.getUser().toString().equals(this.entreeAuteurEvaluation.getText()))
        .findFirst()
        .ifPresent(review -> {
          this.listeNoteEvaluation.setValue((int) review.getRating());
          this.texteCommentaire.setText(review.getComment());
          this.entreeAuteurEvaluation.setText(review.getUser().toString());
        });
  }

  /**
   * Action du bouton "Artiste->Afficher dans la liste->Tous les artistes".
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherTousArtistes(ActionEvent event) {
    // Supprime les éléments de la liste des artistes
    this.listeArtistes.getItems().clear();
    // Récupère tous les artistes
    Set<Artist> artists = location.allActors();
    artists.addAll(location.allDirectors());

    // Ajoute les artistes à la liste des artistes
    artists.forEach(artist -> this.listeArtistes.getItems().add(artist.toString()));

    // Affiche la liste des artistes
    this.labelListeArtistes.setText("Tous les artistes");
  }

  /**
   * Action du bouton "Film->Afficher tous les films".
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherTousFilms(ActionEvent event) {
    // Supprime les éléments de la liste des films
    this.listeFilms.getItems().clear();
    // Récupère tous les films
    location.allMovies().forEach(movie -> this.listeFilms.getItems().add(movie.getTitle()));

    // Affiche la liste des films
    this.labelListeFilms.setText("Tous les films");
  }

  /**
   * Action du bouton "Artiste->Chercher->Un acteur".
   *
   * @param event
   */
  @FXML
  void actionBoutonChercherActeur(ActionEvent event) {
    // Supprime les éléments de la liste des artistes
    this.listeArtistes.getItems().clear();
    // Récupère l'acteur
    Artist actor = location.getActor(this.entreeNomArtiste.getText(), this.entreePrenomArtiste.getText());
    if (actor == null) {
      return;
    }

    // Ajoute l'acteur à la liste des artistes
    this.listeArtistes.getItems().add(actor.toString());

    // Affiche la liste des artistes
    this.labelListeArtistes.setText("Acteur " + actor.toString());

    // Met à jour les champs de l'artiste
    this.entreeNationaliteArtiste.setText(actor.getNationality());
  }

  /**
   * Action du bouton "Film->Chercher un film".
   *
   * @param event
   */
  @FXML
  void actionBoutonChercherFilm(ActionEvent event) {
    // Supprime les éléments de la liste des films
    this.listeFilms.getItems().clear();
    // Récupère le film
    Movie movie = location.getMovie(this.entreeTitreFilm.getText());
    if (movie == null) {
      return;
    }

    // Ajoute le film à la liste des films
    this.listeFilms.getItems().add(movie.getTitle());

    // Affiche la liste des films
    this.labelListeFilms.setText("Film " + movie.getTitle());

    // Met à jour les champs du film
    this.entreeAnneeFilm.setText(String.valueOf(movie.getYear()));
    this.entreeAgeLimiteFilm.setText(String.valueOf(movie.getMinimumAge()));
    this.entreeNomPrenomRealisateurFilm.setText(movie.getDirector().toString());
    this.entreeGenresFilm
        .setText(String.join(", ", movie.getGenres().stream().map(Genre::toString).toArray(String[]::new)));
    this.checkFilmLouable.setSelected(movie.isAvailable());
  }

  /**
   * Action du bouton "Artiste->Chercher->Un réalisateur".
   *
   * @param event
   */
  @FXML
  void actionBoutonChercherRealisateur(ActionEvent event) {
    // Supprime les éléments de la liste des artistes
    this.listeArtistes.getItems().clear();
    // Récupère l'acteur
    Artist director = location.getDirector(this.entreeNomArtiste.getText(), this.entreePrenomArtiste.getText());
    if (director == null) {
      return;
    }

    // Ajoute l'acteur à la liste des artistes
    this.listeArtistes.getItems().add(director.toString());

    // Affiche la liste des artistes
    this.labelListeArtistes.setText("Réalisateur " + director.toString());

    // Met à jour les champs de l'artiste
    this.entreeNationaliteArtiste.setText(director.getNationality());
  }

  /**
   * Action du bouton "Utilisateur->Connexion".
   *
   * @param event
   */
  @FXML
  void actionBoutonConnexion(ActionEvent event) {
    String username = this.entreePseudoUtilisateur.getText();
    String password = this.entreeMotDePasseUtilisateur.getText();

    if (!this.location.login(username, password)) {
      // Affiche un message d'erreur
      new Alert(Alert.AlertType.ERROR, "Nom d'utilisateur ou mot de passe incorrect.").showAndWait();
      return;
    }

    // Popule la liste des films loués
    this.listeFilmsEnLocation.getItems().clear();
    try {
      this.location.rentedMovies().forEach(movie -> this.listeFilmsEnLocation.getItems().add(movie.getTitle()));
    } catch (Exception e) {
      // Affiche un message d'erreur
      new Alert(Alert.AlertType.ERROR, "Erreur lors de la récupération des films loués.").showAndWait();
    }

    // Met à jour les champs de l'utilisateur
    this.entreeNomUtilisateur.setText(this.location.getCurrentUser().getPersonalInformation().getLastName());
    this.entreePrenomUtilisateur.setText(this.location.getCurrentUser().getPersonalInformation().getFirstName());
    this.entreeAdresseUtilisateur.setText(this.location.getCurrentUser().getPersonalInformation().getAddress());
    this.entreeAgeUtilisateur.setText(String.valueOf(this.location.getCurrentUser().getPersonalInformation().getAge()));
  }

  /**
   * Action du bouton "Evaluation->Créer mon évaluation".
   *
   * @param event
   */
  @FXML
  void actionBoutonCreerMonEvaluation(ActionEvent event) {
    // Get selected movie
    Movie movie = this.getSelectedMovie();
    if (movie == null) {
      return;
    }

    // Create review
    Review review = new Review();
    review.setRating(this.listeNoteEvaluation.getValue());
    review.setComment(this.texteCommentaire.getText());
    review.setUser(this.location.getCurrentUser());

    // Add review to movie
    movie.addReview(review);

    // Clear fields
    this.listeNoteEvaluation.setValue(null);
    this.texteCommentaire.clear();
    this.entreeAuteurEvaluation.clear();

    // Update average rating
    this.entreeEvaluationMoyenne
        .setText(String.valueOf(movie.getReviews().stream().mapToDouble(Review::getRating).average().orElse(0)));

    // Update list of reviews
    this.listeEvaluations.getItems().clear();
    movie.getReviews().forEach(r -> this.listeEvaluations.getItems().add(r.toString()));
  }

  /**
   * Action du bouton "Utilisateur->Déconnexion".
   *
   * @param event
   */
  @FXML
  void actionBoutonDeconnexion(ActionEvent event) {
    try {
      this.location.logout();
    } catch (NotLoggedInException e) {
      // Affiche un message d'erreur
      new Alert(Alert.AlertType.ERROR, "Vous n'êtes pas connecté.").showAndWait();
      return;
    }

    // Clear fields
    this.entreeNomUtilisateur.clear();
    this.entreePrenomUtilisateur.clear();
    this.entreeAdresseUtilisateur.clear();
    this.entreeAgeUtilisateur.clear();
    this.entreePseudoUtilisateur.clear();
    this.entreeMotDePasseUtilisateur.clear();
  }

  /**
   * Action du bouton "Utilisateur->Terminer la location du film sélectionné".
   *
   * @param event
   */
  @FXML
  void actionBoutonFinLocation(ActionEvent event) {
    // Get selected movie
    Movie movie = this.getSelectedRentedMovie();
    if (movie == null) {
      return;
    }

    // Close rental
    try {
      this.location.endMovieRental(movie);
    } catch (Exception e) {
      // Affiche un message d'erreur
      new Alert(Alert.AlertType.ERROR, "Erreur lors de la fermeture de la location.").showAndWait();
      return;
    }

    // Update list of rented movies
    this.listeFilmsEnLocation.getItems().clear();
    try {
      this.location.rentedMovies().forEach(m -> this.listeFilmsEnLocation.getItems().add(m.getTitle()));
    } catch (Exception e) {
      // Affiche un message d'erreur
      new Alert(Alert.AlertType.ERROR, "Erreur lors de la récupération des films loués.").showAndWait();
    }
  }

  /**
   * Action du bouton "Utilisateur->Inscription".
   *
   * @param event
   */
  @FXML
  void actionBoutonInscription(ActionEvent event) {
    String username = this.entreePseudoUtilisateur.getText();
    String password = this.entreeMotDePasseUtilisateur.getText();
    String lastName = this.entreeNomUtilisateur.getText();
    String firstName = this.entreePrenomUtilisateur.getText();
    String address = this.entreeAdresseUtilisateur.getText();
    int age = Integer.parseInt(this.entreeAgeUtilisateur.getText());

    int result = this.location.register(username, password, new PersonalInformation(lastName, firstName, address, age));

    if (result == 0) {
      // Affiche un message de succès
      new Alert(Alert.AlertType.INFORMATION, "Utilisateur créé avec succès. Vous pouvez maintenant vous connecter.")
          .showAndWait();
    } else if (result == 1) {
      // Affiche un message d'erreur
      new Alert(Alert.AlertType.ERROR, "Nom d'utilisateur déjà utilisé.").showAndWait();
    } else if (result == 2) {
      // Affiche un message d'erreur
      new Alert(Alert.AlertType.ERROR, "Veuillez remplir tous les champs.").showAndWait();
    } else if (result == 3) {
      new Alert(Alert.AlertType.ERROR, "Veuillez remplir les champs correctement.").showAndWait();
    }
  }

  /**
   * Action du bouton "Film->Louer le film sélectionné".
   *
   * @param event
   */
  @FXML
  void actionBoutonLouerFilmSelectionne(ActionEvent event) {
    // Get selected movie
    Movie movie = this.getSelectedMovie();
    if (movie == null) {
      return;
    }

    // Rent movie
    try {
      this.location.rentMovie(movie);
    } catch (Exception e) {
      // Affiche un message d'erreur
      new Alert(Alert.AlertType.ERROR, "Erreur lors de la location du film.").showAndWait();
      return;
    }

    // Update list of rented movies
    this.listeFilmsEnLocation.getItems().clear();
    try {
      this.location.rentedMovies().forEach(m -> this.listeFilmsEnLocation.getItems().add(m.getTitle()));
    } catch (Exception e) {
      // Affiche un message d'erreur
      new Alert(Alert.AlertType.ERROR, "Erreur lors de la récupération des films loués.").showAndWait();
    }
  }

  /**
   * Action du bouton "Modifier mon évaluation".
   *
   * @param event
   */
  @FXML
  void actionBoutonModifierMonEvaluation(ActionEvent event) {
    // Get selected movie
    Movie movie = this.getSelectedMovie();
    if (movie == null) {
      return;
    }

    // Get and update review of the user
    movie.getReviews().stream()
        .filter(review -> review.getUser().toString().equals(this.entreeAuteurEvaluation.getText()))
        .findFirst()
        .ifPresent(review -> {
          review.setRating(this.listeNoteEvaluation.getValue());
          review.setComment(this.texteCommentaire.getText());
        });

    // Clear fields
    this.listeNoteEvaluation.setValue(null);
    this.texteCommentaire.clear();
    this.entreeAuteurEvaluation.clear();

    // Update average rating
    this.entreeEvaluationMoyenne
        .setText(String.valueOf(movie.getReviews().stream().mapToDouble(Review::getRating).average().orElse(0)));

    // Update list of reviews
    this.listeEvaluations.getItems().clear();
    movie.getReviews().forEach(r -> this.listeEvaluations.getItems().add(r.toString()));
  }

  /**
   * Action de la sélection d'un artiste.
   *
   * @param event
   */
  @FXML
  void actionSelectionArtiste(MouseEvent event) {
    // Supprime les éléments de la liste des films
    this.listeFilms.getItems().clear();
    // Récupère l'artiste sélectionné
    Artist artist = this.getSelectedActor();
    if (artist == null) {
      return;
    }

    // Ajoute les films de l'artiste sélectionné à la liste des films
    artist.getMovies().forEach(movie -> this.listeFilms.getItems().add(movie.getTitle()));

    // Affiche la liste des films
    this.labelListeFilms.setText("Films de l'artiste " + artist.toString());
  }

  /**
   * Action de la sélection d'une évaluation.
   *
   * @param event
   */
  @FXML
  void actionSelectionEvaluation(MouseEvent event) {
    // Récupère l'évaluation sélectionnée
    String selected = this.listeEvaluations.getSelectionModel().getSelectedItem();
    if (selected == null) {
      return;
    }
    String[] parts = selected.split(" ");
    this.entreeAuteurEvaluation.setText(parts[0] + " " + parts[1]);

    // Get selected movie
    Movie movie = this.getSelectedMovie();
    if (movie == null) {
      return;
    }

    // Get and display review of the user
    movie.getReviews().stream()
        .filter(review -> review.getUser().toString().equals(this.entreeAuteurEvaluation.getText()))
        .findFirst()
        .ifPresent(review -> {
          this.listeNoteEvaluation.setValue((int) review.getRating());
          this.texteCommentaire.setText(review.getComment());
        });
  }

  /**
   * Action de la sélection d'un film.
   *
   * @param event
   */
  @FXML
  void actionSelectionFilm(MouseEvent event) {
    // Supprime les éléments de la liste des évaluations
    this.listeEvaluations.getItems().clear();
    // Récupère le film sélectionné
    Movie movie = this.getSelectedMovie();
    if (movie == null) {
      return;
    }

    // Ajoute les évaluations du film sélectionné à la liste des évaluations
    movie.getReviews().forEach(review -> this.listeEvaluations.getItems().add(review.toString()));
  }

  @FXML
  void initialize() {
    // Mettre ici le code d'initialisation du contenu de la fenêtre
  }
}
