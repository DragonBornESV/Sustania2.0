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
            // LHitBoxevel Barrier
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
        , new ArrayList<>());
        
        //Setting up the NPC for the outside room
        //responses are shortend to r, responsesPoints are shortend to rp 
        //and dialog are shortend to d.
        //BUSDRIVER
        String[] rBusDriver1 = new String[]{
                "Car", "Bike", "Bus", "walk" };
        
        int[] rpBusDriver1 = new int[]{0,25,25,25};
        Say dBusDriver1 = new Say("Hello " + Game.name+ "!"
                +"\nMy name is Bob and i transport people around the city in my bus.\n How do you move around your city?"
                + "", rBusDriver1, rpBusDriver1);
        
        String[] rBusDriver2 = new String[]{
                "Nothing. it doesn't hurt anyone with a little air pollution", "I don´t know", "We could all carpool!", 
            "We could use the public transportation, ride our bikes or walk around in the city.\nIt would create less air pollution"};
        
        int[] rpBusDriver2 = new int[]{0,0,0,30};
        Say dBusDriver2 = new Say("We have a problem with to much air pollution in the city.\n What would do you think we should do about it?", rBusDriver2, rpBusDriver2);
        
        //We take all the dialog and use when calling the constructor of the NPC class. 
        NPC busDriverNpc = new NPC("The Bus driver",new Say[]{dBusDriver1,dBusDriver2},"Alright " 
                + Game.name + "!\nLet´s do that!","City Clean Air",30);
        
        streets.setNPC(busDriverNpc);
        
        //CRIMINAL   
        String[] rCriminal1 = new String[]{
                "Nothing", "I want to talk", "I want to ask for directions"};
        int[] rpCriminal1 = new int[]{0,25,15};
        Say dCriminal1 = new Say("What do you want?", rCriminal1, rpCriminal1);
        
        String[] rCriminal2 = new String[]{
                "Yes, i have", "No, i haven't"};
        int[] rpCriminal2 = new int[]{-10,-5};
        Say dCriminal2 = new Say("Listen " + Game.name + "!"
                +"\nHave you been at the police station and talked with Olivia?", rCriminal2, rpCriminal2);
                
        String[] rCriminal3 = new String[]{
                "Actually, i don't care what you do", "Because you don't get anything out of it. You will end up in prison anyway, why not now?", "Because you are better then that!", "I don't care. Teach me to be a criminal!"};
        int[] rpCriminal3 = new int[]{-10,40,30,20};
        Say dCriminal3 = new Say("I know that there are a arrest warrant out for me. I don't want to go to prison!\nTell me why i shouldn't do criminal acts?",rCriminal3, rpCriminal3);
        
        NPC criminalNpc = new NPC("the criminal",new Say[]{dCriminal1,dCriminal2,dCriminal3},"Alright " 
                + Game.name + "!\nI will turn myself in!","City Security",25);
        
        streets.setNPC(criminalNpc);
        
    }
    
    public void createTownHall(){
        townHall = new Room("Town Hall", 128, 194,
            new HitBox[]{
                //Walls
                new HitBox(0,0,256,44), new HitBox(0,187,96,22), new HitBox(160,187,96,22), new HitBox(-10,44,10,143), new HitBox(256,44,10,143)},
            new Door(new HitBox(96,209,64,10), streets),
            new ArrayList<>(Arrays.asList(new Item[] {World.axe.cloneAndPosition(100, 100), World.glassBottle.cloneAndPosition(200, 100)})));
        
        //MAYOR
        String[] rMayor = new String[]{
                "Yes, i do", "No, i don´t"};
        
        int[] rpMayor = new int[]{25,25};
        Say dMayor1 = new Say("Hello " + Game.name+ "!"+"\nI´m Mayor Mcclane and welcome to my city!\nSustainia doesn´t exceed our goal of creating a sustainiable city, so i need your help!"
                + "\n\nDo you know what sustainability means?", rMayor, rpMayor);
        
        String[] rMayor2 = new String[]{
                "Yes, i will", "No, i dont care"};
        
        int[] rpMayor2 = new int[]{30,0};
        Say dMayor2 = new Say("You have to walk around Sustainia and visit the different sights.\nTalk to the people that you meet and learn more about the city and sustainiability."
                + "\n\nDo you want to help me make Sustainia sustainiable?\nand learn more about sustainiability?", rMayor2, rpMayor2);
        
        String[] rMayor3 = new String[]{
                "Ok..."};
        
        int[] rpMayor3 = new int[]{0};
        Say dMayor3 = new Say("Then you are of no use for our city. Goodbye!", rMayor3, rpMayor3);
        
        NPC mayorNpc = new NPC("The Mayor",new Say[]{dMayor1,dMayor2,dMayor3},"Alright " 
                + Game.name + "!\nLet´s get started!","City Security",30);
        
        townHall.setNPC(mayorNpc);
    }
        
    public void createNonsustainableHouse(){
        nonsustainableHouse = new Room("NSH", 240, 115,
            new HitBox[]{
                // Walls
                new HitBox(0,0,224,44), new HitBox(224,0,32,104), new HitBox(224,148,32,61), new HitBox(-10,44,10,165), new HitBox(0,209,224,10)},
            new Door(new HitBox(256,104,10,44), streets)
        , new ArrayList<>());
        
        //HOUSE BUILDER
        String[] rhouseBuilder1 = new String[]{
                "Pressure-impregnated wood", "FSC wood", "Fire impregnated wood"};
        int[] rphouseBuilder1 = new int[]{15,20,15};
        Say dhouseBuilder1 = new Say("Hello " + Game.name+ "!"
                +"\nMy name is Hanna and i'm working on this house."
                +"My company and i are trying to build a sustainiable house but we need your help."
                +"\nWhat kind of wood would you use?", rhouseBuilder1, rphouseBuilder1);
        
        String[] rhouseBuilder2 = new String[]{
                "Double-glazing windows", "Energi windows", "Regular windows"};
        int[] rphouseBuilder2 = new int[]{30,30,0};
        Say dhouseBuilder2 = new Say("We are gonna go with FSC wood, but what the windows!"
                + "\nWhat type of windows do we need?", rhouseBuilder2, rphouseBuilder2);
        
        NPC houseBuilderNpc = new NPC("the factory worker",new Say[]{dhouseBuilder1,dhouseBuilder2},"Alright " 
                + Game.name + "!\nWe will do that!","Sustainiable Housing",50,"City Equality",30);
                
        nonsustainableHouse.setNPC(houseBuilderNpc);
        
    }
    
    public void createPark(){
        park = new Room("Park", 240, 115,
            new HitBox[]{
                // Walls
                new HitBox(0,0,224,44), new HitBox(224,0,32,104), new HitBox(224,148,32,61), new HitBox(-10,44,10,165), new HitBox(0,209,224,10)},
            new Door(new HitBox(256,104,10,44), streets)
        , new ArrayList<>());
        
         //TRASH CHILD
        String[] rTrashChild1 = new String[]{
                "Stop doing that!", "What are you doing?!","Sup dude"};
        int[] rpTrashChild1 = new int[]{20,25,25};
        Say dTrashChild1 = new Say("Hello " + Game.name+ "!"
                +"\nSup dude I'm Chad!"
                +"\n'Throws trash at trashcan but misses'", rTrashChild1, rpTrashChild1);
        
        String[] rTrashChild2 = new String[]{
                "No, i don't want to because it's a stupid game", "Sure, why not!"};
        int[] rpTrashChild2 = new int[]{30,0};
        Say dTrashChild2 = new Say("I'm just having some fun, don't be a buzzkill."
                + "\nDo you wanna join me?", rTrashChild2, rpTrashChild2);
        
        NPC trashChildNpc = new NPC("the child",new Say[]{dTrashChild1,dTrashChild2},"Alright " 
                + Game.name + "!\nI'll clean it up.","City Cleanliness",30);
        
        park.setNPC(trashChildNpc);
    }
    
    public void createBank(){
        bank = new Room("Bank", 16, 115,
            new HitBox[]{
                // Walls
                new HitBox(32,0,224,44), new HitBox(0,0,32,104), new HitBox(0,148,32,61), new HitBox(256,44,10,165), new HitBox(32,209,224,10)},
            new Door(new HitBox(-10,104,10,44), streets)
        , new ArrayList<>());
        
        // BANK MANAGER
        String[] rBankManager1 = new String[]{
                "Yes, i will help you", "No, i don't"};
        int[] rpBankManager1 = new int[]{30,0};
        Say dBankManager1 = new Say("Hello " + Game.name+ "!"
                +"\nMy name is Bryan and i'm the bank manager of Sustainia Central Bank.\nWe had a terrible break-in a week ago. They trashed the entire bank. We are working on getting everything replaced and rebuilding."
                + "\nWill you help me get rid of all the borken items?", rBankManager1, rpBankManager1);
        
        String[] rBankManager2 = new String[]{
                "I will do that", "I think that i will move on", "Oh, i'm good. I don't need to search", "I have already searched the bank"};
        int[] rpBankManager2 = new int[]{25,15,0,50};
        Say dBankManager2 = new Say("Okay, you can search the bank and look for items.", rBankManager2, rpBankManager2);
        
        NPC bankManagerNpc = new NPC("the bank manager",new Say[]{dBankManager1,dBankManager2},"Alright " 
                + Game.name + "!\nGo search and look around!","City Cleanliness",30);
        
        bank.setNPC(bankManagerNpc);
    }
    
    public void createClothingFactory(){
        clothingFactory = new Room("Colothing Factory", 16, 115,
            new HitBox[]{
                // Walls
                new HitBox(32,0,224,44), new HitBox(0,0,32,104), new HitBox(0,148,32,61), new HitBox(256,44,10,165), new HitBox(32,209,224,10)},
            new Door(new HitBox(-10,104,10,44), streets)
        , new ArrayList<>());
        
        //FACTORY WORKER
        String[] rFactoryWorker1 = new String[]{
                "Don't have the lights on", "How about changing to LED bulbs", "Turn off all the machines and do everything by hand"};
        int[] rpFactoryWorker1 = new int[]{5,15,0};
        Say dFactoryWorker1 = new Say("Hello " + Game.name+ "!"
                +"\nMy name is Fiona and i'm the owner of this clohting factory."
                +"\nI really want my factory to use less power."
                +"\nWhat would you suggest that we do?", rFactoryWorker1, rpFactoryWorker1);
        
        String[] rFactoryWorker2 = new String[]{
                "You could use second-hand clothing and make into new clothing", "I don't know what to do!", "You could make eco-labeled clothing"};
        int[] rpFactoryWorker2 = new int[]{20,0,30};
        Say dFactoryWorker2 = new Say("I really wanna create a great factory but the water bill is to much."
                +"\nDo you have any idea as to how we can reduce it?", rFactoryWorker2, rpFactoryWorker2);
        
        String[] rFactoryWorker3 = new String[]{
                "No, i don't", "Eco-labeled clothing doesn't contain chemicals, dye or heavy metals which can harm the environment and also people",
                "Because it sounds better","Because it's in greater demand"};
        int[] rpFactoryWorker3 = new int[]{0,30,-10,10};
        Say dFactoryWorker3 = new Say("We are going to make eco-labeled clothing is actually a good idea."
                +"\nDo you know why?", rFactoryWorker3, rpFactoryWorker3);
        
        NPC factoryWorkerNpc = new NPC("the factory worker",new Say[]{dFactoryWorker1,dFactoryWorker2,dFactoryWorker3},"Alright " 
                + Game.name + "!\nI will use your advice, thank you!","City Green Energy",30,"City Clean Water", 30,"City Clean Air",30);
        
        clothingFactory.setNPC(factoryWorkerNpc);
    }

    public void createPoliceStation(){
        policeStation = new Room("Police Station", 128, 55,
            new HitBox[]{
                // Walls
                new HitBox(0,22,96,44), new HitBox(96,0,16,44), new HitBox(144,0,16,44), new HitBox(160,22,96,44), new HitBox(-10,66,10,143), new HitBox(256,66,10,143), new HitBox(0,209,256,10)},
            new Door(new HitBox(112,0,32,44), streets)
        , new ArrayList<>());
        
         //OFFICER      
        String[] rOfficer1 = new String[]{
                "Yes, of course ", "No, i don't think so", "I can try"};
        int[] rpOfficer1 = new int[]{25,0,25};
        Say dOfficer1 = new Say("Hello " + Game.name+ "!"
                +"\nMy name is Olivia and i'm an officer. My job is to make sure that everyone in Sustainia are safe.\nIn Sustainia, we have a problem with crime and criminalactivity.\nDo you think that you can help me?", rOfficer1, rpOfficer1);
        
        String[] rOfficer2 = new String[]{
                "No, i don't want to help you ", "I can try", "Of course, i'll help you"};
        int[] rpOfficer2 = new int[]{0,30,30};
        Say dOfficer2 = new Say("Alright, listen!\nThere are a arrest warrant out for this criminal named Cato.\nI'm really busy with looking out for Sustainia, would you help me out with looking for Cato?\nFind him and talk to him!", rOfficer2, rpOfficer2);
        
        NPC officerNpc = new NPC("the officer",new Say[]{dOfficer1,dOfficer2},"Alright " 
                + Game.name + "!\nI trust that you will help me","City Security",25);
        
        policeStation.setNPC(officerNpc);
    }
    
    public void createRecyclingStation(){
        recyclingStation = new Room("Recycling Station", 128, 44,
            new HitBox[]{
                //Walls
                new HitBox(0,0,112,44), new HitBox(144,0,112,44), new HitBox(-6,44,10,161), new HitBox(252,44,10,161), new HitBox(0,205,256,10)},
            new Door(new HitBox(112,0,32,44), streets)
        , new ArrayList<>());
        
                //SANITATION WORKER - info
        String[] rSanitationWorker1 = new String[]{"Ok"};
        int[] rpSanitationWorker1 = new int[]{50};
        Say dSanitationWorker1 = new Say("Hello " + Game.name+ "!"
                +"\nMy name is Steve and welcome to the recycling station of Sustainia!"
                +"\nYou can walk around Sustainia and collect items in the different buildings"
                +"\nand theen bring them back to the recycling station."
                +"\nYou can then salvage the items and get points which will help make Sustainia sustainable", rSanitationWorker1, rpSanitationWorker1);
        
        NPC sanitationWorkerNpc = new NPC("the sanitation worker",new Say[]{dSanitationWorker1},"Alright " 
                + Game.name + "!\nLet´s get started!");
        
        recyclingStation.setNPC(sanitationWorkerNpc);
    }
    
    public void createSchool(){
        school = new Room("School", 128, 55,
            new HitBox[]{
                // Walls
                new HitBox(0,22,96,44), new HitBox(96,0,16,44), new HitBox(144,0,16,44), new HitBox(160,22,96,44), new HitBox(-10,66,10,143), new HitBox(256,66,10,143), new HitBox(0,209,256,10)},
            new Door(new HitBox(112,0,32,44), streets)
        , new ArrayList<>());
        
                //TEACHER
        String[] rTeacher1 = new String[]{
                "Bad pay", "Lack of respect", "Men are better end women therefore they don't need to work in a woman field", "Men don't want to work with children"};
        int[] rpTeacher1 = new int[]{20,20,-10,-10};
        Say dTeacher1 = new Say("Hello " + Game.name+ "!"
                +"\nMy name is Tiffany and i teach at the school of Sustainia.\nThis last week the entire school worked with the FN goals for the world and a student pointed out that there are fewer male teachers then female teachers."
                + "\nWhy do you think that is?", rTeacher1, rpTeacher1);
        
        String[] rTeacher2 = new String[]{
                "Stop, looking down on men who want to became teachers", "Why do anything about it. I think it is a good distribution", "Better pay", "Talk more about it"};
        int[] rpTeacher2 = new int[]{50,-10,30,25};
        Say dTeacher2 = new Say("What do you think we could do to make it equal between men and women teachers?", rTeacher2, rpTeacher2);
        
        NPC teacherNpc = new NPC("the teacher",new Say[]{dTeacher1,dTeacher2},"Alright " 
                + Game.name + "!\nLet´s try that!","City Equality",50);
        
        school.setNPC(teacherNpc);
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
            //System.out.println(inv); //For debugging
            
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