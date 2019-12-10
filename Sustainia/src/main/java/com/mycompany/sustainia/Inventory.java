package com.mycompany.sustainia;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Inventory {
    // We create variables for our starting inventory stats.
    double money = 0;           // The amount of money the player is carrying.
    double carrying = 0;        // The current weight the player is carrying.
    int carryingCapacity = 100; // The total amount of weight the player is able to carry.
    Material[] materialArray;

    private ObservableList<Item> itemsInInventory;
    
    HitBox standardHitBox = new HitBox(0,0,10,10);
    
    // This method is called when creating a new inventory in the class Game. 
    public Inventory(){
        // Clones the materials from World
        // (it is clones because the count attribute is modified during gameplay in each object.)
        materialArray = World.materialArray.clone();
        
        itemsInInventory = FXCollections.observableArrayList();
        updateInventory();

    }
    
   
/* 
 * The method updateInventory() is used to update the money value and the carry value.
 * The method is called whenever an item is removed or placed in the inventory
 */  
  public void updateInventory() {
        double tempWeight = 0;         // The first temporary variable is used to calculate the amount of weight the player is carrying.
        double tempValue = 0;        // The second temporary variable is used to calculate the total material value the player is carrying.
      
    // This for loop loops through the item array and takes the weight value and multiplies it with the item count value.      
    for (int i = 0; i < itemsInInventory.size(); i++){                
            tempWeight += getItemsInInventory().get(i).weight;
        }
    // This for loop loops through the material array and takes the value of each material and multiplies it with the material count value.
    for (int j = 0; j < materialArray.length; j++){
            tempValue += materialArray[j].value*materialArray[j].count;
        }
        carrying = tempWeight;
        money = tempValue;
        
    }
  
    /*
     * This method is called when the user salvage <itemName> command is used.
     * The selected item is removed from the inventory and the materials the item consists of is added to the inventory.
     */
    public void salvageMaterials(Item itemToSalvage){
        
        for (int i = 0; i < itemToSalvage.materials.length; i++) {
            materialArray[i].count += itemToSalvage.materials[i].count;
        }
        
        getItemsInInventory().remove(itemToSalvage);
    } 

    /**
     * @return the itemsInInventory
     */
    public ObservableList<Item> getItemsInInventory() {
        return itemsInInventory;
    }
    
    /**
     * Can be used to return the names of the items in the inventory.
     * @return the names of the items in the inventory.
     */
    public String toString() {
        String toBePrinted = "[";
        
        for (int i = 0; i < itemsInInventory.size(); i++) {
            if (i != 0) {
                toBePrinted += ", ";
            }
            
            toBePrinted += itemsInInventory.get(i).getName();
        }
        
        return toBePrinted + "]";
    }
    
}
