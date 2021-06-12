package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.logic.Player;
import fr.umontpellier.iut.bang.views.GameView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class OurResultsView extends GameView {

    private BangIHM main;

    @FXML
    private Label winners;

    private VBox gagnants;

    public OurResultsView(IGame game, BangIHM main) {
        super(game);
        this.main = main;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/src/main/resources/fxml/resultsView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        gagnants = new VBox();

        for (Player p : game.winnersProperty()) {
            winners = new Label(p.getName());
            gagnants.getChildren().add(winners);
        }

        getChildren().add(gagnants);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    protected void playAgain() {

    }

    @FXML
    protected void stop() {
        main.onStopGame();
    }

    @Override
    protected void bindNextActionMessage() {

    }

    @Override
    protected void setPassSelectedListener() {

    }
}
