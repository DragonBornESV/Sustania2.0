package com.mycompany.sustainia.GUI;

import com.mycompany.sustainia.Game;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StartMenu extends Node {


    private StackPane startPane;
    private Button startButton;
    private  TextField playerName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public StartMenu() throws FileNotFoundException {

        FileInputStream startImage = new FileInputStream("img/sustainia.png");

        Image startScreen = new Image(startImage, 900, 600, true, false);
        ImageView view = new ImageView(startScreen);
        startPane = new StackPane();
        startPane.getChildren().add(view);
        startPane.setAlignment(Pos.CENTER);


        GridPane startScreenGridPane = new GridPane();
        startScreenGridPane.getRowConstraints().add(new RowConstraints(450));
        startScreenGridPane.getColumnConstraints().add(new ColumnConstraints(300));
        startScreenGridPane.getColumnConstraints().add(new ColumnConstraints(500));

        //Start screen button
        startButton = new Button("Start");
        startButton.setMinSize(200, 100);

        //Entering player name
        playerName = new TextField();
        playerName.setPromptText("Please enter your name:");
        playerName.setMinSize(15, 10);

        startScreenGridPane.add(playerName, 1,0);
        startScreenGridPane.setValignment(playerName, VPos.BOTTOM);
        startScreenGridPane.add(startButton,1,1 );
        startScreenGridPane.setHalignment(startButton, HPos.CENTER);
        startScreenGridPane.setVgap(20);



        startPane.getChildren().add(startScreenGridPane);

    }
    public StackPane getStartMenu() {
        return startPane;
    }
    public Button getStartButton() {
        return startButton;
    }

    public TextField getPlayerName() {
        return playerName;
    }
}
