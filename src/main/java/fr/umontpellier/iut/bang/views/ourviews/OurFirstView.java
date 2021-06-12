package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.views.GameView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;


public class OurFirstView extends GameView {
    private BangIHM main; //Permet d'acceder aux fonctions du main

    @FXML
    private Button btnLancer;

    @FXML
    private Button lancerSauvegarde;

    @FXML
    private Button lireRegles;

    @FXML
    private Button parametres;

    public OurFirstView(IGame game, BangIHM main) {
        super(game);
        this.main = main;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/src/main/resources/fxml/myStartView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    protected void bindNextActionMessage() {

    }

    @Override
    protected void setPassSelectedListener() {

    }

    @FXML
    public void lancerPartie() {
        main.changeSceneToInGame();
    }

    @FXML
    public void lireRegles() {
        main.changeScenereadRulesView();
        URL url = getClass().getClassLoader().getResource("sounds/Paper.mp3");
        Media media = new Media(url.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaPlayer.play();
    }

    @FXML
    public void parametres() {
        main.changeSceneParametersView("OurFirstView");
    }
}
