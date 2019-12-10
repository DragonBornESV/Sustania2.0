package com.mycompany.sustainia.GUI;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

import java.awt.*;

public class ButtonPanel extends Node {
    private Button materialsButton;
    private Button inventoryButton;
    private GridPane gridPaneButtons;

    public ButtonPanel(){
        gridPaneButtons = new GridPane();
        gridPaneButtons.getRowConstraints().add(new RowConstraints(100));
        gridPaneButtons.setHgap(10);
        gridPaneButtons.setPadding(new Insets(5));

        materialsButton = new Button("Materials");
        inventoryButton = new Button("Inventory");

        gridPaneButtons.add(inventoryButton,0,0);
        gridPaneButtons.add(materialsButton,1,0);

    }

    public GridPane getGridPaneButtons() {
        return gridPaneButtons;
    }
    public Button getMaterialsButton() {
        return materialsButton;
    }

    public Button getInventoryButton() {
        return inventoryButton;
    }
}
