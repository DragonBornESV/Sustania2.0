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

    private TilePane tilePane;

    private GridPane terminal;

    public TextBox(){
        gridPane = new GridPane();

        titledPane = new TitledPane();
        titledPane.setExpanded(false);
        titledPane.setAlignment(Pos.CENTER);
        terminal = new GridPane();
        terminal.getColumnConstraints().add(new ColumnConstraints(500));
        terminal.getColumnConstraints().add(new ColumnConstraints(300));
        gridPane.getRowConstraints().add(new RowConstraints(600));
        tilePane = new TilePane();
        tilePane.setHgap(3);
        tilePane.setVgap(3);
        tilePane.setAlignment(Pos.CENTER);

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

    public GridPane getTerminal() {
        return terminal;
    }

    public TitledPane getTitledPane() {
        return titledPane;
    }

    public TilePane getTilePane() {
        return tilePane;
    }
    
    public TextArea getTextBox() {
        return textBox;
    }

    public void setTextBox(String string){

        textBox.setText(string);

    }


}
