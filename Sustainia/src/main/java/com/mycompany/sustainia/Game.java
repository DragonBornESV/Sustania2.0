package com.mycompany.sustainia;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    boolean roomSwitch = true;

    int previousRoom;
    
    Room streets, townHall, nonsustainableHouse, policeStation, bank, 
            clothingFactory, school, park, recyclingStation;
    
    Room currentRoom;
    private Inventory inv;

    public static String name;
    
    //This is true, when the item graphics needs to be updated
    private boolean needsUpdate = false;
    
    public Game() 
    {
        Parameter.createParameters();
        inv = new Inventory();
    }
    
    
    public void createStreets(){
        streets = new Room("Streets", new int[][]{
            // Front of Town Hall
            {560,451},
            // Front of Non-Sustainable house
            {240,374},
            // Front of Park
            {240,506},
            // Front of Bank
            {896,374},
            // Front of Clothing Factory
            {880,506},
            // Front of police station
            {336,600},
            // Front of Recyclestation
            {560,600},
            // Front of School
            {784,600}
        },
        new HitBox[]{
            // LHitBoxevel Barrier
            new HitBox(0,298,1120,20), new HitBox(0,770,1120,10),
            // TownHall
            new HitBox(448,318,224,110), new HitBox(448,428,96,34), new HitBox(576,428,96,34),
            // NSH
            new HitBox(192,374,32,2), new HitBox(192,394,32,2), new HitBox(32,330,160,110),
            // Park
            new HitBox(0,528,213,57), new HitBox(0,440,213,66), new HitBox(0,506,192,22),
            // Bank
            new HitBox(912,374,16,2), new HitBox(912,394,16,2), new HitBox(928,330,160,110),
            // Clothing Factory
            new HitBox(896,526,32,2), new HitBox(896,506,32,2), new HitBox(928,462,160,110),
            // Police Station
            new HitBox(320,616,2,22), new HitBox(350,616,2,22), new HitBox(256,638,160,110), new HitBox(417,726,1,22),
            // Recyclestation
            new HitBox(480,614,64,24), new HitBox(576,614,64,24), new HitBox(480,638,160,110),
            // School
            new HitBox(768,616,2,22), new HitBox(798,616,2,22), new HitBox(704,638,160,55), new HitBox(704,693,96,55)
        },
 
            new HitBox[]{
                // Town Hall door
                new HitBox(550,410,20,30),
                // Non-Sustainable house door
                new HitBox(192,376,32,18),
                // Park door
                new HitBox(192,506,16,22),
                // Bank door
                new HitBox(912,376,16,18),
                // Clothing Factory door
                new HitBox(896,506,22,22),
                // Police Station door
                new HitBox(322,616,28,22),
                // Recycle Station door
                new HitBox(544,616,32,22),
                // School door
                new HitBox(770,616,28,22)
            }
        , new ArrayList<>());
    }
    
    public void createTownHall(){
        townHall = new Room("Town Hall", 128, 194,
            new HitBox[]{
                // Walls
                new HitBox(0,0,256,44), new HitBox(0,185,98,22), new HitBox(158,185,98,22), new HitBox(-8,44,10,143), new HitBox(254,44,10,143),
                // Desk
                new HitBox(80,83,32,44), new HitBox(144,83,32,44), new HitBox(112,105,32,22),
                // Book shelf
                new HitBox(2,44,16,45), new HitBox(18,44,64,12), new HitBox(238,44,16,45), new HitBox(174,44,64,12)
                },
            new Door(new HitBox(96,209,64,10), streets),
        new ArrayList<>(Arrays.asList(new Item[] {World.axe.cloneAndPosition(100, 100), World.glassBottle.cloneAndPosition(200, 100)})));
    }
        
    public void createNonsustainableHouse(){
        nonsustainableHouse = new Room("NSH", 240, 115,
            new HitBox[]{
                // Walls
                new HitBox(0,0,222,45), new HitBox(0,45,2,164), new HitBox(222,146,34,63), new HitBox(222,0,34,105), new HitBox(2,0,220,45)
            },
            new Door(new HitBox(256,104,10,44), streets)
        , new ArrayList<>());
    }
    
    public void createPark(){
        park = new Room("Park", 240, 115,
            new HitBox[]{
                // Walls
                new HitBox(0,0,21,209), new HitBox(20,0,215,57)
            },
            new Door(new HitBox(256,104,10,44), streets)
        , new ArrayList<>());
    }
    
    public void createBank(){
        bank = new Room("Bank", 16, 115,
            new HitBox[]{
                // Walls
                new HitBox(0,0,32,104), new HitBox(32,0,224,44), new HitBox(250,44,6,165), new HitBox(0,147,32,62), new HitBox(32,205,218,4),
                // Desk
                new HitBox(128,44,16,141)
            },
            new Door(new HitBox(-10,104,10,44), streets)
        , new ArrayList<>());
    }
    
    public void createClothingFactory(){
        clothingFactory = new Room("Colothing Factory", 16, 115,
            new HitBox[]{
                // Walls
                new HitBox(0,0,32,104), new HitBox(32,0,224,44), new HitBox(0,147,32,62), new HitBox(250,43,6,166), new HitBox(32,205,218,4),
                // Shelf
                new HitBox(32,44,96,10),
                // Tabels
                new HitBox(80,72,48,32), new HitBox(80,146,48,32), new HitBox(202,44,48,161)
            },
            new Door(new HitBox(-10,104,10,44), streets)
        , new ArrayList<>());
    }

    public void createPoliceStation(){
        policeStation = new Room("Police Station", 128, 55,
            new HitBox[]{
                // Walls
                new HitBox(0,0,98,67), new HitBox(98,0,14,45), new HitBox(144,0,14,45), new HitBox(158,0,98,67), new HitBox(0,67,2,142), new HitBox(254,67,2,142), new HitBox(2,207,252,2),
                // Locker
                new HitBox(188,67,66,10),
                // Desk
                new HitBox(26,100,228,17),
                // Tabels
                new HitBox(26,134,60,17), new HitBox(110,134,60,17), new HitBox(194,134,60,17), new HitBox(26,168,60,17), new HitBox(110,168,60,17), new HitBox(194,168,60,17)
                        },
            new Door(new HitBox(112,0,32,44), streets)
        , new ArrayList<>());
    }
    
    public void createRecyclingStation(){
        recyclingStation = new Room("Recycling Station", 128, 44,
            new HitBox[]{
                //Walls
                new HitBox(0,0,112,44), new HitBox(144,0,112,44), new HitBox(-6,44,10,161), new HitBox(252,44,10,161), new HitBox(0,205,256,10)},
            new Door(new HitBox(112,0,32,44), streets)
        , new ArrayList<>());
    }
    
    public void createSchool(){
        school = new Room("School", 128, 55,
            new HitBox[]{
                // Walls
                new HitBox(0,0,98,69), new HitBox(98,0,14,45), new HitBox(144,0,14,45), new HitBox(158,0,98,69), new HitBox(0,69,2,140), new HitBox(254,69,2,67), new HitBox(158,136,98,73), new HitBox(2,207,156,2),
                // Desk and tabels
                new HitBox(11,83,50,17), new HitBox(2,112,68,17), new HitBox(2,142,68,17), new HitBox(2,172,68,17),
                // Book shelf
                new HitBox(174,69,64,10), new HitBox(238,69,16,67)
            },
            new Door(new HitBox(112,0,32,44), streets)
        , new ArrayList<>());
    }
    
    /** Rooms are created and named.
     *  Rooms are assigned an intro which describes where the player are at.
     *  Rooms are also assigned an exit command.
     */
    public void createRooms(){
        createStreets();
        createTownHall();
        createNonsustainableHouse();
        createPark();
        createBank();
        createClothingFactory();
        createPoliceStation();
        createRecyclingStation();
        createSchool();
        currentRoom = townHall;
    }


    public int collisionDetectionX(int dx){
        for (int i = 0; i < currentRoom.hitboxesInRoom.length; i++) {
            if (currentRoom.hitboxesInRoom[i].collisionLeft){
            dx = -1;
            }
            if (currentRoom.hitboxesInRoom[i].collisionRight){
                dx = 1;
            }
        }
        return dx;
    }

    
    public int collisionDetectionY(int dy){
        for (int i = 0; i < currentRoom.hitboxesInRoom.length; i++) {
            if (currentRoom.hitboxesInRoom[i].collisionTop){
            dy = -1;
            }
            if (currentRoom.hitboxesInRoom[i].collisionBottom){
                dy = 1;
            }
        }
        return dy;
    }
    
    
    public void collisionWithObjects(int x, int y, Room room){
        for (int i = 0; i < room.hitboxesInRoom.length; i++){
            room.hitboxesInRoom[i].collisionWithObject(x, y);
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
    
    
    public Room roomChangeCheck(int x, int y){
        //System.out.println(currentRoom.name);
        if (currentRoom.equals(streets)){
            for (int i =0; i <streets.multipleDoors.length; i++){
            streets.multipleDoors[i].collisionWithObject(x, y);
                if (streets.multipleDoors[0].checkIfTriggered()){
                    currentRoom = townHall;
                    roomSwitch = true;
                } else if (streets.multipleDoors[1].checkIfTriggered()){
                    currentRoom = nonsustainableHouse;
                    roomSwitch = true;
                } else if (streets.multipleDoors[2].checkIfTriggered()){
                    currentRoom = park;
                    roomSwitch = true;
                } else if (streets.multipleDoors[3].checkIfTriggered()){
                    currentRoom = bank;
                    roomSwitch = true;
                } else if (streets.multipleDoors[4].checkIfTriggered()){
                    currentRoom = clothingFactory;
                    roomSwitch = true;
                } else if (streets.multipleDoors[5].checkIfTriggered()){
                    currentRoom = policeStation;
                    roomSwitch = true;
                } else if (streets.multipleDoors[6].checkIfTriggered()){
                    currentRoom = recyclingStation;
                    roomSwitch = true;
                } else if (streets.multipleDoors[7].checkIfTriggered()){
                    currentRoom = school;
                    roomSwitch = true;
                }
            }
        } else {
            currentRoom.door.doorFrame.collisionWithObject(x, y);
            //System.out.println(currentRoom.door.doorFrame.checkIfTriggered());
            if (currentRoom.door.doorFrame.checkIfTriggered()){
                currentRoom = currentRoom.door.leadsTo;
                roomSwitch = true;
            }
        }
        return currentRoom;
    }
    
    
    public int getSpawnPointX(int x, Room room, int previousRoom){
        //System.out.println(previousRoom);
        if (roomSwitch){
            if (room.equals(streets)){
                //x = -300 + 560*4;
                x = -World.characterX +streets.multipleSpawnPoints[previousRoom][0]*World.scale;
            } else {
                x = -World.characterX +room.spawnPX*World.scale;
            }
        }
        
        return x;
    }
    
    
    public int getSpawnPointY(int y, Room room, int previousRoom){
        if (roomSwitch){
            if (room.equals(streets)){
                //y = -300 +450*4;
                y = -World.characterY +streets.multipleSpawnPoints[previousRoom][1]*World.scale;
            } else {
                y = -World.characterY +room.spawnPY*World.scale;
            }
            roomSwitch = false;
        }
        
        return y;
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
            item.setPosition((World.gameX +World.characterX -8*World.scale)/World.scale, (World.gameY +World.characterY + 16*World.scale)/World.scale);
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
}