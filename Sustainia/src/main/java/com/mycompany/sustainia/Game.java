package com.mycompany.sustainia;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    boolean roomSwitch = true;  //Checks if the player switches room.

    int previousRoom;
    
    Room streets, townHall, nonsustainableHouse, policeStation, bank, 
            clothingFactory, school, park;  //The different rooms created.
    
    //The Recycling station 
    RecyclingStationRoom recyclingStation;
    
    Room currentRoom;   //Where the player is placed.
    private Inventory inv;
    

    public String playerName;


    
    //This is true, when the item graphics needs to be updated.
    private boolean needsUpdate = false;
    
    public Game(){
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
            new HitBox(0,298,1120,20), new HitBox(0,770,1120,10), new HitBox(1120,298,10,472), new HitBox(-10,298,10,472),
            // TownHall
            new HitBox(448,318,224,110), new HitBox(448,428,96,34), new HitBox(576,428,96,34),
            // NSH
            new HitBox(192,374,32,2), new HitBox(192,394,32,2), new HitBox(32,330,160,110),
            // Park
            new HitBox(0,528,213,57), new HitBox(0,440,213,66), new HitBox(0,506,192,22),
            // Bank
            new HitBox(912,374,16,2), new HitBox(912,394,16,2), new HitBox(928,330,160,110), new HitBox(896,407,32,33), new HitBox(896,308,32,55),
            // Clothing Factory
            new HitBox(896,526,32,2), new HitBox(896,506,32,2), new HitBox(928,462,160,110), new HitBox(896,528,32,88), new HitBox(896,440,32,66), new HitBox(928,583,192,33),
            // Police Station
            new HitBox(320,616,2,22), new HitBox(350,616,2,22), new HitBox(256,638,160,110), new HitBox(417,726,1,22),
            // Recyclestation
            new HitBox(480,614,64,24), new HitBox(576,614,64,24), new HitBox(480,638,160,110),
            // School
            new HitBox(768,616,2,22), new HitBox(798,616,2,22), new HitBox(704,638,160,55), new HitBox(704,693,96,55), new HitBox(672,616,2,154), new HitBox(879,705,2,42),
            // NPC
            new HitBox(638,446,19,25)
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
        , new ArrayList<>(Arrays.asList(new Item[] {
            World.glassBottle.cloneAndPosition(819, 341),
            World.glassBottle.cloneAndPosition(271, 445),
            World.aluminumCan.cloneAndPosition(647, 524),
            World.aluminumCan.cloneAndPosition(869, 400),
            World.tire.cloneAndPosition(368, 377),
            World.tire.cloneAndPosition(795, 437),
            World.ironCan.cloneAndPosition(281, 545),
            World.organicWaste.cloneAndPosition(274, 339),
            World.organicWaste.cloneAndPosition(394, 495),
            World.organicWaste.cloneAndPosition(481, 575),
            World.organicWaste.cloneAndPosition(551, 498),
            World.organicWaste.cloneAndPosition(746, 500),
            World.organicWaste.cloneAndPosition(840, 534),
            World.organicWaste.cloneAndPosition(713, 385)
        })));

        //Setting up the NPC for the outside room
        //responses are shortend to r, responsesPoints are shortend to rp
        //and dialog are shortend to d.
        //BUSDRIVER
        String[] rBusDriver1 = new String[]{
                "Car", "Bike", "Bus", "Walk" };

        int[] rpBusDriver1 = new int[]{0,25,25,25};
        Say dBusDriver1 = new Say("Hello " + this.playerName+ "!"
                +"\nMy name is Bob and i transport people around the city in my bus.\n How do you move around your city?"
                + "", rBusDriver1, rpBusDriver1);

        String[] rBusDriver2 = new String[]{
                "Nothing. it doesn't hurt anyone with\n"
                + "a little air pollution", "I don´t know", "We could all carpool!",
            "Use the public transportation, ride our\n"
                + "bikes or walk around in the city."};

        int[] rpBusDriver2 = new int[]{0,0,0,30};
        Say dBusDriver2 = new Say("We have a problem with to much air pollution in the city.\n What would do you think we should do about it?", rBusDriver2, rpBusDriver2);

        //We take all the dialog and use when calling the constructor of the NPC class.

        NPC busDriverNpc = new NPC(300, 600, //placement of the NPC
                "The Bus driver",new Say[]{dBusDriver1,dBusDriver2},"Alright "
                + this.playerName + "!\nLet´s do that!", //NPC name, dialog array and endMessage
                "City Clean Air",30); //The score the NPC adds to the parameter


        streets.setNPC(busDriverNpc);
    }
    
    public void createTownHall(){
        townHall = new Room("Town Hall", 128, 194,
            new HitBox[]{
                // Walls
                new HitBox(0,0,256,44), new HitBox(0,185,98,22), new HitBox(158,185,98,22), new HitBox(-8,44,10,143), new HitBox(254,44,10,143),
                // Desk
                new HitBox(80,83,32,44), new HitBox(144,83,32,44), new HitBox(112,105,32,22),
                // Book shelf
                new HitBox(2,44,16,45), new HitBox(18,44,64,12), new HitBox(238,44,16,45), new HitBox(174,44,64,12),
                // NPC
                new HitBox(118,70,21,32)
                },
            new Door(new HitBox(96,209,64,10), streets),
        new ArrayList<>(Arrays.asList(new Item[] {
            World.glassBottle.cloneAndPosition(176, 112),
            World.computer.cloneAndPosition(62, 108)
        })));

        //MAYOR
        String[] rMayor = new String[]{
                "Yes, i do", "No, i don´t"};

        int[] rpMayor = new int[]{25,25};
        Say dMayor1 = new Say("Hello " + this.playerName+ "!"+"\nI´m Mayor Mcclane and welcome to my city!\nSustainia doesn´t exceed our goal of creating a sustainiable city, so i need your help!"
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

        NPC mayorNpc = new NPC(100, 100, //placement of the NPC
                "The Mayor", new Say[]{dMayor1,dMayor2,dMayor3},"Alright "

                + this.playerName + "!\nLet´s get started!", //NPC navn, dialog array og endMessage
                "City Security", 30); //Den score NPC giver efter succesfull samtale

        townHall.setNPC(mayorNpc);
    }
        
    public void createNonsustainableHouse(){
        nonsustainableHouse = new Room("NSH", 240, 115,
            new HitBox[]{
                // Walls
                new HitBox(0,0,222,45), new HitBox(0,45,2,164), new HitBox(222,146,34,63), new HitBox(222,0,34,105), new HitBox(2,0,220,45),
                // NPC
                new HitBox(69,88,19,29)
            },
            new Door(new HitBox(256,104,10,44), streets)
        , new ArrayList<>(Arrays.asList(new Item[] {
            World.axe.cloneAndPosition(142, 92),
            World.glassBottle.cloneAndPosition(200, 84),
            World.glassBottle.cloneAndPosition(159, 155),
            World.glassBottle.cloneAndPosition(199, 188),
            World.plasticBottle.cloneAndPosition(197, 65),
            World.glassBottle.cloneAndPosition(170, 188),
            World.cardboardBox.cloneAndPosition(196, 40),
            World.cardboardBox.cloneAndPosition(174, 37),
            World.cardboardBox.cloneAndPosition(4, 39),
            World.tire.cloneAndPosition(198, 74),
            World.tire.cloneAndPosition(12, 176)
        })));

        //HOUSE BUILDER
        String[] rhouseBuilder1 = new String[]{
                "Pressure impregnated wood", "FSC wood", "Fire impregnated wood"};
        int[] rphouseBuilder1 = new int[]{15,20,15};
        Say dhouseBuilder1 = new Say("Hello " + this.playerName+ "!"
                +"\nMy name is Hanna and i'm working on this house."
                +"My company and i are trying to build a sustainiable house but we need your help."
                +"\nWhat kind of wood would you use?", rhouseBuilder1, rphouseBuilder1);

        String[] rhouseBuilder2 = new String[]{
                "Double-glazing windows", "Energi windows", "Regular windows"};
        int[] rphouseBuilder2 = new int[]{30,30,0};
        Say dhouseBuilder2 = new Say("We are gonna go with FSC wood, but what the windows!"
                + "\nWhat type of windows do we need?", rhouseBuilder2, rphouseBuilder2);


        NPC houseBuilderNpc = new NPC(50, 25, //placement of the NPC
                "the house builder",new Say[]{dhouseBuilder1,dhouseBuilder2},"Alright "
                + this.playerName + "!\nWe will do that!", //NPC name, dialog array and endMessage
                "Sustainable Housing",50); //The score the NPC adds to the parameter

        nonsustainableHouse.setNPC(houseBuilderNpc);
    }
    
    public void createPark(){
        park = new Room("Park", 240, 115,
            new HitBox[]{
                // Walls
                new HitBox(0,0,21,209), new HitBox(20,0,215,57), new HitBox(230,148,26,61), new HitBox(230,0,26,101), new HitBox(0,209,256,10),
                // NPC
                new HitBox(57,44,19,25)
            },
            new Door(new HitBox(256,104,10,44), streets)
        , new ArrayList<>(Arrays.asList(new Item[] {
            World.organicWaste.cloneAndPosition(191, 165),
            World.organicWaste.cloneAndPosition(173, 99),
            World.organicWaste.cloneAndPosition(45, 84),
            World.organicWaste.cloneAndPosition(85, 128),
            World.organicWaste.cloneAndPosition(64, 168),
            World.aluminumCan.cloneAndPosition(152, 64),
            World.axe.cloneAndPosition(133, 123)
        })));

         //TRASH CHILD
        String[] rTrashChild1 = new String[]{
                "Stop doing that!", "What are you doing?!","Sup dude"};
        int[] rpTrashChild1 = new int[]{20,25,25};
        Say dTrashChild1 = new Say("Hello " + this.playerName+ "!"
                +"\nSup dude I'm Chad!"
                +"\n'Throws trash at trashcan but misses'", rTrashChild1, rpTrashChild1);

        String[] rTrashChild2 = new String[]{
                "No, i don't want to because it's a stupid game", "Sure, why not!"};
        int[] rpTrashChild2 = new int[]{30,0};
        Say dTrashChild2 = new Say("I'm just having some fun, don't be a buzzkill."
                + "\nDo you wanna join me?", rTrashChild2, rpTrashChild2);


        NPC trashChildNpc = new NPC(25, 50, //placement of the NPC
                "The Child Chad",new Say[]{dTrashChild1,dTrashChild2},"Alright "
                + this.playerName + "!\nI'll clean it up.", //NPC name, dialog array and endMessage
                "City Cleanliness",30 ); //The score the NPC adds to the parameter


        park.setNPC(trashChildNpc);
    }
    
    public void createBank(){
        bank = new Room("Bank", 16, 115,
            new HitBox[]{
                // Walls
                new HitBox(0,0,32,104), new HitBox(32,0,224,44), new HitBox(250,44,6,165), new HitBox(0,147,32,62), new HitBox(32,205,218,4),
                // Desk
                new HitBox(128,44,16,141),
                // NPC
                new HitBox(145,85,19,25)
            },
            new Door(new HitBox(-10,104,10,44), streets)
        , new ArrayList<>(Arrays.asList(new Item[] {
            World.plasticBottle.cloneAndPosition(146, 165),
            World.cardboardBox.cloneAndPosition(233, 36),
            World.computer.cloneAndPosition(226, 146)
        })));

        // BANK MANAGER
        String[] rBankManager1 = new String[]{
                "Yes, i will help you", "No, i don't"};
        int[] rpBankManager1 = new int[]{30,0};
        Say dBankManager1 = new Say("Hello " + this.playerName+ "!"
                +"\nMy name is Bryan and i'm the bank manager of Sustainia Central Bank.\nWe had a terrible break-in a week ago. They trashed the entire bank. We are working on getting everything replaced and rebuilding."
                + "\nWill you help me get rid of all the borken items?", rBankManager1, rpBankManager1);

        String[] rBankManager2 = new String[]{
                "I will do that", "I think that i will move on", "Oh, i'm good. I don't need to search", "I have already searched the bank"};
        int[] rpBankManager2 = new int[]{25,15,0,50};
        Say dBankManager2 = new Say("Okay, you can search the bank and look for items.", rBankManager2, rpBankManager2);


        NPC bankManagerNpc = new NPC(50, 50, //placement of the NPC
                "the bank manager",new Say[]{dBankManager1,dBankManager2},"Alright "
                + this.playerName + "!\nGo search and look around!", //NPC name, dialog array and endMessage
                "City Cleanliness",30); //The score the NPC adds to the parameter


        bank.setNPC(bankManagerNpc);
    }
    
    public void createClothingFactory(){
        clothingFactory = new Room("Colothing Factory", 16, 115,
            new HitBox[]{
                // Walls
                new HitBox(0,0,32,104), new HitBox(32,0,224,44), new HitBox(0,147,32,62), new HitBox(250,43,6,166), new HitBox(32,205,218,4),
                // Shelf
                new HitBox(32,44,96,10),
                // Tabels
                new HitBox(80,72,48,32), new HitBox(80,146,48,32), new HitBox(202,44,48,161),
                // NPc
                new HitBox(107,162,19,25)
            },
            new Door(new HitBox(-10,104,10,44), streets)
        , new ArrayList<>(Arrays.asList(new Item[] {
            World.cardboardBox.cloneAndPosition(134, 44),
            World.cardboardBox.cloneAndPosition(150, 44),
            World.cardboardBox.cloneAndPosition(166, 44),
            World.clothes.cloneAndPosition(180, 151),
            World.clothes.cloneAndPosition(180, 167),
        })));

        //FACTORY WORKER
        String[] rFactoryWorker1 = new String[]{
                "Don't have the lights on", "How about changing to LED bulbs", "Turn off all the machines and do everything by hand"};
        int[] rpFactoryWorker1 = new int[]{5,15,0};
        Say dFactoryWorker1 = new Say("Hello " + this.playerName+ "!"
                +"\nMy name is Fiona and i'm the owner of this clohting factory."
                +"\nI really want my factory to use less power."
                +"\nWhat would you suggest that we do?", rFactoryWorker1, rpFactoryWorker1);

        String[] rFactoryWorker2 = new String[]{
                "You could use second-hand clothing and\n"
                + "make into new clothing", "I don't know what to do!", "You could make eco-labeled clothing"};
        int[] rpFactoryWorker2 = new int[]{30,0,30};
        Say dFactoryWorker2 = new Say("I really wanna create a great factory but the water bill is to much."
                +"\nDo you have any idea as to how we can reduce it?", rFactoryWorker2, rpFactoryWorker2);

        String[] rFactoryWorker3 = new String[]{
                "No, i don't", "Eco-labeled clothing doesn't contain chemicals,\n"
                + "dye or heavy metals which can harm the \nenvironment and also people",
                "Because it sounds better","Because it's in greater demand"};
        int[] rpFactoryWorker3 = new int[]{0,30,10,10};
        Say dFactoryWorker3 = new Say("We are going to make eco-labeled clothing is actually a good idea."
                +"\nDo you know why?", rFactoryWorker3, rpFactoryWorker3);

        NPC factoryWorkerNpc = new NPC(25, 50, //placement of the NPC
                "the factory worker",new Say[]{dFactoryWorker1,dFactoryWorker2,dFactoryWorker3},"Alright "
                + this.playerName + "!\nI will use your advice, thank you!", //NPC name, dialog array and endMessage
                "City Green Energy",50); //The score the NPC adds to the parameter

        clothingFactory.setNPC(factoryWorkerNpc);
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
                new HitBox(26,134,60,17), new HitBox(110,134,60,17), new HitBox(194,134,60,17), new HitBox(26,168,60,17), new HitBox(110,168,60,17), new HitBox(194,168,60,17),
                // NPC WOmAN
                new HitBox(3,48,19,29),
                // NPC MAN
                new HitBox(201,168,79,25)
            },
            new Door(new HitBox(112,0,32,44), streets)
        , new ArrayList<>(Arrays.asList(new Item[] {
            World.cardboardBox.cloneAndPosition(35, 59),
            World.computer.cloneAndPosition(12, 130),
            World.computer.cloneAndPosition(12, 167),
            World.computer.cloneAndPosition(98, 130),
            World.computer.cloneAndPosition(98, 167),
            World.computer.cloneAndPosition(180, 130),
            World.computer.cloneAndPosition(180, 167)
        })));

         //OFFICER
        String[] rOfficer1 = new String[]{
                "Yes, of course ", "No, i don't think so", "I can try"};
        int[] rpOfficer1 = new int[]{25,0,25};
        Say dOfficer1 = new Say("Hello " + this.playerName+ "!"
                +"\nMy name is Olivia and i'm an officer. My job is to make sure that everyone in Sustainia are safe.\nIn Sustainia, we have a problem with crime and criminal activity.\nDo you think that you can help me?", rOfficer1, rpOfficer1);

        String[] rOfficer2 = new String[]{
                "No, i don't want to help you ", "I can try", "Of course, i'll help you"};
        int[] rpOfficer2 = new int[]{0,30,30};
        Say dOfficer2 = new Say("Alright, listen!\nThere are a lot of criminals arround so look out!\nIf you see something please report it to me.", rOfficer2, rpOfficer2);


        NPC officerNpc = new NPC(100, 50, //placement of the NPC
                "the officer",new Say[]{dOfficer1,dOfficer2},"Alright "
                + this.playerName + "!\nI trust that you will help me", //NPC name, dialog array and endMessage
                "City Security",50); //The score the NPC adds to the parameter

        policeStation.setNPC(officerNpc);
    }
    
    public void createRecyclingStation(){
        recyclingStation = new RecyclingStationRoom("Recycling Station", 128, 44,
            new HitBox[]{
                //Walls
                new HitBox(0,0,112,44), new HitBox(144,0,112,44), new HitBox(-6,44,10,161), new HitBox(252,44,10,161), new HitBox(0,205,256,10),
                // NPc
                new HitBox(37,60,19,25),
                // Non functional container
                new HitBox(156,79,96,32)
            },
            //Door
                new Door(new HitBox(112,0,32,44), streets),
            //Items
                new ArrayList<>(),
            //Container
                new HitBox(133,150,96,32));

                //SANITATION WORKER - info
        String[] rSanitationWorker1 = new String[]{"Ok"};
        int[] rpSanitationWorker1 = new int[]{55};
        Say dSanitationWorker1 = new Say("Hello " + this.playerName+ "!"
                +"\nMy name is Steve and welcome to the recycling station of Sustainia!"
                +"\nYou can walk around Sustainia and collect items in the different buildings"
                +"\nand theen bring them back to the recycling station."
                +"\nYou can then salvage the items and get points which will help make Sustainia sustainable", rSanitationWorker1, rpSanitationWorker1);

        NPC sanitationWorkerNpc = new NPC(50, 100, //placement of the NPC
                "the sanitation worker", new Say[]{dSanitationWorker1},"Alright "
                + this.playerName + "!\nLet´s get started!\nYou can press 'b' and go to the container to recycle you items!", //NPC name, dialog array and endMessage
                "City Cleanliness", 30); //The score the NPC adds to the parameter


        recyclingStation.setNPC(sanitationWorkerNpc);
    }
    
    public void createSchool(){
        school = new Room("School", 128, 55,
            new HitBox[]{
                // Walls
                new HitBox(0,0,98,69), new HitBox(98,0,14,45), new HitBox(144,0,14,45), new HitBox(158,0,98,69), new HitBox(0,69,2,140), new HitBox(254,69,2,67), new HitBox(158,136,98,73), new HitBox(2,207,156,2),
                // Desk and tabels
                new HitBox(11,83,50,17), new HitBox(2,112,68,17), new HitBox(2,142,68,17), new HitBox(2,172,68,17),
                // Book shelf
                new HitBox(174,69,64,10), new HitBox(238,69,16,67),
                // NPC
                new HitBox(23,54,19,28)
            },
            new Door(new HitBox(112,0,32,44), streets)
        , new ArrayList<>(Arrays.asList(new Item[] {
            World.aluminumCan.cloneAndPosition(211, 90),
            World.ironCan.cloneAndPosition(168, 113),
            World.glassBottle.cloneAndPosition(95, 172)
        })));
        
                //TEACHER
        String[] rTeacher1 = new String[]{
                "Bad pay", "Lack of respect", "Men are better end women therefore\nthey don't need to work in a woman field", "Men don't want to work with children"};
        int[] rpTeacher1 = new int[]{20,20,-10,-10};
        Say dTeacher1 = new Say("Hello " + this.playerName+ "!"
                +"\nMy name is Tiffany and i teach at the school of Sustainia.\nThis last week the entire school worked with the FN goals for the world and a student\n"
                + "pointed out that there are fewer male teachers then female teachers."
                + "\nWhy do you think that is?", rTeacher1, rpTeacher1);
        
        String[] rTeacher2 = new String[]{
                "Stop, looking down on men who want to \nbecame teachers", "Why do anything about it.\nI think it is a good distribution", "Better pay", "Talk more about it"};
        int[] rpTeacher2 = new int[]{50,-10,30,25};
        Say dTeacher2 = new Say("What do you think we could do to make it equal between men and women teachers?", rTeacher2, rpTeacher2);
        

        NPC teacherNpc = new NPC(150, 100, //placement of the NPC
                "the teacher",new Say[]{dTeacher1,dTeacher2},"Alright " 
                + this.playerName + "!\nLet´s try that!", //NPC name, dialog array and endMessage
                "City Equality",50); //The score the NPC adds to the parameter

        
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

    // Return the x-value the player has to move so it doesn't collide.
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

    // Return the y-value the player has to move so it doesn't collide.
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
    
    //Checks for collision with hitboxes in rooms and items.
    public void collisionWithObjects(int x, int y, Room room){
        //Checks for collision with hitboxes like walls etc.
        for (int i = 0; i < room.hitboxesInRoom.length; i++){
            room.hitboxesInRoom[i].collisionWithObject(x, y);
        }
        
        // Checks if the container is hit in the recycling station room.
        if (recyclingStation.getContainerHitBox().checkIfTriggered()) {
            //Executes the recycleMaterials method.
            inv.recycleMaterials();
            setNeedsUpdate(true);
        }

        // Checks if the items collide with the player. 
        for (int i = 0; i < currentRoom.getItemsInRoom().size(); i++) {
            currentRoom.getItemsInRoom().get(i).getHitBox().collisionWithObject(x, y);
            
            //Checks if the player hit the item
            if (currentRoom.getItemsInRoom().get(i).getHitBox().checkIfTriggered()) {
                
                App.textBox.setTextBox("Picked up " + room.getItemsInRoom().get(i));
                pickUpItem(currentRoom.getItemsInRoom().get(i));

            }
        }
    }
    
    //
    public Room roomChangeCheck(int x, int y){
        
        //This is only for the streets room
        if (currentRoom.equals(streets)){
            // Checks for a collision with a door in streets
            for (int i =0; i <streets.multipleDoors.length; i++){
            streets.multipleDoors[i].collisionWithObject(x, y);
            //After this it looks for which door is triggered sets the room to the right one.
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
            //For all the other rooms
            currentRoom.door.doorFrame.collisionWithObject(x, y);
            
            //Checks if the door is triggered
            if (currentRoom.door.doorFrame.checkIfTriggered()){
                currentRoom = currentRoom.door.leadsTo;
                roomSwitch = true;
            }
        }
        
        //Return the new or the same room back.
        return currentRoom;
    }
    
    //Determines the x-coordinate for the player when he enters the new room
    public int getSpawnPointX(int x, Room room, int previousRoom){
        if (roomSwitch){
            if (room.equals(streets)){
                x = -World.characterX +streets.multipleSpawnPoints[previousRoom][0]*World.scale;
            } else {
                x = -World.characterX +room.spawnPX*World.scale;
            }
        }
        
        return x;
    }
    
    //Determines the y-coordinate for the player when he enters the new room
    public int getSpawnPointY(int y, Room room, int previousRoom){
        if (roomSwitch){
            if (room.equals(streets)){
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
            inv.updateValue();
            
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
            inv.updateValue();

            App.textBox.setTextBox(item+" was dropped");
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