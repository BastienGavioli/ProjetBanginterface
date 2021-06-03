package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;

public class ReadRulesView extends Pane {
    private BangIHM main;

    @FXML
    private Button retourMenuBtn;


    public ReadRulesView(BangIHM main) {
        this.main = main;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/readRules.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void retourMenu(){
        main.changeSceneToStartView();
        URL url = getClass().getClassLoader().getResource("sounds/Paper.mp3");
        Media media = new Media(url.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaPlayer.play();
    }

}