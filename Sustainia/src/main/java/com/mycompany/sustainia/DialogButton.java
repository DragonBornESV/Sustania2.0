package com.mycompany.sustainia;

import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * The button for one response in the dialog menu
 */
public class DialogButton extends Node {

    private Button button;

    /**
     * This Constructor makes a button for each response available, and adds functionality to the button.
     *
     * @param name text response to the Button.
     * @param points the amount of Persuasion points the response Button gives.
     */
    public DialogButton(String name, int points){
        button = new Button(name);

        /**
         * setOnAction adds the functionality for the button.
         */
        button.setOnAction(event -> {
            Conversation.points += points; // The amount of persuation points the response button gives
            Conversation.i++; //Increments the initializer to move to the next dialog[i].
            System.out.println(button.getText());

            Conversation conversation = new Conversation(); //When the response button is pressed it creates a new Conversation object.
        });
    }

    /**
     * Is used to add to the response Button to the UI.
     * @return a button
     */
    public Button getButton() {
        return button;
    }

}
