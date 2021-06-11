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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class YourPlayerArea extends PlayerArea {
    VBox rootPlayer;
    Label name;
    ImageView img;
    HBox handView;
    private HBox inPlay; //Stoque les cartes inPlay
    private CardViewEssai weapon; //Stocke l'arme


    /* L'update de la main est dans YourHand
        private ListChangeListener<Card> whenHandIsUpdate = new ListChangeListener<Card>() {
            @Override
            public void onChanged(Change<? extends Card> change) {
                while (change.next()){
                    if(change.wasAdded()){
                        for(Card c: change.getAddedSubList())
                            handView.getChildren().add(new CardViewEssai
                                    (new ICard(c),  YourPlayerArea.this));
                    }
                    else if(change.wasRemoved()){
                        for(Card c: change.getRemoved())
                            handView.getChildren().remove(findCardView(handView, c));
                    }
                }
            }
        };
    */
    public YourPlayerArea(IPlayer player, GameView gameView) {
        super(player, gameView);
        rootPlayer = new VBox();
        rootPlayer.setId("rootPlayer");
        name = new Label(player.getName());
        rootPlayer.setAlignment(Pos.CENTER);
        img = new ImageView(getImageName());
        img.setFitHeight(200);
        img.setFitWidth(150);
        handView = new HBox();

        weapon = new CardViewEssai(new ICard(new Colt()),YourPlayerArea.this);
        weapon.setTranslateX(160);
        weapon.setTranslateY(-235);

        inPlay = new HBox();
        inPlay.setMaxWidth(300);
        inPlay.setTranslateX(160);
        inPlay.setTranslateY(-155);

        setInPlayListener(whenInPlayIsUpdated);
        setWeaponListener(whenWeaponChanges);

        rootPlayer.getChildren().add(img);
        rootPlayer.getChildren().add(name);
        rootPlayer.getChildren().add(inPlay);
        rootPlayer.getChildren().add(weapon);
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
        setStyle("-fx-border-color: red");
    }

    @Override
    public void deHightlightCurrentArea() {
        setStyle("-fx-border: none");
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
                                    (new ICard(c), YourPlayerArea.this));
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
            rootPlayer.getChildren().remove(weapon);
            if(newWeapon == null){
                weapon = new CardViewEssai(new ICard(new Colt()),YourPlayerArea.this);
            }
            else{
                weapon =new CardViewEssai(new ICard(newWeapon),YourPlayerArea.this);
            }
            weapon.setTranslateX(160);
            weapon.setTranslateY(-280);
            rootPlayer.getChildren().add(weapon);
        }
    };



    /*private CardViewEssai findWeaponCardView(WeaponCard weaponCard){
        for (Node c : inPlay.getChildren()){
            CardViewEssai weapon = (CardViewEssai) c;
            if(weapon.getCard().equals(weaponCard)){
                return weapon;
            }
        }
        return null;
    }*/

}
