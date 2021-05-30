package fr.umontpellier.iut.bang;

import fr.umontpellier.iut.bang.views.GameView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

public class BangIHMControl extends GameView {

    @FXML
    private Button lancerUnePartie;

    @FXML
    private Button lancerSauvegarde;

    @FXML
    private Button lireRegles;

    @FXML
    private Button parametres;

    @FXML
    private Button quitter;

    public BangIHMControl(IGame game) {
        super(game);
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
    public void quitGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Nigerundayo, Smokey!");
        alert.setContentText("Do you really want to stop playing ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }
}
