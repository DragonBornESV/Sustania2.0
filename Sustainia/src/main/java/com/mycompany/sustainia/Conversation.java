package com.mycompany.sustainia;

import javafx.scene.Node;
import javafx.scene.control.Button;


import static com.mycompany.sustainia.App.game;

public class Conversation extends Node {


    static int i = 0; //Determines which dialog is being used.
    static int points = 0; //The persuation points which is used to compare if the player has convinced the NPC.

    /**
     * This constructor is used to start a Conversation with a NPC.
     */
    public Conversation() {

        NPC npc = game.currentRoom.getNPC(); //gets the NPC in current room
        Say[] dialog = npc.getDialog(); //gets the dialogs of the NPC


        App.textBox.getTitledPane().setExpanded(true); //Expands the terminal

        /**
         * Creates a "leave conversation" button and adds functionality
         */
        Button leaveButton = new Button("Leave Conversation");
        leaveButton.setOnAction(actionEvent -> {
            leaveConversation();
        });


        App.textBox.getTilePane().getChildren().clear(); //Removes all the previous response buttons (if there is any) of the previous dialog.

        /**
         * Checks if the NPC is convinced, and add points to a specific parameter.
         *
         */
        if (points > npc.getPersuasionTrigger()) {
            App.textBox.setTextBox(npc.getEndTriggerMessage()); //Display the end message from the NPC
            npc.givePoints(); //Add points
            System.out.println("FÆRDIG");
            points = 0; //sets the persuation points to 0.
            i = 0; //sets the initializer back to 0.
            App.textBox.getTilePane().getChildren().add(leaveButton); //Adds "leave conversation" button
            return;
        }

        /**
         * Checks is if there is anymore dialogs left from the NPC.
         */
        if (dialog.length < i+1) {
            // Display the lose message
            App.textBox.setTextBox("You failed to convince " + npc.getNpcName() + "...\n" +
                "Talk to the person again. " + "Try to be more convincing this time...");
            App.textBox.getTilePane().getChildren().add(leaveButton);
            return;
        }

        App.textBox.setTextBox(dialog[i].getNpcText());
        for (int j = 0; j < dialog[i].getResponses().length; j++) {
            App.textBox.getTilePane().getChildren().add(new DialogButton(dialog[i].getResponses()[j], dialog[i].getPersuasionPoints()[j]).getButton());
        }
        App.textBox.getTilePane().getChildren().add(leaveButton);

    }

    /**
     * This method gets called when the "leave conversation" button is pressed, which closes the terminal,
     * resets initializer (i = 0), reset persuation points, clears the text in the terminal and removes all buttons.
     */
    public static void leaveConversation(){
        App.textBox.getTitledPane().setExpanded(false);

        i = 0;
        points = 0;
        App.textBox.getTextBox().clear();
        App.textBox.getTilePane().getChildren().clear(); //Removes all the previous response buttons (if there is any) of the previous dialog.
    }
}
