package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.views.GameView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class BangIHMControl extends GameView {
    BangIHM main; //Permet d'acceder aux fonctions du main

    @FXML
    private Button lancerUnePartie;

    @FXML
    private Button lancerSauvegarde;

    @FXML
    private Button lireRegles;

    @FXML
    private Button parametres;

    public BangIHMControl(IGame game, BangIHM main) {
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
    public void lancerPartie(){
        main.changeSceneToInGame();
    }
}
