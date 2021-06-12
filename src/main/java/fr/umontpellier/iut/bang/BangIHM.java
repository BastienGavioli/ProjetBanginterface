package fr.umontpellier.iut.bang;

import fr.umontpellier.iut.bang.logic.Game;
import fr.umontpellier.iut.bang.views.GameView;
import fr.umontpellier.iut.bang.views.ResultsView;
import fr.umontpellier.iut.bang.views.StartView;
import fr.umontpellier.iut.bang.views.ourviews.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BangIHM extends Application {

    private Stage primaryStage;
    private IGame game;

    /**
     * Attributs View
     */
    private GameView firstView;
    private InGameView inGame; //Vue utilisée pendant le jeu
    private StartView startView;
    private ReadRulesView readRulesView;
    private ParametersView parametersView;
    private ResultsView resultsView;

    /**
     * Attributs Scene
     */
    private Scene scene;
    private Scene scene2;
    private Scene sceneRules;
    private Scene parameters;



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
        initParametersView();

        //Creation des scenes
        scene = new Scene(firstView);
        scene2 = new Scene(inGame);
        sceneRules = new Scene(readRulesView);
        parameters = new Scene(parametersView);

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
//passer a la scene parameters
    public void changeSceneParametersView(){
        primaryStage.setScene(parameters);
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
        readRulesView = new ReadRulesView(this);
    }

 // pour instancier la vue parametres
    private void initParametersView() {
        parametersView = new ParametersView(this);
    }

    /**
     * Pour instancier la vue de renseignement des noms des joueurs
     */
    private void initStartView() {
        startView = new OurStartView();
    }

    /**
     * Pour instancier la vue d'arrivé dans le jeu
     */
    private void initGameView() {
        firstView = new OurFirstView(game, this);
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
        resultsView = new EndGameView(this, game);
    }

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