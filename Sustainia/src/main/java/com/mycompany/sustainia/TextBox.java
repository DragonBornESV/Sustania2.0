package com.mycompany.sustainia;

import javafx.animation.TranslateTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;

public class TextBox extends Node {

    private TextArea textBox;

    private GridPane gridPane;

    private TitledPane titledPane;

    public TextBox(){
        gridPane = new GridPane();

        titledPane = new TitledPane();
        titledPane.setText("Terminal");
        titledPane.setExpanded(false);
        titledPane.setAlignment(Pos.CENTER);


        gridPane.getRowConstraints().add(new RowConstraints(600));

        textBox = new TextArea();
        titledPane.setContent(textBox);
        textBox.setMaxHeight(100);
        textBox.setEditable(false);

        gridPane.getColumnConstraints().add(new ColumnConstraints(800));


        gridPane.add(titledPane, 0, 0);
        gridPane.setValignment(titledPane, VPos.BOTTOM);



    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setTextBox(String string){
        textBox.setText(string);
    }

}
