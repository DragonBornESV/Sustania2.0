package com.mycompany.sustainia;

import javafx.scene.image.ImageView;

/*
 * This class createss our objekt/datatype item.
 * Item consists of a name in form of a string,
 * a list of materials in form of a material array,
 * the amount(count) of each materials in form of an integer array,
 * the amount(count) of the item
 * and the weight of the item in form of a double. 
 */
public class Item {
    
    String name;
    
    private int itemX = 0;
    private int itemY = 0;
    private HitBox hb = new HitBox(itemX,itemY,16*4,16*4);
    
    int itemImage = 0;
    
    private ImageView image;
    
    Material[] materials = new Material[10];
    double weight;
    
    public Item(String name, Material[] materials, int imageNumber) {
        this.name = name;
        this.materials = materials;

        int sumOfMaterials = 0;   // This is a temporary variable used to calculate the weight.
        
        // We take each value from the integer array materialCount and add them together one at a time. 
        for (int j = 0; j < materials.length; j++){
             sumOfMaterials += materials[j].count;
        }
        weight = sumOfMaterials * 0.1;
        
        itemImage = imageNumber;
    }
    
    public Item(String name, int itemX, int itemY, Material[] materials, int imageNumber, HitBox hb){
        
        this.name = name;
        this.materials = materials;

        int sumOfMaterials = 0;   // This is a temporary variable used to calculate the weight.
        
        // We take each value from the integer array materialCount and add them together one at a time. 
        for (int j = 0; j < materials.length; j++){
             sumOfMaterials += materials[j].count;
        }
        weight = sumOfMaterials * 0.1;
        
        this.itemX = itemX;
        this.itemY = itemY;
        
        // Adds the coordinates of the item to align the item and the hitbox on top of each other.
        this.hb = new HitBox(hb.topLeftX + itemX, hb.topLeftX + itemX, hb.width, hb.height);
        
        //The image that is shown as the item.
        itemImage = imageNumber;
    }
    
    /**
     * @return the itemX
     */
    public int getItemX() {
        return itemX;
    }

    /**
     * @param itemX the itemX to set
     */
    public void setItemX(int itemX) {
        this.itemX = itemX;
        hb = new HitBox(this.itemX, this.itemY, hb.width, hb.height);
    }

    /**
     * @return the itemY
     */
    public int getItemY() {
        return itemY;
    }

    /**
     * @param itemY the itemY to set
     */
    public void setItemY(int itemY) {
        this.itemY = itemY;
        hb = new HitBox(this.itemX, this.itemY, hb.width, hb.height);
    }

    /**
     * @return the hb
     */
    public HitBox getHitBox() {
        return hb;
    }
    
    public void setPosition(int itemX, int itemY) {
        this.itemX = itemX;
        this.itemY = itemY;
        hb = new HitBox(this.itemX, this.itemY, hb.width, hb.height);
    }
    
    public void printPosition() {
        System.out.println(itemX + ", " + itemY);
    }
}
