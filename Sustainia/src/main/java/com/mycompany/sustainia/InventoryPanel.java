package com.mycompany.sustainia;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


public class InventoryPanel extends Node {

    private ObservableList<Item> observableList;
    private ListView<Item> listView;
    private GridPane gridPane;
    private Text header;


    public InventoryPanel(ObservableList<Item> itemsInInventory){
        header = new Text("Inventory");
        header.setFont(new Font(20));
        listView = new ListView<>();
        observableList = itemsInInventory;
        gridPane = new GridPane();
        gridPane.getColumnConstraints().add(new ColumnConstraints(290));
        gridPane.add(header,0,0);
        gridPane.add(listView,0,1);
        gridPane.setHalignment(header, HPos.CENTER);
        gridPane.setPadding(new Insets(5));
        listView.setItems(observableList);
        listView.setCellFactory(itemListView -> new ImageTextCell());


    }

    public GridPane getGridPane() {
        return gridPane;
    }


}
