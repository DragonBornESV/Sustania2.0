package com.mycompany.sustainia.GUI;

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

    public GridPane getTerminal() {
        return terminal;
    }

    public TilePane getTilePane() {
        return tilePane;
    }

    private TilePane tilePane;

    private GridPane terminal;

    public TextBox(){
        gridPane = new GridPane();

        titledPane = new TitledPane();
        titledPane.setText("Terminal");
        titledPane.setExpanded(false);
        titledPane.setAlignment(Pos.CENTER);
        terminal = new GridPane();
        terminal.getColumnConstraints().add(new ColumnConstraints(600));
        terminal.getColumnConstraints().add(new ColumnConstraints(200));
        gridPane.getRowConstraints().add(new RowConstraints(600));
        tilePane = new TilePane();

        textBox = new TextArea();
        titledPane.setContent(terminal);
        textBox.setMaxHeight(100);
        textBox.setEditable(false);
        terminal.add(textBox,0,0);
        terminal.add(tilePane,1,0);
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
