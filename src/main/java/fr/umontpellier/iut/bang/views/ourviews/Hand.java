package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.views.GameView;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

//cette classe est la vue de la main du jouer courrant : elle affiche la main du joueur en train de jouer
//et s'actualise quand le joueur courrant change, montrant la main du nouveau joueur courrant

public class Hand extends VBox {
    //private Label name;

    public Hand( ){
        //name = new Label();
        //getChildren().add(0, name);
    }

    /*public void setName(String name) {
        this.name = new Label(name);
    }*/

   /* public Label getName() {
        return name;
    }*/

    public void emptyHand(){
        getChildren().remove(0);
        //getChildren().remove(1);
    }

    public void renewHand(YourHand playerHand){
        //getChildren().add(0,name);
        getChildren().add(playerHand);
    }
}
