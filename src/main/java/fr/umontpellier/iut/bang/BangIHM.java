package fr.umontpellier.iut.bang;

import fr.umontpellier.iut.bang.logic.Game;
import fr.umontpellier.iut.bang.logic.GameState;
import fr.umontpellier.iut.bang.logic.Player;
import fr.umontpellier.iut.bang.logic.cards.Card;
import fr.umontpellier.iut.bang.views.GameView;
import fr.umontpellier.iut.bang.views.ResultsView;
import fr.umontpellier.iut.bang.views.StartView;
import fr.umontpellier.iut.bang.views.ourviews.BangIHMControl;
import fr.umontpellier.iut.bang.views.ourviews.InGameView;
import fr.umontpellier.iut.bang.views.ourviews.ReadRulesView;
import fr.umontpellier.iut.bang.views.ourviews.YourStartView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.WeakListChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BangIHM extends Application {

    private GameView firstView;
    private InGameView inGame; //Vue utilisée pendant le jeu
    private StartView startView;
    private ReadRulesView readRulesView;
    private ResultsView resultsView;
    private Stage primaryStage;
    private IGame game;
    private Scene scene2;
    private Scene sceneRules;
    private Scene scene;
    private ListChangeListener playerTarget;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Bang");
        startFirstView();
    }

    public void startFirstView() {
        List<String> playerNames = new ArrayList<>();
        playerNames.add("John");
        playerNames.add("Paul");
        playerNames.add("Ringo");
        playerNames.add("George");

        //Creation de la partie
        game = new IGame(new Game(Game.makePlayers(playerNames.toArray(new String[playerNames.size()]))));
        initGameView();
        initInGameView();
        initResultView();
        initRulesView();

        //Creation des scenes
        scene = new Scene(firstView);
        scene2 = new Scene(inGame);
        sceneRules = new Scene(readRulesView);

        //Caracteristiques générales de la fenetre
        primaryStage.setHeight(700);
        primaryStage.setWidth(1100);
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().set(false);
        primaryStage.setOnCloseRequest(event -> {
            this.onStopGame();
            event.consume();
        });
        primaryStage.show();
        playGame();
    }

    public void playGame(){

        // Ce listener écoute les changements de currentPlayer (Le joueur dont c'est le tour.)
        ChangeListener<? super Player> whenCurrentPlayerChanges = new ChangeListener<Player>() {
            @Override
            public void changed(ObservableValue<? extends Player> observableValue, Player player, Player t1) {
                System.out.println("Le currentPlayer a changé");
            }
        };

        //Ce listener écoute les changements de si on peut sélectionner la pioche ou non
        ChangeListener<? super Boolean> whenDrawPileCanBeSelectedChanges = new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                System.out.println("'La pioche peut être sélectionné' a changé");
            }
        };

        //Ce listener écoute les changements d'attaque
        ChangeListener<? super Card> whenCurrentAttackChanges = new ChangeListener<Card>() {
            @Override
            public void changed(ObservableValue<? extends Card> observableValue, Card aBoolean, Card t1) {
                System.out.println("L'attaque a changé");
            }
        };

        //Ce listener écoute les changements d'état
        ChangeListener<? super GameState> whenStateChanges = new ChangeListener<GameState>() {
            @Override
            public void changed(ObservableValue<? extends GameState> observableValue, GameState gameState, GameState t1) {
                System.out.println("L'état a changé");
            }
        };

        ListChangeListener<Card> whenDrawnCardschanges = new ListChangeListener<Card>() {
            @Override
            public void onChanged(Change<? extends Card> change) {
                System.out.println("Les cartes piochées ont changé");
            }
        };

        game.currentPlayerProperty().addListener(whenCurrentPlayerChanges);
        game.canDrawPileBeSelectedProperty().addListener(whenDrawPileCanBeSelectedChanges);
        game.currentAttackProperty().addListener(whenCurrentAttackChanges);
        game.currentStateProperty().addListener(whenStateChanges);
        game.drawnCardsProperty().addListener(whenDrawnCardschanges);
        game.run();

    }

    public void passTurn(){
        game.onPass();
    }

    /**
     * Permet de passer à la scene inGame
     */
    public void changeSceneToInGame(){
        primaryStage.setScene(scene2);
    }

    /**
     * Permet de passer à la scene readRules
     */
    public void changeScenereadRulesView(){
        primaryStage.setScene(sceneRules);
    }

    /**
     * Permet de passer à la scene inGame
     */
    public void changeSceneToStartView(){

    primaryStage.setScene(scene);
    }

    /**
     * Pour lancer la vue de la partie
     */
    static public void startInGame(){

        //scene = new Scene(firstView);
        //primaryStage.show();
    }

    /**
     * Pour instancier la vue de lecture des regles
     */
    private void initRulesView() {
        readRulesView = new ReadRulesView();
    }

    /**
     * Pour instancier la vue de renseignement des noms des joueurs
     */
    private void initStartView() {
        startView = new YourStartView();
    }

    /**
     * Pour instancier la vue d'arrivé dans le jeu
     */
    private void initGameView() {
        firstView = new BangIHMControl(game, this);
    }

    /**
     * Pour instancier la vue principale du jeu
     */
    private void initInGameView() {
        inGame = new InGameView(game, this);
    }
    /**
     * Pour instancier la vue de fin de partie
     */
    private void initResultView() {
        resultsView = null;
    }

    private final ListChangeListener<String> whenPlayersNamesListIsSet = change -> {
        if (!startView.getPlayersNamesList().isEmpty())
            startFirstView();
    };

    public IGame getIGame() {
        return game;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void initPlayersNames() {
        startView.show();
    }

    public void onStopGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Voulez vous vraiment arrêter de jouer ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }
}