package com.mycompany.sustainia;

public class Game {
    
    Room streets = new Room("Streets", 432, 532, new HitBox[]{new HitBox(0,0,10,10)});
    Room townHall = new Room("Town Hall", 128, 209, 
            new HitBox[]{new HitBox(0,0,256*4,44*4), new HitBox(-10,44*4,10,165*4), new HitBox(0,187*4,96*4,22*4), new HitBox(160*4,187*4,96*4,22*4), new HitBox(256*4, 44*4, 10, 165*4)});
    Room nonsustainableHouse = new Room("NSH", 240, 104, new HitBox[]{new HitBox(0,0,10,10)});
    Room policeStation = new Room("Police Station", 16, 128, new HitBox[]{new HitBox(0,0,10,10)});
    Room bank = new Room("Bank", 16, 104, new HitBox[]{new HitBox(0,0,10,10)});
    Room clothingFactory = new Room("Colothing Factory", 16, 104, new HitBox[]{new HitBox(0,0,10,10)});
    Room school = new Room("School", 128, 32, new HitBox[]{new HitBox(0,0,10,10)});
    Room park = new Room("Park", 240, 104, new HitBox[]{new HitBox(0,0,10,10)});
    Room recyclingStation = new Room("Recycling Station", 16, 128, new HitBox[]{new HitBox(0,0,10,10)});
        
    Room currentRoom = townHall;
    
    HitBox testBox = new HitBox(546*4, 527*4, 32*4, 22*4);
    HitBox[] hitboxArray = {testBox};
    
 // private Inventory inv;
        
    public static String name;
    
    public Game() 
    {
        Parameter.createParameters();
  //    inv = new Inventory();
    }


    private void createRooms(){
        
        /** Rooms are created and named.
         *  Rooms are assigned an intro which describes where the player are at.
         *  Rooms are also assigned an exit command.
         */
        
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
            // her tjekkes der for collision med NPS'er ved at gikke på <hitbox>.triggerd
        }
    }
    
    
    public void play(){
        createRooms();
    }
        
}
