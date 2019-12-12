package com.mycompany.sustainia;

import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;


public class DialogButton extends Node {
    public Button getButton() {
        return button;
    }

    private Button button;
    public static List<DialogButton> DialogButtonList = new ArrayList<>();
    public DialogButton(String name, int num){
        button = new Button(name);
        if (num >= 1) {
            button.setOnAction(event -> {
                Conversation.i++;
                System.out.println(button.getText());
                Conversation conversation = new Conversation();
            });
        }
        else {
            button.setOnAction(event -> {
                Conversation.i = 2;
                System.out.println(button.getText());
                Conversation conversation = new Conversation();
            });
        }
        DialogButtonList.add(this);
    }

}
