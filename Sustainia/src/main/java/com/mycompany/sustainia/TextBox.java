package com.mycompany.sustainia;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TextBox extends Node {

    private Label textBox;

    public GridPane getGridPane() {
        return gridPane;
    }

    private GridPane gridPane;

    public TextBox(Node node){
        gridPane = new GridPane();

        gridPane.getRowConstraints().add(new RowConstraints(400));
        gridPane.getColumnConstraints().add(new ColumnConstraints(801));
        textBox = new Label("kewewkærewælrkewælrklæewærlewlærkelwærklewkæ");
        textBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.add(textBox, 0, 1);
        gridPane.setHalignment(textBox, HPos.CENTER);
        gridPane.setGridLinesVisible(true);
        gridPane.setVisible(true);


    }

}
