package com.mycompany.sustainia.GUI;

import com.mycompany.sustainia.Sustainia;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WinningScreen extends Node {
    public ImageView getView() {
        return view;
    }

    private ImageView view;

    public WinningScreen() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("Sustainia/img/winscreen.png");
        Image winScreen = new Image(fileInputStream, 900, 600, true, false);
        view = new ImageView(winScreen);
        view.setVisible(false);


    }
}
