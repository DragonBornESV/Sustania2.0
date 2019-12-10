package com.mycompany.sustainia.GUI;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ParameterPanel extends Node {

    private StackPane stackPane;
    private GridPane gridPane;
    private ProgressBar progressBar;
    private Text parameterName, progressText;
    public static List<ParameterPanel> list = new ArrayList<>();
    public static ParameterPanel mainBar = new ParameterPanel();

    public ProgressBar getProgressBar() {
        return progressBar;
    }


    public String getParameterName() {
        return parameterName.getText();
    }

    public Text getProgressText() {
        return progressText;
    }

    public ParameterPanel(){
        stackPane = new StackPane();
        gridPane = new GridPane();
        progressBar = new ProgressBar();
        progressBar.setMinSize(250, 28);
        progressBar.setProgress(0);
        progressBar.setStyle("-fx-accent: greenyellow");
        parameterName = new Text("Main Score");
        progressText = new Text("%");

        stackPane.getChildren().add(progressBar);
        stackPane.getChildren().add(gridPane);

        gridPane.getColumnConstraints().add(new ColumnConstraints(30));
        gridPane.getColumnConstraints().add(new ColumnConstraints(120));
        gridPane.getColumnConstraints().add(new ColumnConstraints(110));
        gridPane.getRowConstraints().add(new RowConstraints(progressBar.getMinHeight()));

        gridPane.add(parameterName, 1,0);
        gridPane.add(progressText,2,0);

        gridPane.setHalignment(parameterName, HPos.LEFT);
        gridPane.setHalignment(progressText, HPos.RIGHT);


    }




    public ParameterPanel(String name){
        stackPane = new StackPane();
        gridPane = new GridPane();
        progressBar = new ProgressBar();
        progressBar.setMinSize(250, 23);
        progressBar.setProgress(0);
        parameterName = new Text(name);
        progressText = new Text("%");


        stackPane.getChildren().add(progressBar);
        stackPane.getChildren().add(gridPane);

        gridPane.getColumnConstraints().add(new ColumnConstraints(30));
        gridPane.getColumnConstraints().add(new ColumnConstraints(120));
        gridPane.getColumnConstraints().add(new ColumnConstraints(110));
        gridPane.getRowConstraints().add(new RowConstraints(progressBar.getMinHeight()));

        gridPane.add(parameterName, 1,0);
        gridPane.add(progressText,2,0);

        gridPane.setHalignment(parameterName, HPos.LEFT);
        gridPane.setHalignment(progressText, HPos.RIGHT);


        list.add(this);

    }

    public StackPane getStackPane() {
        return stackPane;
    }

}

