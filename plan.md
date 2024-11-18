**Plan de test pour ArtistTest.java :**

| Méthode de test | Actions | Attendu |
|-----------------|----------|----------|
| testConstructor | Instancier un Artist avec un nom "Doe" et prénom "John" | Le nom est "Doe", le prénom est "John", et l'artiste est présent dans la collection des artistes |
| testDefaultConstructor | Instancier un Artist sans paramètres | Le nom et le prénom sont null, et l'artiste est présent dans la collection des artistes |
| testSetters | Modifier le nom en "Brown" et le prénom en "Bob" | Le nom est "Brown" et le prénom est "Bob" |
| testMovies | Ajouter un film à l'artiste | La collection de films contient le nouveau film et sa taille est 1 |
| testGetArtists | Créer deux artistes | La collection contient les deux artistes et sa taille est 2 |
| testGetArtistsWithPredicate | Filtrer les artistes avec le nom "Doe" | La collection filtrée contient uniquement l'artiste "Doe" |

**Plan de test pour MovieTest.java :**

| Méthode de test | Actions | Attendu |
|-----------------|----------|----------|
| testConstructor | Instancier un Movie vide | La collection globale de films contient 1 élément |
| testReset | Réinitialiser la collection de films | La collection est vide |
| testParameterizedConstructor | Créer un film avec titre, année, réalisateur et acteurs | Les attributs correspondent aux valeurs fournies |
| testTitle | Définir le titre d'un film | Le titre est correctement modifié |
| testYear | Définir l'année d'un film | L'année est correctement modifiée |
| testDirector | Définir le réalisateur d'un film | Le réalisateur est correctement associé |
| testAvailability | Modifier la disponibilité d'un film | La disponibilité est mise à jour |
| testActors | Définir la liste des acteurs | La liste des acteurs est correctement associée |
| testMinimumAge | Définir l'âge minimum | L'âge minimum est correctement modifié |
| testGenres | Définir les genres du film | Les genres sont correctement associés |
| testGetMoviesPredicate | Filtrer les films par critère | La collection filtrée contient uniquement les films correspondants |

**Plan de test pour ReservationTest.java :**

| Méthode de test | Actions | Attendu |
|-----------------|----------|----------|
| testReservationConstructor | Créer une réservation avec utilisateur, film, date et durée | Tous les attributs sont correctement initialisés |
| testGetUser | Récupérer l'utilisateur d'une réservation | L'utilisateur correspond à celui défini |
| testSetUser | Modifier l'utilisateur d'une réservation | Le nouvel utilisateur est correctement associé |
| testGetMovie | Récupérer le film d'une réservation | Le film correspond à celui défini |
| testSetMovie | Modifier le film d'une réservation | Le nouveau film est correctement associé |
| testGetDate | Récupérer la date de réservation | La date correspond à celle définie |
| testSetDate | Modifier la date de réservation | La nouvelle date est correctement associée |
| testGetDuration | Récupérer la durée de réservation | La durée correspond à celle définie |
| testSetDuration | Modifier la durée de réservation | La nouvelle durée est correctement associée |

**Plan de test pour ReviewTest.java :**

| Méthode de test | Actions | Attendu |
|-----------------|----------|----------|
| testEquals | Comparer deux avis avec mêmes utilisateur et film | Les avis sont considérés comme égaux |
| testComment | Définir un commentaire d'avis | Le commentaire est correctement enregistré |
| testMovie | Associer un film à l'avis | Le film est correctement associé |
| testRating | Définir une note | La note est correctement enregistrée |
| testUser | Associer un utilisateur à l'avis | L'utilisateur est correctement associé |

**Plan de test pour UserTest.java :**

| Méthode de test | Actions | Attendu |
|-----------------|----------|----------|
| testRegisterSuccess | Enregistrer un nouvel utilisateur avec des informations valides | L'utilisateur est créé avec le bon login et les bonnes informations personnelles |
| testRegisterUsernameAlreadyExists | Tenter d'enregistrer un utilisateur avec un login déjà existant | L'enregistrement échoue et renvoie null |
| testRegisterEmptyUsername | Tenter d'enregistrer un utilisateur avec un login vide | L'enregistrement échoue et renvoie null |
| testRegisterEmptyPassword | Tenter d'enregistrer un utilisateur avec un mot de passe vide | L'enregistrement échoue et renvoie null |
| testRegisterNullUsername | Tenter d'enregistrer un utilisateur avec un login null | L'enregistrement échoue et renvoie null |
| testRegisterNullPassword | Tenter d'enregistrer un utilisateur avec un mot de passe null | L'enregistrement échoue et renvoie null |
| testRegisterNullPersonalInformation | Tenter d'enregistrer un utilisateur avec des informations personnelles null | L'enregistrement échoue et renvoie null |
| testGetLogin | Récupérer le login d'un utilisateur | Le login correspond à celui défini |
| testSetLogin | Modifier le login d'un utilisateur | Le nouveau login est correctement enregistré |
| testGetPassword | Récupérer le mot de passe d'un utilisateur | Le mot de passe correspond à celui défini |
| testSetPassword | Modifier le mot de passe d'un utilisateur | Le nouveau mot de passe est correctement enregistré |
| testAddReservationSuccess | Ajouter une réservation valide | La réservation est ajoutée à la liste des réservations |
| testAddReservationNull | Tenter d'ajouter une réservation null | Une exception RentingException est levée |
| testAddReservationAlreadyExists | Tenter d'ajouter une réservation déjà existante | Une exception RentingException est levée |
| testAddReservationLimitExceeded | Tenter d'ajouter une réservation au-delà de la limite | Une exception RentingException est levée |
| testCancelReservationSuccess | Annuler une réservation existante | La réservation est retirée de la liste des réservations |