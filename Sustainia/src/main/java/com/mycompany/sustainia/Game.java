package com.mycompany.sustainia;

public class Game {
    boolean roomSwitch = true;

    int previousRoom;
    
    Room townHall, streets, nonsustainableHouse, policeStation, bank, clothingFactory, school, park, recyclingStation;
    
    Room currentRoom;
 // private Inventory inv;
        
    public static String name;
    
    public Game() 
    {
        Parameter.createParameters();
  //    inv = new Inventory();
    }
    
    
    public void createStreets(){
        streets = new Room("Streets", new int[][]{
            // Front of Town Hall
            {560,451},
            // Front of Non-Sustainable house
            {208,374},
            // Front of Park
            {208,506},
            // Front of Bank
            {912,374},
            // Front of Clothing Factory
            {912,506},
            // Front of police station
            {336,622},
            // Front of Recyclestation
            {560,600},
            // Front of School
            {784,622}
        },
        new HitBox[]{
            // Level Barrier
            new HitBox(0,298,1120,20), new HitBox(0,770,1120,10),
            // TownHall
            new HitBox(448,318,224,110), new HitBox(448,428,96,34), new HitBox(576,428,96,34),
            // Recyclestation
            new HitBox(480,614,64,24), new HitBox(576,614,64,24), new HitBox(480,638,160,110)
        },
 
            new HitBox[]{
                // Town Hall door
                new HitBox(550,410,20,30),
                // Non-Sustainable house door
                new HitBox(170,374,22,22),
                // Park door
                new HitBox(170,506,22,22),
                // Bank door
                new HitBox(928,374,22,22),
                // Clothing Factory door
                new HitBox(928,506,22,22),
                // Police Station door
                new HitBox(320,638,32,22),
                // Recycle Station door
                new HitBox(544,616,32,22),
                // School door
                new HitBox(768,638,32,22)
            }
        );
    }
    
    public void createTownHall(){
        townHall = new Room("Town Hall", 128, 194,
            new HitBox[]{
                //Walls
                new HitBox(0,0,256,44), new HitBox(0,187,96,22), new HitBox(160,187,96,22), new HitBox(-10,44,10,143), new HitBox(256,44,10,143)},
            new Door(new HitBox(96,209,64,10), streets)
        );
    }
        
    public void createNonsustainableHouse(){
        nonsustainableHouse = new Room("NSH", 240, 115,
            new HitBox[]{
                // Walls
                new HitBox(0,0,224,44), new HitBox(224,0,32,104), new HitBox(224,148,32,61), new HitBox(-10,44,10,165), new HitBox(0,209,224,10)},
            new Door(new HitBox(256,104,10,44), streets)
        );
    }
    
    public void createPark(){
        park = new Room("Park", 240, 115,
            new HitBox[]{
                // Walls
                new HitBox(0,0,224,44), new HitBox(224,0,32,104), new HitBox(224,148,32,61), new HitBox(-10,44,10,165), new HitBox(0,209,224,10)},
            new Door(new HitBox(256,104,10,44), streets)
        );
    }
    
    public void createBank(){
        bank = new Room("Bank", 16, 115,
            new HitBox[]{
                // Walls
                new HitBox(32,0,224,44), new HitBox(0,0,32,104), new HitBox(0,148,32,61), new HitBox(256,44,10,165), new HitBox(32,209,224,10)},
            new Door(new HitBox(-10,104,10,44), streets)
        );
    }
    
    public void createClothingFactory(){
        clothingFactory = new Room("Colothing Factory", 16, 115,
            new HitBox[]{
                // Walls
                new HitBox(32,0,224,44), new HitBox(0,0,32,104), new HitBox(0,148,32,61), new HitBox(256,44,10,165), new HitBox(32,209,224,10)},
            new Door(new HitBox(-10,104,10,44), streets)
        );
    }

    public void createPoliceStation(){
        policeStation = new Room("Police Station", 128, 55,
            new HitBox[]{
                // Walls
                new HitBox(0,22,96,44), new HitBox(96,0,16,44), new HitBox(144,0,16,44), new HitBox(160,22,96,44), new HitBox(-10,66,10,143), new HitBox(256,66,10,143), new HitBox(0,209,256,10)},
            new Door(new HitBox(112,0,32,44), streets)
        );
    }
    
    public void createRecyclingStation(){
        recyclingStation = new Room("Recycling Station", 128, 44,
            new HitBox[]{
                //Walls
                new HitBox(0,0,112,44), new HitBox(144,0,112,44), new HitBox(-6,44,10,161), new HitBox(252,44,10,161), new HitBox(0,205,256,10)},
            new Door(new HitBox(112,0,32,44), streets)
        );
    }
    
    public void createSchool(){
        school = new Room("School", 128, 55,
            new HitBox[]{
                // Walls
                new HitBox(0,22,96,44), new HitBox(96,0,16,44), new HitBox(144,0,16,44), new HitBox(160,22,96,44), new HitBox(-10,66,10,143), new HitBox(256,66,10,143), new HitBox(0,209,256,10)},
            new Door(new HitBox(112,0,32,44), streets)
        );
    }
    
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
            currentRoom = streets;
        }

        
        
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
    }
    
    
    public Room newRoom(int x, int y, Room room, Room roomStreets){
        System.out.println(room.name);
        if (room.equals(streets)){
            for (int i =0; i<streets.multipleDoors.length; i++){
            streets.multipleDoors[i].collisionWithObject(x, y);
                if (streets.multipleDoors[0].checkIfTriggered()){
                    room = townHall;
                    roomSwitch = true;
                } else if (streets.multipleDoors[1].checkIfTriggered()){
                    room = nonsustainableHouse;
                    roomSwitch = true;
                } else if (streets.multipleDoors[2].checkIfTriggered()){
                    room = park;
                    roomSwitch = true;
                } else if (streets.multipleDoors[3].checkIfTriggered()){
                    room = bank;
                    roomSwitch = true;
                } else if (streets.multipleDoors[4].checkIfTriggered()){
                    room = clothingFactory;
                    roomSwitch = true;
                } else if (streets.multipleDoors[5].checkIfTriggered()){
                    room = policeStation;
                    roomSwitch = true;
                } else if (streets.multipleDoors[6].checkIfTriggered()){
                    room = recyclingStation;
                    roomSwitch = true;
                } else if (streets.multipleDoors[7].checkIfTriggered()){
                    room = school;
                    roomSwitch = true;
                } else {
                    room = room;
                }
            }
        } else {
            room.door.doorFrame.collisionWithObject(x, y);
            //System.out.println(currentRoom.door.doorFrame.checkIfTriggered());
            if (room.door.doorFrame.checkIfTriggered()){
                room = room.door.leadsTo;
                roomSwitch = true;
            } else {
                room = room;
            }
        }
        return room;
    }
    
    
    public int getSpawnPointX(int x, Room room, int previousRoom){
        System.out.println(previousRoom);
        if (roomSwitch){
            if (room.equals(streets)){
                //x = -300 + 560*4;
                x = -World.characterX +streets.multipleSpawnPoints[previousRoom][0]*World.scale;
            } else {
                x = -World.characterX +room.spawnPX*World.scale;
            }
        } else {
            x = x;
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
        } else {
            y = y;
        }
        return y;
    }
        
}
