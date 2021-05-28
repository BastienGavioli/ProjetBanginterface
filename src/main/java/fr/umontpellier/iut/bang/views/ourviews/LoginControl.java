package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.views.StartView;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginControl extends StartView {

    @FXML
    private Button buttonOk, buttonCancel;
    @FXML
    private PasswordField pwd;
    @FXML
    private TextField identifiant;

    public LoginControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/src/main/resources/fxml/preparationPartieView.fxml"));
        //fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void setPlayersListSetListener(ListChangeListener<String> whenPlayersNamesListIsSet) {

    }

    @Override
    protected void setNbPlayersChangeListener(ChangeListener<Integer> whenNbPlayersChanged) {

    }

    @Override
    protected int getNumberOfPlayers() {
        return 0;
    }

    @Override
    protected String getPlayerNameByNumber(int playerNumber) {
        return null;
    }

    @FXML
    private void okClicked() {
        System.out.println(identifiant.getText());
        for(int i=0; i<pwd.getLength(); i++)
            System.out.print("*");
        System.out.println();
    }

    @FXML
    private void cancelClicked() {
        System.out.println("");
        System.out.println("");
        pwd.clear();
        identifiant.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}