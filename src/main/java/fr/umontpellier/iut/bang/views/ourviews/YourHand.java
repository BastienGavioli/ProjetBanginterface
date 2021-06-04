package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.Player;
import fr.umontpellier.iut.bang.logic.cards.Card;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class YourHand extends Node {
    private PlayerArea playerArea;
    private Player owner;
    private HBox vueMain;

    public YourHand(PlayerArea playerArea){
        this.playerArea = playerArea;
        owner = this.playerArea.getIPlayer().getPlayer();
        vueMain = new HBox();
    }

    private ListChangeListener<Card> whenHandIsUpdated = new ListChangeListener<Card>() {
        @Override
        public void onChanged(Change<? extends Card> change) {
            while(change.next()){
                if(change.wasAdded()){
                    for (Card c : change.getAddedSubList()){
                        vueMain.getChildren().add(new CardViewEssai(new ICard(c), playerArea));
                    }
                }
                else if(change.wasRemoved()){
                    for(Card c : change.getRemoved()){
                        vueMain.getChildren().remove(findCardView(vueMain,c));
                    }
                }
            }
        }
    };

    private CardView findCardView(HBox container, Card card) {
        for (Node n : container.getChildren()) {
            CardView nodeCardView = (CardView) n;
            Card nodeCard = nodeCardView.getCard();
            if (nodeCard.equals(card))
                return nodeCardView;
        }
        return null;
    }

    public Player getOwner() {
        return owner;
    }

    public HBox getVueMain() {
        return vueMain;
    }
}
