package com.mycompany.sustainia;

import java.util.ArrayList;
import java.util.Arrays;

public class Inventory {
    // We create variables for our starting inventory stats.
    float value = 0;           // The amount of value the player is carrying.
    float carrying = 0;        // The current weight the player is carrying.
    int carryingCapacity = 100; // The total amount of weight the player is able to carry.
    Material[] materialArray = new Material[10];

    private ArrayList<Item> itemsInInventory;
    
    HitBox standardHitBox = new HitBox(0,0,10,10);
    
    // This method is called when creating a new inventory in the class Game. 
    public Inventory(){
        // Clones the materials from World
        // (it is clones because the count attribute is modified during gameplay in each object.)
        
        //Clones every material over from World
        for (int i = 0; i < materialArray.length; i++) {
            materialArray[i] = World.materialArray[i].clone();
        }
        
        itemsInInventory = new ArrayList<>(); 
        updateWeight();
        updateValue();
        System.out.println(value);

    }
    
   
    /**
     * The method updateWeight() is used to update the money value and the carry value.
     * The method is called whenever an item is removed or placed in the inventory
     */  
    public void updateWeight() {
        float tempWeight = 0;         // The first temporary variable is used to calculate the amount of weight the player is carrying.
      
        // This for loop loops through the item array and takes the weight value and multiplies it with the item count value.      
        for (int i = 0; i < itemsInInventory.size(); i++){                
            tempWeight += getItemsInInventory().get(i).weight;
        }

        carrying = tempWeight;
    }
    
    public void updateValue() {
        value = 0;
        
        for (int i = 0; i < itemsInInventory.size(); i++) {
            value += itemsInInventory.get(i).value;
            System.out.println(itemsInInventory.get(i).value);
        }
    }
  
    /*
     * The selected item is removed from the inventory and the materials the item consists of is added to the inventory.
     */
    public void salvageMaterials(Item itemToSalvage){
        //Iterates through the materials in the item and adds them to the 
        // materials in the inventory.
        
        //Is there an item to salvage from?
        if (itemToSalvage == null) {
            // Goes through every material in the item.
            for (int i = 0; i < itemToSalvage.materials.length; i++) {
                //Then goes through every material in the inventory.
                for (int j = 0; j < materialArray.length; j++) {
                    // Checks if the item material and inventory material are
                    // the same and adds the item material-count to the inventory.
                    if (itemToSalvage.materials[i].name == materialArray[j].name) {
                        materialArray[j].count += itemToSalvage.materials[i].count;
                    }
                }
            }

            //Removes the salvaged item from the inventory
            getItemsInInventory().remove(itemToSalvage);
        }
    }
    
    /**
     * Converts the material into value
     */
    public void recycleMaterials() {
        // Goes through every material in the inventory and calculates the value 
        // of the materials in the entire inventory. 
        for (int i = 0; i < materialArray.length; i++) {
            value += materialArray[i].count * materialArray[i].value;
            
            //Resets the materials in the inventory.
            materialArray[i].count = 0;
            
            //Converts the value into points on the parameter for recycling.
            Parameter.mapAddScore("Recycling", value/2);
            
            //Resets the value
            value = 0;
        }
        
        updateWeight();
    }

    /**
     * @return the itemsInInventory
     */
    public ArrayList<Item> getItemsInInventory() {
        return itemsInInventory;
    }
    
    /**
     * Can be used to return the names of the items in the inventory.
     * @return the names of the items in the inventory.
     */
    public String toString() {
        String toBeReturned = "[";
        
        for (int i = 0; i < itemsInInventory.size(); i++) {
            if (i != 0) {
                toBeReturned += ", ";
            }
            toBeReturned += itemsInInventory.get(i).getName();
        }
        return toBeReturned + "]";
    }
    
    /**
     * Prints out the material name and the quantity of each material in the array.
     */
    public void printMaterials() {
        System.out.println(Arrays.toString(materialArray));
    }
    
}
