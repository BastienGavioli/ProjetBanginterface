package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.Player;
import fr.umontpellier.iut.bang.logic.cards.Bang;
import fr.umontpellier.iut.bang.logic.cards.Card;
import fr.umontpellier.iut.bang.logic.cards.CardSuit;
import fr.umontpellier.iut.bang.logic.cards.CatBalou;
import fr.umontpellier.iut.bang.views.GameView;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InGameView extends GameView {
    private BangIHM main;

    private YourPlayerArea yourPlayerArea;

    @FXML
    private Button testCard;

    @FXML
    private Button retourMenuBtn;

    @FXML
    private Button passBtn;

    @FXML
    private Button parametresBtn;


    public InGameView(IGame game, BangIHM main) {
        super(game);
        this.main = main;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/src/main/resources/fxml/inGameView.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        //placementInitial();
        for(int i=0; i<game.getPlayers().size(); i++) {
            IPlayer iplayer = new IPlayer(game.getPlayers().get(i));
            yourPlayerArea = new YourPlayerArea(iplayer, this);
        }
    }


    @Override
    protected void bindNextActionMessage() {}


    public void setCurrentPlayerChangesListener(ChangeListener<? super Player> whenCurrentPlayerChanges) {
        super.setCurrentPlayerChangesListener(whenCurrentPlayerChanges);
    }

    public void deplacementVersPioche(List<Button> cards){
        TranslateTransition transition;
        for(Button card : cards){
            Duration duration = Duration.millis(1500);
            transition = new TranslateTransition(duration, card);
            transition.setByX(150);
            transition.setByY(-150);
            transition.play();
        }
    }

    @Override
    protected void setPassSelectedListener() {

    }

    @FXML
    public void retourMenu(){
        main.changeSceneToStartView();
    }

}
