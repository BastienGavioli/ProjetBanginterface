package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.BangIHM;
import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.GameState;
import fr.umontpellier.iut.bang.logic.Player;
import fr.umontpellier.iut.bang.logic.cards.Card;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.GameView;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class InGameView extends GameView {
    private BangIHM main;

    private ArrayList<OurPlayerArea> areasPlayers;

    private ArrayList<HandView> playersHands;

    private ArrayList<VBox> playersBtn;


    /**
     * Listeners
     */
    private ChangeListener<? super Player> whenCurrentPlayerChanges = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends Player> observableValue, Player oldPlayer, Player newPlayer) {
            for (HandView h : playersHands) {
                h.setVisible(false);
            }
            if (oldPlayer != null)
                findPlayerArea(oldPlayer).deHightlightCurrentArea();
            if (newPlayer != null) {
                findPlayerArea(newPlayer).highlightCurrentArea();
                findPlayerHand(newPlayer).setVisible(true);
                deplacementVersCoord(findPlayerHand(newPlayer), 700, 325);
            }
        }
    };

    private ChangeListener<? super GameState> whenStateChanges = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends GameState> observableValue, GameState oldState, GameState newState) {
            instruction.setText(newState.toString());
        }
    };

    //Listener pour les cartes piochés à résoudre
    private ChangeListener<? super Card> whenDrawnCardChanges = (ChangeListener<Card>) (observableValue, oldCard, newCard) -> {

    };

    @FXML
    private Button testCard;

    @FXML
    private Button retourMenuBtn;

    @FXML
    private Button passBtn;

    @FXML
    private Button parametresBtn;

    @FXML
    private Label instruction;

    public InGameView(IGame game, BangIHM main) {
        super(game);
        this.main = main;
        setDrawnCardListener();
        setTargetListener();
        setWinnerListener();

        areasPlayers = new ArrayList<>();
        playersHands = new ArrayList<>();
        instruction = new Label("");


        getIGame().currentStateProperty().addListener(whenStateChanges);

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

            OurPlayerArea yourPlayerArea = new OurPlayerArea(iplayer, this);
            getChildren().add(yourPlayerArea);

            //Sert à mettre les joueurs au bon endroit à 4 joueurs
            int[] conv = {0, 2, 3, 1};
            deplacementVersCoord(yourPlayerArea, ((conv[i]/2)+1)*200-((1-conv[i]/2)*150),
                    ((conv[i])%2+1)*225-((1-(conv[i])%2)*105));

            areasPlayers.add(yourPlayerArea);
            playersHands.add(new HandView(yourPlayerArea));
            getChildren().add(playersHands.get(i));
            deplacementVersCoord(playersHands.get(i), 700, 325);
            playersHands.get(i).setVisible(false);
        }


        setCurrentPlayerChangesListener(whenCurrentPlayerChanges);


        game.run();


    }

    public void deplacementVersCoord(Node vBox, int x, int y){
        TranslateTransition transition;
        Duration duration = Duration.millis(500);
        transition = new TranslateTransition(duration, vBox);
        transition.setByX(x-vBox.getTranslateX());
        transition.setByY(y-vBox.getTranslateY());
        transition.play();
    }

    public OurPlayerArea getPlayerArea(int i) {
        return areasPlayers.get(i);
    }

    public void passOnClic(){
        getIGame().onPass();
    }

    @FXML
    public void retourMenu(){
        main.changeSceneToStartView();
    }

    @FXML
    public void goToTheParameters(){
        main.changeSceneParametersView("InGameView");
    }

    public void setCurrentPlayerChangesListener(ChangeListener<? super Player> whenCurrentPlayerChanges) {
        super.setCurrentPlayerChangesListener(whenCurrentPlayerChanges);
    }

    public void setDrawnCardListener(){
        getIGame().drawnCardsProperty().addListener(whenDrawnCardIsUpdate);
    }

    public void setTargetListener(){
        getIGame().getTargetPlayerProperty().addListener(whenTargetChange);
    }

    public void setWinnerListener(){
        getIGame().winnersProperty().addListener(whenWinnersChange);
    }

    //C'est ici que l'on gere l'affichage des cartes piochés pour les magasins
    private ListChangeListener<Card> whenDrawnCardIsUpdate = new ListChangeListener<Card>() {
        @Override
        public void onChanged(Change<? extends Card> change) {
            HBox contain = new HBox();
            while(change.next()){
                if(change.wasAdded()){
                    for (Card c : change.getAddedSubList()){
                        contain.getChildren().add(new DrawnCardView(new ICard(c), findPlayerArea(getIGame().getCurrentPlayer())));
                    }
                }
                else if(change.wasRemoved()){
                    for(Card c : change.getRemoved()){
                        contain.getChildren().remove(c);
                    }
                }
            }
            deplacementVersCoord(contain, 150, 325);
            getChildren().add(contain);
        }
    };

    private ChangeListener<? super Player> whenTargetChange = new ChangeListener<Player>() {
        @Override
        public void changed(ObservableValue<? extends Player> observableValue, Player player, Player t1) {
            HandView opa;
            if(player!=null && player!=getIGame().getCurrentPlayer()){
                opa = findPlayerHand(player);
                opa.setVisible(false);
                for(Node c : opa.getChildren()){
                    CardViewEssai cae = (CardViewEssai) c;
                    cae.setVisible();
                }
            }
            if(t1!=null  && t1!=getIGame().getCurrentPlayer()) {
                opa = findPlayerHand(t1);
                opa.setVisible(true);
                for(Node c : opa.getChildren()){
                    CardViewEssai cae = (CardViewEssai) c;
                    cae.setUnVisible();
                }
                deplacementVersCoord(opa, 700, 125);
            }
        }
    };

    private ListChangeListener<Player> whenWinnersChange = new ListChangeListener<>() {
        @Override
        public void onChanged(Change<? extends Player> change) {
            main.changeSceneToResultView();
            System.out.println("Les gagnants sont là");
        }
    };

    private OurPlayerArea findPlayerArea(Player player){
        for (OurPlayerArea pa : areasPlayers){
            if(pa.getPlayer().equals(player)){
                return pa;
            }
        }
        return null;
    }

    private HandView findPlayerHand(Player player){
        for(HandView h : playersHands){
            if(h.getOwner().equals(player)){
                return h;
            }
        }
        return null;
    }


    private CardView findCardView(HBox container, Card card) {
        for (Node n : container.getChildren()) {
            CardView nodeCardView = (CardView) n;
            Card nodeCard = nodeCardView.getCard();
            if (nodeCard.equals(card))
                return nodeCardView;
        }
        return null;
    }

    @Override
    protected void bindNextActionMessage() {}

    @Override
    protected void setPassSelectedListener() {
    }
}