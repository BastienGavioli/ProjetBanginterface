package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.logic.Player;
import fr.umontpellier.iut.bang.views.GameView;
import fr.umontpellier.iut.bang.views.ResultsView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Optional;

public class EndGameView extends ResultsView {

    private BangIHM main;

    @FXML
    private Label winners;

    public EndGameView(BangIHM main, IGame game) {
        super(main);
        this.main = main;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/src/main/resources/fxml/endGameView.fxml"));
        fxmlLoader.setController(this);

        for(Player p : game.winnersProperty())
            winners = new Label(p.getName());

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    @Override
    protected void playAgain() {

    }

    @FXML
    @Override
    public void stop() {
        main.onStopGame();
    }
}
