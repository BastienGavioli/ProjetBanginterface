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


// cette classe représente la vue de la main d'un joueur, elle est utilisée par la classe Hand afin
// de pouvoir montrer la main du joueur courrant

public class YourHand extends HBox {
    private PlayerArea playerArea;
    private Player owner;

    public YourHand(PlayerArea playerArea){
        this.playerArea = playerArea;
        owner = this.playerArea.getIPlayer().getPlayer();
        setHandListener(whenHandIsUpdated);
    }

    private ListChangeListener<Card> whenHandIsUpdated = new ListChangeListener<Card>() {
        @Override
        public void onChanged(Change<? extends Card> change) {
            while(change.next()){
                if(change.wasAdded()){
                    for (Card c : change.getAddedSubList()){
                        getChildren().add(new CardViewEssai(new ICard(c), playerArea));
                    }
                }
                else if(change.wasRemoved()){
                    for(Card c : change.getRemoved()){
                        getChildren().remove(findCardView(YourHand.this,c));
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
        return this;
    }

    protected void setHandListener(ListChangeListener<Card> whenHandIsUpdated) {
        owner.handProperty().addListener(whenHandIsUpdated);
    }
}
