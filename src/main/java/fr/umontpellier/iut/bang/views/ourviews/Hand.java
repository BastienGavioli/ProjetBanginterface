package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.views.GameView;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Hand extends Node {
    private GameView game;
    private VBox container;
    private Label name;

    public Hand(GameView gameView){
        game = gameView;
        container = new VBox();
        gameView.getChildren().add(this);
    }

    public VBox getContainer() {
        return container;
    }

    public void setName(String name) {
        this.name = new Label(name);
    }

    public Label getName() {
        return name;
    }

    public void emptyHand(){
        for(Node n : container.getChildren()){
            container.getChildren().remove(n);
        }
    }

    public void renewHand(YourHand playerHand){
        container.getChildren().add(name);
        container.getChildren().add(playerHand.getVueMain());
    }
}
