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

        App.textBox.getTilePane().getChildren().clear(); //Removes all the previous response buttons (if there is any) of the previous dialog.

        //Checks if the NPC is convinced?
        if (points > npc.getPersuasionTrigger()) {
            App.textBox.setTextBox(npc.getEndTriggerMessage());
            npc.givePoints();
            System.out.println("FÆRDIG");
            points = 0;
            i = 0;
            return;
        }

        // Is there no dialog left?
        if (dialog.length < i+1) {
            // Display the loose message
            App.textBox.setTextBox("You failed to convince " + npc.getNpcName() + "...\n" +
                "Talk to the person again. " + "Try to be more convincing this time...");
            return;
        }

        App.textBox.setTextBox(dialog[i].getNpcText());
        for (int j = 0; j < dialog[i].getResponses().length; j++) {
            App.textBox.getTilePane().getChildren().add(new DialogButton(dialog[i].getResponses()[j], dialog[i].getPersuasionPoints()[j]).getButton());

        }


    }
}
