package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.Player;
import fr.umontpellier.iut.bang.logic.cards.BlueCard;
import fr.umontpellier.iut.bang.logic.cards.Card;
import fr.umontpellier.iut.bang.logic.cards.Colt;
import fr.umontpellier.iut.bang.logic.cards.WeaponCard;
import fr.umontpellier.iut.bang.views.GameView;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InGameView extends GameView {
    private BangIHM main;

    private ArrayList<YourPlayerArea> areasPlayers;

    private ArrayList<YourHand> playersHands;
    

    @FXML
    private Button testCard;

    @FXML
    private Button retourMenuBtn;

    @FXML
    private Button passBtn;

    @FXML
    private Button parametresBtn;


    private ArrayList<VBox> playersBtn;



    private ChangeListener<? super Player> getWhenCurrentPlayerChanges = new ChangeListener<Player>() {
        @Override
        public void changed(ObservableValue<? extends Player> observableValue, Player player, Player t1) {
            for(YourHand h: playersHands){
                h.setVisible(false);
            }
            if (player!=null)
                findPlayerArea(player).deHightlightCurrentArea();
            if(t1!=null) {
                findPlayerArea(t1).highlightCurrentArea();
                findPlayerHand(t1).setVisible(true);

            }
        }
    };

    public InGameView(IGame game, BangIHM main) {
        super(game);
        this.main = main;

        areasPlayers = new ArrayList<>();
        playersHands = new ArrayList<>();

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

            YourPlayerArea yourPlayerArea = new YourPlayerArea(iplayer, this);
            getChildren().add(yourPlayerArea);

            deplacementVersCoord(yourPlayerArea, ((i/2)+1)*200-((1-i/2)*150), ((i)%2+1)*200-((1-(i)%2)*80));

            areasPlayers.add(yourPlayerArea);
            playersHands.add(new YourHand(yourPlayerArea));
            getChildren().add(playersHands.get(i));
            deplacementVersCoord(playersHands.get(i), 700, 400);
            playersHands.get(i).setVisible(false);
        }


        setCurrentPlayerChangesListener(getWhenCurrentPlayerChanges);


        game.run();


    }

    public void passOnClic(){
        getIGame().onPass();
    }

    public void deplacementVersCoord(Node vBox, int x, int y){
        TranslateTransition transition;

        Duration duration = Duration.millis(1500);
        transition = new TranslateTransition(duration, vBox);
        transition.setByX(x);
        transition.setByY(y);
        transition.play();

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

    @Override
    protected void setPassSelectedListener() {

    }

    @FXML
    public void retourMenu(){
        main.changeSceneToStartView();
    }


    private YourPlayerArea findPlayerArea(Player player){
        for (YourPlayerArea pa : areasPlayers){
            if(pa.getPlayer().equals(player)){
                return pa;
            }
        }
        return null;
    }

    private YourHand findPlayerHand(Player player){
        for(YourHand h : playersHands){
            if(h.getOwner().equals(player)){
                return h;
            }
        }
        return null;
    }

    private YourPlayerArea findAreaPlayer(Player player){
        for(YourPlayerArea ap : areasPlayers){
            if(ap.getPlayer().equals(player))
                return ap;
        }
        return null;
    }
}