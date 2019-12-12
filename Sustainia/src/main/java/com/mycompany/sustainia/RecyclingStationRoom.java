/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sustainia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 */
public class RecyclingStationRoom extends Room {
    
    private HitBox containerHitBox;
    private int lengthOfActualHitBoxesInRoom; //Used to remember the first length of the array
    
    public RecyclingStationRoom (String name, int spawnPX, int spawnPY, HitBox[] hitboxesInRoom, Door door, ArrayList<Item> itemsInRoom, HitBox container) {
        
        //Calls the constructor for the Room class
        super(name, spawnPX, spawnPY, hitboxesInRoom, door, itemsInRoom);
        
        lengthOfActualHitBoxesInRoom = hitboxesInRoom.length;
        
        setContainerHitBox(container);
    }
    
    public void setContainerHitBox(HitBox hb) {
        //Assigns the variable
        containerHitBox = hb;
        
        //Adds the container as a hitbox you can't go through using a List.
        ArrayList<HitBox> newHitBoxArray = new ArrayList<>();
        newHitBoxArray.addAll(Arrays.asList(hitboxesInRoom));
        newHitBoxArray.add(hb);
        
        //Assigns the new List as the new Array of HitBoxes.
        //We enter the array we want to be used as an argument to the toArray-method
        //Thats why we use the previous length as the new length of the array...
        
        //That means one extra space in the array for the container-hitbox.
        hitboxesInRoom = newHitBoxArray.toArray(new HitBox[lengthOfActualHitBoxesInRoom]); 
    }
    
    public HitBox getContainerHitBox() {
        return containerHitBox;
    }
    
}
