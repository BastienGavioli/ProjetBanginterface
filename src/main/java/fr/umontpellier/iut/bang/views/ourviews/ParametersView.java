package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;

public class ParametersView extends Pane {
    private BangIHM main;

    @FXML
    private Button retourAccueil;

    @FXML
    private Slider volumeSlider;

    @FXML
    private CheckBox volume;

    @FXML
    private CheckBox cursor;

    public ParametersView(BangIHM main) {
        this.main = main;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Parameters.fxml"));
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
        if(main.getProvenance()=="InGameView")
            main.changeSceneToInGame();
        else
            main.changeSceneToStartView();
    }

    @FXML
    public void changeCursor(){
        if(cursor.isSelected()){
            Image image = new Image("images/bullet.png");
            getScene().setCursor(new ImageCursor(image));
        }
        else
            getScene().setCursor(new ImageCursor());
    }

    @FXML
    public void ajusterVolume(){
        if(volumeSlider == null){
            main.getIGame().setVolume(1);
        }
        else{
            main.getIGame().setVolume(volumeSlider.getValue()/100);
        }
    }

    @FXML
    public void muteVolume(){
        main.getIGame().setVolume(0);
    }

}
