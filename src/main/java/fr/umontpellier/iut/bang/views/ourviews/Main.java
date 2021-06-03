package fr.umontpellier.iut.bang.views.ourviews;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Main extends Node {
    private VBox conteneur;
    private Label joueur;

    public Main(VBox main, Label joueur){
        conteneur = main;
        this.joueur = joueur;
    }

}
