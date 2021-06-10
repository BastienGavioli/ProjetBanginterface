package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class CardViewEssai extends CardView {
    private Boolean isVisible = true;
    private ImageView frontImage;
    private IPlayer player;
    private Label cardName;
    private VBox affichage;
    private static ImageView backImage = new ImageView(getBack());

    public CardViewEssai(ICard card, PlayerArea playerArea){
        super(card, playerArea);
        setCardSelectionListener();


        frontImage = new ImageView(new Image(card.getImageName()));
        frontImage.setFitHeight(120);
        frontImage.setFitWidth(85);

        player = playerArea.getIPlayer();
        cardName = new Label(getICard().getName());
        affichage = new VBox();


        affichage.getChildren().add(cardName);
        affichage.getChildren().add(frontImage);


        getChildren().add(affichage);

    }


    @Override
    public void setVisible() {
        if(!isVisible){
            isVisible = true;
            affichage.getChildren().remove(backImage);
            affichage.getChildren().add(frontImage);
        }
    }

    @Override
    public void setUnVisible() {
        if(isVisible){
            isVisible = false;
            affichage.getChildren().remove(frontImage);
            affichage.getChildren().add(backImage);
            }
        }


     @Override
    protected void setCardSelectionListener() {
        setOnMouseClicked(whenCardSelected);
    }

    EventHandler<MouseEvent> whenCardSelected = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            CardViewEssai selectedCardView = (CardViewEssai) mouseEvent.getSource();
            ICard selectedICard = selectedCardView.getICard();
            IPlayer owner = selectedCardView.getPlayerArea().getIPlayer();
            IGame currentGame = selectedCardView.getPlayerArea().getGameView().getIGame();
            currentGame.onCardSelection(selectedICard,owner);
        }
    };


}

