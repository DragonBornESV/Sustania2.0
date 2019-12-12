package com.mycompany.sustainia;

import javafx.scene.Node;
import javafx.scene.control.Button;


import static com.mycompany.sustainia.App.game;

public class Conversation extends Node {
    public void setI(int i) {
        this.i = i;
    }

    static int i = 0;
    Button b;
    public Conversation(){


            App.textBox.getTilePane().getChildren().clear();

            App.textBox.setTextBox(game.currentRoom.getNPC().getDialog()[i].getNpcText());
                for (int j = 0; j < game.currentRoom.getNPC().getDialog()[i].getPersuasionPoints().length; j++) {
                    App.textBox.getTilePane().getChildren().add(new DialogButton(game.currentRoom.getNPC().getDialog()[i].getResponses()[j],game.currentRoom.getNPC().getDialog()[i].getPersuasionPoints()[j]).getButton());

                    /*
                    if(game.currentRoom.getNPC().getDialog()[i].getPersuasionPoints()[j] >= 0){
                        this.b.setOnAction(event -> {
                            i = 1;
                            System.out.println(b.getText());

                            Conversation conversation = new Conversation();
                        });

                    }
                    else {
                        this.b.setOnAction(event -> {
                            i = 2;
                            System.out.println(b.getText());
                            Conversation conversation = new Conversation();
                        });

                    }

                     */


                }


           /*
            for (int j = 0; i < game.currentRoom.getNPC().getDialog()[i].getResponses().length; i++) {

                    App.textBox.getTilePane().getChildren().add(new Button(game.currentRoom.getNPC().getDialog()[i].getResponses()[j]));



            }

            */
        }



}
