package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.views.GameView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ReadRulesView extends Pane {
    private BangIHM main;

    @FXML
    private Button retourMenuBtn;


    public ReadRulesView(BangIHM main) {
        this.main = main;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/src/main/resources/fxml/readRules.fxml"));
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
    }

}