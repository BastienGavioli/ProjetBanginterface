package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.cards.BlueCard;
import fr.umontpellier.iut.bang.logic.cards.Card;
import fr.umontpellier.iut.bang.logic.cards.Colt;
import fr.umontpellier.iut.bang.logic.cards.WeaponCard;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.GameView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;


public class OurPlayerArea extends PlayerArea {
    private VBox rootPlayer;
    private Label name;
    private ImageView img;
    private HBox handView;
    private HBox inPlay; //Stocke les cartes inPlay


    public OurPlayerArea(IPlayer player, GameView gameView) {
        super(player, gameView);

        setOnMouseClicked(whenPlayerSelected);


        rootPlayer = new VBox();
        rootPlayer.setId("rootPlayer");
        name = new Label(player.getName());
        rootPlayer.setAlignment(Pos.CENTER);
        img = new ImageView(getImageName());
        img.setFitHeight(200);
        img.setFitWidth(150);
        handView = new HBox();


        inPlay = new HBox();
        inPlay.setMaxWidth(300);
        inPlay.setTranslateX(160);
        inPlay.setTranslateY(-155);
        inPlay.getChildren().add(0,new CardViewEssai(new ICard(new Colt()), OurPlayerArea.this));

        setInPlayListener(whenInPlayIsUpdated);
        setWeaponListener(whenWeaponChanges);

        rootPlayer.getChildren().add(img);
        rootPlayer.getChildren().add(name);
        rootPlayer.getChildren().add(inPlay);
        getChildren().add(rootPlayer);




    }

    private String getImageName(){
        return("images/characters/" + super.getIPlayer().getBangCharacter().getName().toLowerCase().replaceAll("\\s+","")+".png" );
    }

    @Override
    protected void setHandListener(ListChangeListener<Card> whenHandIsUpdated) {
        super.setHandListener(whenHandIsUpdated);
    }

    @Override
    protected void setInPlayListener(ListChangeListener<BlueCard> whenInPlayIsUpdated) {
        super.setInPlayListener(whenInPlayIsUpdated);
    }

    @Override
    protected void setHealthPointsListener(ChangeListener<? super Number> whenInPlayIsUpdated) {
        super.setHealthPointsListener(whenInPlayIsUpdated);
    }

    @Override
    protected void setWeaponListener(ChangeListener<? super WeaponCard> whenWeaponChanges) {
        super.setWeaponListener(whenWeaponChanges);
    }

    @Override
    public void highlightCurrentArea() {
        name.setStyle("-fx-border-color: red");
    }

    @Override
    public void deHightlightCurrentArea() {
        name.setStyle("-fx-border: none");
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

    private ListChangeListener<BlueCard> whenInPlayIsUpdated = new ListChangeListener<BlueCard>() {
        @Override
        public void onChanged(Change<? extends BlueCard> change) {
            while (change.next()){
                if(change.wasAdded()){
                    for(Card c: change.getAddedSubList()) {
                        if(!(c instanceof WeaponCard)){
                            inPlay.getChildren().add(new CardViewEssai
                                    (new ICard(c), OurPlayerArea.this));
                        }
                    }
                }
                else if(change.wasRemoved()){
                    for(Card c: change.getRemoved()) {
                        if(!(c instanceof WeaponCard)){
                            inPlay.getChildren().remove(findCardView(handView, c));
                        }
                    }
                }
            }
        }
    };

    private ChangeListener<? super WeaponCard> whenWeaponChanges = new ChangeListener<WeaponCard>() {
        @Override
        public void changed(ObservableValue<? extends WeaponCard> observableValue, WeaponCard oldWeapon, WeaponCard newWeapon) {
            inPlay.getChildren().remove(0);
            if(newWeapon == null){
                inPlay.getChildren().add(0,new CardViewEssai(new ICard(new Colt()), OurPlayerArea.this));
            }
            else{
                inPlay.getChildren().add(0,new CardViewEssai(new ICard(newWeapon), OurPlayerArea.this));
            }
        }
    };

    EventHandler<MouseEvent> whenPlayerSelected = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            OurPlayerArea selectedYourPlayerArea = (OurPlayerArea) mouseEvent.getSource();
            IPlayer target = selectedYourPlayerArea.getIPlayer();
            GameView currentGame = selectedYourPlayerArea.getGameView();
            currentGame.getIGame().onTargetSelection(target);
        }
    };

}
