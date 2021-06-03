package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.Player;
import fr.umontpellier.iut.bang.views.GameView;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InGameView extends GameView {
    private BangIHM main;

    private ArrayList<YourPlayerArea> areasPlayers;


    @FXML
    private Button testCard;

    @FXML
    private Button retourMenuBtn;

    @FXML
    private Button passBtn;

    @FXML
    private Button parametresBtn;


    private ArrayList<Button> playersBtn;


    public InGameView(IGame game, BangIHM main) {
        super(game);
        this.main = main;
        areasPlayers = new ArrayList<>();
        playersBtn = new ArrayList<>();

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
            Button btn = new Button("Player "+i);
            this.getChildren().add(btn);
            playersBtn.add(btn);

            IPlayer iplayer = new IPlayer(game.getPlayers().get(i));
            YourPlayerArea yourPlayerArea = new YourPlayerArea(iplayer, this, playersBtn.get(i));
            areasPlayers.add(yourPlayerArea);
            btn.setOnAction(actionEvent -> yourPlayerArea.highlightCurrentArea());
        }

        deplacementVersPioche(playersBtn);


    }



    public YourPlayerArea getPlayerArea(int i) {
        return areasPlayers.get(i);
    }

    @Override
    protected void bindNextActionMessage() {}

    public void hightlightPlayer(Player p){
        System.out.println("Joueur attaqué mis en valeur");
    }


    public void setCurrentPlayerChangesListener(ChangeListener<? super Player> whenCurrentPlayerChanges) {
        super.setCurrentPlayerChangesListener(whenCurrentPlayerChanges);
    }

    public void deplacementVersPioche(List<Button> cards){
        TranslateTransition transition;
        for(Button card : cards){
            Duration duration = Duration.millis(1500);
            transition = new TranslateTransition(duration, card);
            transition.setByX(150);
            transition.setByY(150);
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