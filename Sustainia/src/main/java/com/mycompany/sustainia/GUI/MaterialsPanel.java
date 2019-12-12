package com.mycompany.sustainia.GUI;
import com.mycompany.sustainia.*;

import javafx.collections.FXCollections;
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
    private ObservableList<com.mycompany.sustainia.Material> observableList;
    private ListView<Material> listView;
    private GridPane gridPane;
    private Text header;

    public MaterialsPanel(){
        header = new Text("Materials");
        header.setFont(new Font(20));
        listView = new ListView<>();
        observableList = FXCollections.observableArrayList();
        for (Material m : World.materialArray) {
            observableList.add(m);
        }

        gridPane = new GridPane();
        gridPane.getColumnConstraints().add(new ColumnConstraints(290));
        gridPane.add(header,0,0);
        gridPane.setHalignment(header, HPos.CENTER);

        gridPane.add(listView,0,1);
        gridPane.setPadding(new Insets(5));
        listView.setItems(observableList);



    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
