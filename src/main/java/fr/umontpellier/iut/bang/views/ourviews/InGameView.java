package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.views.GameView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class InGameView extends GameView {

    public InGameView(IGame game) {
        super(game);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/src/main/resources/fxml/inGameView.fxml"));

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

}
