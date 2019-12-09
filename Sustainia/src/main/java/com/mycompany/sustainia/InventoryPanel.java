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

    private ObservableList<ImageView> observableList;

    public ListView<ImageView> getListView() {
        return listView;
    }

    private ListView<ImageView> listView;

    public GridPane getGridPane() {
        return gridPane;
    }

    private GridPane gridPane;
    private Text header;
    private ImageView imageView;

    public InventoryPanel(){
        header = new Text("Inventory");
        header.setFont(new Font(20));
        listView = new ListView<>();
        observableList = FXCollections.observableArrayList();
        gridPane = new GridPane();
        gridPane.getColumnConstraints().add(new ColumnConstraints(290));
        gridPane.add(header,0,0);
        gridPane.add(listView,0,1);
        gridPane.setHalignment(header, HPos.CENTER);
        gridPane.setPadding(new Insets(5));
        gridPane.setGridLinesVisible(true);

    }

    public void addInventory(ImageView imageView){
        listView.setItems(observableList);
        observableList.add(imageView);
        updateList();
    }
    public void removeInv(){
        ImageView imageSelected = listView.getSelectionModel().getSelectedItem();
        observableList.remove(imageSelected);

        updateList();
    }

    public void updateList(){

    }


}
