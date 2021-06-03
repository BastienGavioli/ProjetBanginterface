package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.cards.Card;
import fr.umontpellier.iut.bang.views.CardView;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class YourHand extends Node {
    private IPlayer owner;
    private HBox vueMain;

    public YourHand(IPlayer player){
        owner = player;
        vueMain = new HBox();
    }

    private ListChangeListener<Card> whenHandIsUpdated = new ListChangeListener<Card>() {
        @Override
        public void onChanged(Change<? extends Card> change) {
            while(change.next()){
                if(change.wasAdded()){
                    for (Card c : change.getAddedSubList()){
                        //vueMain.getChildren().add(new CardView(new ICard(c), owner.getPlayerArea()));
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

}
