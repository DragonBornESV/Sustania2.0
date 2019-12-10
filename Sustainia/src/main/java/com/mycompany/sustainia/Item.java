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
public class Item implements Cloneable {
    
    private String name;
    
    private int itemX = 0;
    private int itemY = 0;
    private HitBox hb = new HitBox(itemX,itemY,16,16);

    ImageView image;

    int imageNumber = 0;
    
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
        
        this.imageNumber = imageNumber;
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
        this.imageNumber = imageNumber;
    }

    
    public int getItemX() {
        return itemX;
    }

    public void setItemX(int itemX) {
        this.itemX = itemX;
        hb = new HitBox(this.itemX, this.itemY, hb.width, hb.height);
    }

    public int getItemY() {
        return itemY;
    }

    public void setItemY(int itemY) {
        this.itemY = itemY;
        hb = new HitBox(this.itemX, this.itemY, hb.width, hb.height);
    }

    public HitBox getHitBox() {
        return hb;
    }
    
    public String getName() {
        return name;
    }
    
    public void setPosition(int itemX, int itemY) {
        this.itemX = itemX*World.scale;
        this.itemY = itemY*World.scale;
        hb = new HitBox(this.itemX/World.scale, this.itemY/World.scale, hb.width, hb.height);
    }
    
    public void printPosition() {
        System.out.println(itemX + ", " + itemY);
    }
    
    /**
     * This can be used to clone a standard item from the World class and set
     * their position at the same time.
     * @param x the x-coordinate of the cloned item.
     * @param y the y-coordiante of the cloned item.
     * @return the cloned item with the new position.
     */
    public Item cloneAndPosition(int x, int y) {
        Item newItem = null;
        try {
            newItem = (Item) this.clone();
            newItem.setPosition(x, y);
        } catch (CloneNotSupportedException ex) {
            System.out.println("Could not clone item");
        }
        
        return newItem;
    }
    
    /**
     * Overrides the clone method from the interface Cloneable which makes
     * the object able to be cloned. This is used in the cloneAndPosition
     * method.
     * 
     * @return a clone of the item-object.
     * @throws CloneNotSupportedException 
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
