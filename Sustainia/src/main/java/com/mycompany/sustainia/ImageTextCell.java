package com.mycompany.sustainia;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ImageTextCell extends ListCell<Item> {

    @Override
    protected void updateItem(Item item, boolean b) {
        super.updateItem(item, b);
        if (b || item == null){
            setGraphic(null);
        }
        else
        {
            HBox hBox = new HBox(2.0);
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.getChildren().add(item.image);

            Label label = new Label(item.toString());
            label.setAlignment(Pos.CENTER_RIGHT);
            hBox.getChildren().add(label);

            setGraphic(hBox);
        }


    }
}
