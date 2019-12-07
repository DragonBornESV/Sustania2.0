package com.mycompany.sustainia;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ParameterPanel extends Node {

    private StackPane stackPane;
    private GridPane gridPane;
    private ProgressBar progressBar;
    private Text parameterName, progressText;
    public static List<ParameterPanel> list = new ArrayList<>();

    public ProgressBar getProgressBar() {
        return progressBar;
    }


    public String getParameterName() {
        return parameterName.getText();
    }

    public Text getProgressText() {
        return progressText;
    }





    public ParameterPanel(String name){
        stackPane = new StackPane();
        gridPane = new GridPane();
        progressBar = new ProgressBar();
        progressBar.setMinSize(250, 25);
        progressBar.setProgress(0);
        parameterName = new Text(name);
        progressText = new Text("%");

        stackPane.getChildren().add(progressBar);
        stackPane.getChildren().add(gridPane);

        gridPane.getColumnConstraints().add(new ColumnConstraints(150));
        gridPane.getColumnConstraints().add(new ColumnConstraints(110));
        gridPane.getRowConstraints().add(new RowConstraints(progressBar.getMinHeight()));

        gridPane.add(parameterName, 0,0);
        gridPane.add(progressText,1,0);

        gridPane.setHalignment(parameterName, HPos.CENTER);
        gridPane.setHalignment(progressText, HPos.RIGHT);

        list.add(this);

    }

    public StackPane getStackPane() {
        return stackPane;
    }

}

