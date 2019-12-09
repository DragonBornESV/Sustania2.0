package com.mycompany.sustainia;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MaterialsPanel extends Node {
    private ObservableList<String> observableList;
    private ListView<String> listView;

    public GridPane getGridPane() {
        return gridPane;
    }

    private GridPane gridPane;
    private Text header;

    public MaterialsPanel(){
        header = new Text("Materials");
        header.setFont(new Font(20));


        gridPane = new GridPane();
        gridPane.getColumnConstraints().add(new ColumnConstraints(290));
        gridPane.add(header,0,0);

        gridPane.setHalignment(header, HPos.CENTER);
        gridPane.setPadding(new Insets(5));
        gridPane.setGridLinesVisible(true);

    }

}
