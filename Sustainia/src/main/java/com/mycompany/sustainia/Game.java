package com.mycompany.sustainia;

import java.util.ArrayList;

public class Game {
    
    private Inventory inv;
        
    public static String name;
    Room currentRoom;
    Room streets, townHall, nonsustainableHouse, policeStation, bank, 
            clothingFactory, school, park, recyclingStation;
    
    //This is true, when the static graphics needs to be updated
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
        ArrayList<Item> itemsInTownHall = new ArrayList<>();
        Item item1 = new Item("Aluminum can", World.aluminumCanMaterialArray, 0);
        item1.setPosition(128, 200);
        Item item2 = new Item("Aluminum can", World.aluminumCanMaterialArray, 9);
        item2.setPosition(400, 300);
        itemsInTownHall.add(item1);
        itemsInTownHall.add(item2);
        
        townHall.getItemsInRoom().addAll(itemsInTownHall);
        streets.getItemsInRoom().addAll(itemsInTownHall);
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
        
    /*    
        int[] mayorItems = new int[]{0,0,0,0,2,0,0,0,0,0};
        int[] houseItems = new int[]{5,1,4,40,1,5,10,40,100,4};
        int[] policeItems = new int[]{0,1,0,5,8,0,0,0,50,40};
        int[] bankItems = new int[]{0,0,0,0,10,0,0,0,30,0};
        int[] factoryItems = new int[]{0,0,70,100,5,0,0,0,0,0};
        int[] waterPlantItems = new int[]{0,0,0,0,3,0,0,0,10,0};
        int[] schoolItems = new int[]{0,2,0,0,50,0,10,60,120,0};
        int[] supermarketItems = new int[]{100,10,30,90,9,50,100,0,200,40};
        int[] storeItems = new int[]{0,0,10,300,5,10,0,0,0,0};
        int[] buildingItems = new int[]{100,5,0,10,0,0,30,20,40,20};
        int[] parkItems = new int[]{0,0,0,0,0,0,25,70,50,0};
        int[] hospitalItems = new int[]{0,0,0,50,30,20,0,10,60,0};
    
    */
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
        for (int i = 0; i < currentRoom.getItemsInRoom().size(); i++) {
            currentRoom.getItemsInRoom().get(i).getHitBox().collisionWithObject(x, y);
            
            //Checks if the player hit the item
            if (currentRoom.getItemsInRoom().get(i).getHitBox().checkIfTriggered()) {
                
                System.out.println("Ramte item!");
                pickUpItem(currentRoom.getItemsInRoom().get(i));
            }
        }
    }
    
    public void pickUpItem(Item item) {
        if (item != null) {
            
            inv.getItemsInInventory().add(item);
            currentRoom.getItemsInRoom().remove(item);
            System.out.println(inv.getItemsInInventory());
            
            needsUpdate = true;
        } else {
            System.out.println("No item selected");
        }
        
    }
    
    public void dropItem(Item item) {
        if (item != null) {
            currentRoom.getItemsInRoom().add(item);
            inv.getItemsInInventory().remove(item);
            
            needsUpdate = true;
        } else {
            System.out.println("No item selected");
        }
        
    }
    
    public boolean needsUpdate() {
        return needsUpdate;
    }
    
    public void setNeedsUpdate(boolean value) {
        needsUpdate = value;
    }
    
    public void play(){
        createRooms();
    }
        
}
