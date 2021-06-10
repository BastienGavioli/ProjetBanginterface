package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.cards.BlueCard;
import fr.umontpellier.iut.bang.logic.cards.Card;
import fr.umontpellier.iut.bang.logic.cards.WeaponCard;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.GameView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class YourPlayerArea extends PlayerArea {
    VBox rootPlayer;
    Label name;
    ImageView img;
    HBox handView;

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

    public YourPlayerArea(IPlayer player, GameView gameView) {
        super(player, gameView);
        rootPlayer = new VBox();
        rootPlayer.setId("rootPlayer");
        name = new Label(player.getName());
        rootPlayer.setAlignment(Pos.CENTER);
        img = new ImageView("/src/main/resources/images/characters/bartcassidy.png");
        img.setFitHeight(200);
        img.setFitWidth(150);
        handView = new HBox();


        setHandListener(whenHandIsUpdate);


        rootPlayer.getChildren().add(img);
        rootPlayer.getChildren().add(name);
        //rootPlayer.getChildren().add(handView);
        getChildren().add(rootPlayer);


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

}
