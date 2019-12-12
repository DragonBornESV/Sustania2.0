package com.mycompany.sustainia.GUI;
import com.mycompany.sustainia.Item;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class is used to make a custom display for the ListView class.
 */
public class ImageTextCell extends ListCell<Item> {

    /**
     * The body of the method is how we want the ListView to display each cell.
     * @param item
     * @param b
     */
    @Override
    protected void updateItem(Item item, boolean b) {
        super.updateItem(item, b);
        if (b || item == null){
            setGraphic(null);
        }
        else
        {

            HBox hBox = new HBox(2.0); //Creates a HBox with the given spacing.
            hBox.setAlignment(Pos.CENTER_LEFT); //Aligns the node to the center left.
            hBox.getChildren().add(item.getImage()); //Adds the item image into the Hbox

            Label label = new Label(item.toString()); //Creates a Label object
            label.setAlignment(Pos.CENTER_RIGHT); //Aligns the node
            hBox.getChildren().add(label); //Adds the label into the Hbox

            setGraphic(hBox); //Finally adds the Hbox containing the image and label to setGraphic which ListView uses to display the Hbox.
        }


    }
}
