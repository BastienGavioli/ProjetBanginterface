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


    @FXML
    private Button testCard;

    @FXML
    private Button retourMenuBtn;

    @FXML
    private Button passBtn;

    @FXML
    private Button parametresBtn;


    private ArrayList<VBox> playersBtn;


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
            VBox rootPlayer = new VBox();
            rootPlayer.setId("rootPlayer");
            Label name = new Label("abc");
            rootPlayer.setAlignment(Pos.CENTER);
            ImageView img = new ImageView("/src/main/resources/images/characters/bartcassidy.png");
            img.setFitHeight(220);
            img.setFitWidth(150);
            rootPlayer.getChildren().add(name);
            rootPlayer.getChildren().add(img);
            this.getChildren().add(rootPlayer);
            playersBtn.add(rootPlayer);

            IPlayer iplayer = new IPlayer(game.getPlayers().get(i));
            YourPlayerArea yourPlayerArea = new YourPlayerArea(iplayer, this, playersBtn.get(i));
            areasPlayers.add(yourPlayerArea);

            deplacementVersCoord(rootPlayer, ((i/2)+1)*200, ((i)%2+1)*200-(((i)%2)*50));
        }




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

}