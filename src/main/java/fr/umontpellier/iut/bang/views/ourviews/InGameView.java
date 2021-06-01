package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.logic.cards.CardSuit;
import fr.umontpellier.iut.bang.logic.cards.CatBalou;
import fr.umontpellier.iut.bang.views.GameView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class InGameView extends GameView {

    @FXML
    private Button testCard;

    @FXML
    private Button retourMenuBtn;

    @FXML
    private Button passBtn;

    @FXML
    private Button parametresBtn;

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

        //placementInitial();
    }
/*
    public void placementInitial(){
        ICard carteTest = new ICard(new CatBalou(1, CardSuit.CLUB), 50, 50);
        testCard.setGraphic(new ImageView("src/main/resources/"+carteTest.getImageName()));

    }*/

    @Override
    protected void bindNextActionMessage() {

    }

    @Override
    protected void setPassSelectedListener() {

    }

}
