package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.cards.BlueCard;
import fr.umontpellier.iut.bang.logic.cards.WeaponCard;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;

/**
 * Cette classe sert à afficher les cartes, qu'elles soient dans la main, face caché ou partout sur l'écran
 */

public class CardViewEssai extends CardView {
    private Boolean isVisible;
    /**
     * frontImage contient la face visible de la carte
     * backImage contient le dos de la carte
     * displayImage contient l'imageView à afficher parmit les deux choix au dessus
     */
    private ImageView frontImage, backImage, displayImage;

    /**
     * Le possesseur de la carte
     */
    private IPlayer player;
    /**
     * Le nom de la carte à afficher
     */
    private Label cardName;
    /**
     * Le bloc contenant tout les elements de la carte pour les afficher
     */
    private VBox affichage;


    public CardViewEssai(ICard card, PlayerArea playerArea){
        super(card, playerArea);
        setCardSelectionListener();
        isVisible=true;


        frontImage = new ImageView(new Image(card.getImageName()));
        frontImage.setFitHeight(120);
        frontImage.setFitWidth(85);

        backImage = new ImageView(getBack());
        backImage.setFitHeight(120);
        backImage.setFitWidth(85);

        displayImage = frontImage;

        player = playerArea.getIPlayer();
        cardName = new Label(getICard().getName());

        if(card.getCard() instanceof BlueCard){
            cardName.setStyle("-fx-translate-y: -110px;" + "-fx-font-family: Algerian;" + "-fx-text-fill: #4169E1");
        }
        else{
            cardName.setStyle("-fx-translate-y: -110px;" + "-fx-font-family: Algerian;" + "-fx-text-fill: #CD853F");
        }

        affichage = new VBox();
        affichage.setAlignment(Pos.CENTER);
        affichage.getChildren().add(displayImage);
        affichage.getChildren().add(cardName);

        getChildren().add(affichage);

    }


    @Override
    public void setVisible() {
        affichage.getChildren().remove(displayImage);
        affichage.getChildren().remove(cardName);
        isVisible = !isVisible;
        displayImage = frontImage;
        affichage.getChildren().add(displayImage);
        affichage.getChildren().add(cardName);
    }

    @Override
    public void setUnVisible() {
        affichage.getChildren().remove(displayImage);
        affichage.getChildren().remove(cardName);
        isVisible = !isVisible;
        displayImage = backImage;
        affichage.getChildren().add(displayImage);
    }


     @Override
    protected void setCardSelectionListener() {
        setOnMouseClicked(whenCardSelected);
    }

    EventHandler<MouseEvent> whenCardSelected = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            //Mise à jour des variables
            CardViewEssai selectedCardView = (CardViewEssai) mouseEvent.getSource();
            ICard selectedICard = selectedCardView.getICard();
            IPlayer owner = selectedCardView.getPlayerArea().getIPlayer();
            IGame currentGame = selectedCardView.getPlayerArea().getGameView().getIGame();

            //Action de la carte
            currentGame.onCardSelection(selectedICard,owner);
            //son de la carte
            if(isVisible){
                URL url;
                if(selectedICard.getCard() instanceof WeaponCard)
                    url = getClass().getClassLoader().getResource("sounds/Arme.mp3");
                else
                    url = getClass().getClassLoader().getResource("sounds/"+selectedICard.getName()+".mp3");
                if(url!=null) {
                    Media media = new Media(url.toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.setVolume(getPlayerArea().getGameView().getIGame().getVolume());
                    mediaPlayer.play();
                }
                else{
                    System.out.println("sounds/"+selectedICard.getName()+".mp3");
                }
            }
        }
    };


}

