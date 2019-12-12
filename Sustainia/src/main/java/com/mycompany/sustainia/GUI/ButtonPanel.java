package com.mycompany.sustainia.GUI;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

import java.awt.*;

public class ButtonPanel extends Node {
    /**
     * All the Buttons and Panes attributes used to swap Panels.
     */
    private Button materialsButton;
    private Button inventoryButton;
    private GridPane gridPaneButtons;

    /**
     * This Constructor creates two buttons (no functionality implemented) which is used to swap between Inventory Panel and Material Panel
     * and a gridpane to arrange the buttons.
     */
    public ButtonPanel(){
        gridPaneButtons = new GridPane(); //Creates a new Gridpane object.
        gridPaneButtons.getRowConstraints().add(new RowConstraints(100)); //Arranging the row of the
        gridPaneButtons.setHgap(10); //Sets a Horizontal gap between the cells of the gridpane.
        gridPaneButtons.setPadding(new Insets(5));// Sets a padding

        materialsButton = new Button("Materials"); //new Button object
        inventoryButton = new Button("Inventory"); //new Button object

        gridPaneButtons.add(inventoryButton,0,0); //adds the 2 buttons into the gridpane at the given cell.
        gridPaneButtons.add(materialsButton,1,0);

    }

    /**
     * Is used to return the gridpane containing the 2 buttons
     * @return Gridpane
     */
    public GridPane getGridPaneButtons() {
        return gridPaneButtons;
    }

    /**
     * Is used get the button and to add functionality later on.
     * @return materialsButton
     */
    public Button getMaterialsButton() {
        return materialsButton;
    }
    /**
     * Is used get the button and to add functionality later on.
     * @return inventoryButton
     */
    public Button getInventoryButton() {
        return inventoryButton;
    }
}
