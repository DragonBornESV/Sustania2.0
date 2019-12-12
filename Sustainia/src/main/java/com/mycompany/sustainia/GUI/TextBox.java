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

    /**
     * All the datafields used for the Terminal.
     */
    private TextArea textBox;

    private GridPane gridPane;

    private TitledPane titledPane;

    private TilePane tilePane;

    private GridPane terminal;

    /**
     * This constructor creates the UI and arranges all the Nodes.
     */
    public TextBox(){
        gridPane = new GridPane();

        titledPane = new TitledPane();
        titledPane.setExpanded(false); //Has the terminal closed at the start.

        terminal = new GridPane();
        terminal.getColumnConstraints().add(new ColumnConstraints(500)); //adds a column with the given size for the terminal(Gridpane)
        terminal.getColumnConstraints().add(new ColumnConstraints(300)); //adds a column with the given size for the terminal(Gridpane)
        gridPane.getRowConstraints().add(new RowConstraints(600)); //adds a row with the given size for the gridpane
        gridPane.getColumnConstraints().add(new ColumnConstraints(800)); //adds a row with the given size for the gridpane

        tilePane = new TilePane();
        tilePane.setHgap(3); //Sets a Horizontal gap between the columns and rows for the tilepane
        tilePane.setVgap(3); //Sets a Vertial gap between the columns and rows for the tilepane
        tilePane.setAlignment(Pos.CENTER); //Aligns all nodes in the tilepane into the center

        titledPane.setContent(terminal); //Adds the terminal(a gridpane) into the titledpane.

        textBox = new TextArea();
        textBox.setMaxHeight(100); //sets the size for the textBox.
        textBox.setEditable(false); //makes the textBox uneditable.

        terminal.add(textBox,0,0); //adds the textbox into terminal(gridpane) at the given cell
        terminal.add(tilePane,1,0); //adds the tilepane into terminal(gridpane) at the given cell


        gridPane.add(titledPane, 0, 0); //adds the titledpane into the gridpane who arranges the titledpane in the game window
        gridPane.setValignment(titledPane, VPos.BOTTOM); //Aligns the titled pane at the bottom of the gridpane.



    }

    /**
     * Is used to get place the Terminal into the game
     * @return Gridpane
     */
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
