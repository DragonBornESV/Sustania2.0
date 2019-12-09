package com.mycompany.sustainia;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    
    private Inventory inv;
        
    public static String name;
    Room currentRoom;
    Room streets, townHall, nonsustainableHouse, policeStation, bank, 
            clothingFactory, school, park, recyclingStation;
    
    //This is true, when the item graphics needs to be updated
    private boolean needsUpdate = false;
    
    public Game() 
    {
        Parameter.createParameters();
        inv = new Inventory();
    }


    private void createRooms(){
        
        /** Rooms are created and named.
         *  Rooms are assigned an intro which describes where the player are at.
         *  Rooms are also assigned an exit command.
         */
        
        streets = new Room("Streets", 432, 532, new HitBox[]{new HitBox(0,0,10,10)});
        townHall = new Room("Town Hall", 128, 209, 
                new HitBox[]{new HitBox(0,0,256*4,44*4), new HitBox(-10,44*4,10,165*4), new HitBox(0,187*4,96*4,22*4), new HitBox(160*4,187*4,96*4,22*4), new HitBox(256*4, 44*4, 10, 165*4)});
        Item[] itemsInTownHall = new Item[] {
            World.axe.cloneAndPosition(100, 300),
            World.glassBottle.cloneAndPosition(200, 300),
            World.ironCan.cloneAndPosition(300, 300),
            World.plasticBottle.cloneAndPosition(400, 300)
        };
        
        
        townHall.getItemsInRoom().addAll(Arrays.asList(itemsInTownHall));
        streets.getItemsInRoom().addAll(Arrays.asList(itemsInTownHall));
        nonsustainableHouse = new Room("NSH", 240, 104, new HitBox[]{new HitBox(0,0,10,10)});
        policeStation = new Room("Police Station", 16, 128, new HitBox[]{new HitBox(0,0,10,10)});
        bank = new Room("Bank", 16, 104, new HitBox[]{new HitBox(0,0,10,10)});
        clothingFactory = new Room("Clothing Factory", 16, 104, new HitBox[]{new HitBox(0,0,10,10)});
        school = new Room("School", 128, 32, new HitBox[]{new HitBox(0,0,10,10)});
        park = new Room("Park", 240, 104, new HitBox[]{new HitBox(0,0,10,10)});
        recyclingStation = new Room("Recycling Station", 16, 128, new HitBox[]{new HitBox(0,0,10,10)});

        currentRoom = townHall;

        HitBox testBox = new HitBox(546*4, 527*4, 32*4, 22*4);
        HitBox[] hitboxArray = {testBox};
        
    }
    
    
    public int collisionDetectionX(int dx){
        for (int i = 0; i < currentRoom.hitboxesInRoom.length; i++) {
            if (currentRoom.hitboxesInRoom[i].collisionLeft){
                dx = 1;
            }
            if (currentRoom.hitboxesInRoom[i].collisionRight){
                dx = -1;
            }
        }
        return dx;
    }
    
    public int collisionDetectionY(int dy){
        for (int i = 0; i < currentRoom.hitboxesInRoom.length; i++) {
            if (currentRoom.hitboxesInRoom[i].collisionTop){
                dy = 1;
            }
            if (currentRoom.hitboxesInRoom[i].collisionBottom){
                dy = -1;
            }
        }
        return dy;
    }
    
    public void collisionWithObjects(int x, int y){
        for (int i = 0; i < currentRoom.hitboxesInRoom.length; i++){
            currentRoom.hitboxesInRoom[i].collisionWithObject(x, y);
            // her tjekkes der for collision med NPC'er ved at kigge på <hitbox>.triggerd
        }
        
        // Checks if the items collide with the player. 
        for (int i = 0; i < currentRoom.getItemsInRoom().size(); i++) {
            currentRoom.getItemsInRoom().get(i).getHitBox().collisionWithObject(x, y);
            
            //Checks if the player hit the item
            if (currentRoom.getItemsInRoom().get(i).getHitBox().checkIfTriggered()) {
                
                System.out.println("Hit item");
                pickUpItem(currentRoom.getItemsInRoom().get(i));
            }
        }
    }
    
    /**
     * Picks up the item by moving it from the room to the inventory and requests
     * an update.
     * @param item 
     */
    public void pickUpItem(Item item) {
        if (item != null) {
            
            inv.getItemsInInventory().add(item);
            currentRoom.getItemsInRoom().remove(item);
            System.out.println(inv); //For debugging
            
            needsUpdate = true;
        } else {
            System.out.println("No item selected");
        }
        
    }
    
    /**
     * Drops the item by moving the item-object from the inventory to the room
     * and requests an update.
     * @param item 
     */
    public void dropItem(Item item) {
        if (item != null) {
            //SKAL LAVES OM UNDER MERGE
            item.setPosition(-World.gameX + World.gameScreenWidth/2 - 8*4, -World.gameY+World.gameScreenHeight/2 + 16*4);
            currentRoom.getItemsInRoom().add(item);
            inv.getItemsInInventory().remove(item);
            
            needsUpdate = true;
        } else {
            System.out.println("No item selected");
        }
        
    }
    
    /**
     * A way that the app-class can detect if it needs to update its items.
     * @return true if the item graphics needs to be updated 
     */
    public boolean needsUpdate() {
        return needsUpdate;
    }
    
    public void setNeedsUpdate(boolean value) {
        needsUpdate = value;
    }
    
    public Inventory getInventory() {
        return inv;
    }
    
    public void play(){
        createRooms();
    }
        
}
