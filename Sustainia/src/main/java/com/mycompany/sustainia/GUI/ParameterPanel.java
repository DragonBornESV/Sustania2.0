package com.mycompany.sustainia.GUI;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
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
    public static GridPane parameterGridpane = new GridPane();


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

    public static void insertParametersIntoPanel(){
        Text titelParamater = new Text("Parameter");
        titelParamater.setFont(new Font(20));
        parameterGridpane.getColumnConstraints().add(new ColumnConstraints(300));
        parameterGridpane.setVgap(2);
        parameterGridpane.add(titelParamater, 0 ,0);
        parameterGridpane.setHalignment(titelParamater, HPos.CENTER);
        parameterGridpane.add(mainBar.getStackPane(),0,1);

        //Adding all of the Parameters to the gridpane to display them.
        for (int i = 0; i < list.size(); i++) {

            parameterGridpane.add(list.get(i).getStackPane(),0,i+2);
        }
    }

    public static void createParameterPanel(){
        ParameterPanel p1 = new ParameterPanel("City Equality");
        ParameterPanel p2 = new ParameterPanel("City Green Energy");
        ParameterPanel p3 = new ParameterPanel("City Clean Water");
        ParameterPanel p4 = new ParameterPanel("Sustainable Housing");
        ParameterPanel p5 = new ParameterPanel("City Clean Air");
        ParameterPanel p6 = new ParameterPanel("City Cleanliness");
        ParameterPanel p7 = new ParameterPanel("City Security");
    }

    public StackPane getStackPane() {
        return stackPane;
    }
    public ProgressBar getProgressBar() {
        return progressBar;
    }
    public String getParameterName() {
        return parameterName.getText();
    }
    public Text getProgressText() {
        return progressText;
    }
}

