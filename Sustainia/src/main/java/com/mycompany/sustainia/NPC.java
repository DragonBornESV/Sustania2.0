package com.mycompany.sustainia;

class NPC {
    Room currentRoom;
    //private final Say[] dialog;
    private int persuasionValue = 0;
    private final int persuasionTrigger = 50;
    private final String endTriggerMessage;
    private boolean pointsGiven = false;
    
    private String parameterName;   //The name of the parameter this NPC effects.
    private int points;             //The points the parameter change with.
    
    private final String npcName;
    
    private int npcX;
    private int npcY;
    private HitBox hb = new HitBox(getNpcX(), getNpcY(),32,32);
    
    public NPC (String npcName, int npcX, int npcY, String endTriggerMessage){
        this.npcName = npcName;
        this.npcX = npcX;
        this.npcY = npcY;
        this.endTriggerMessage = endTriggerMessage;
    }
    
    String getNpcName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the npcX
     */
    public int getNpcX() {
        return npcX;
    }

    /**
     * @param npcX the npcX to set
     */
    public void setNpcX(int npcX) {
        this.npcX = npcX;
    }

    /**
     * @return the npcY
     */
    public int getNpcY() {
        return npcY;
    }

    /**
     * @param npcY the npcY to set
     */
    public void setNpcY(int npcY) {
        this.npcY = npcY;
    }

}

/**
 * //Setting up the NPC for the outside room
        //responses are shortend to r, responsesPoints are shortend to rp 
        //and dialog are shortend to d.
        //MAYOR
        String[] rMayor = new String[]{
                "Yes, i do", "No, i don´t"};
        
        int[] rpMayor = new int[]{25,25};
        Say dMayor1 = new Say("Hello " + Game.name+ "!"+"\nI´m Mayor Mcclane and welcome to my city!\nSustainia doesn´t exceed our goal of creating a sustainiable city, so i need your help!"
                + "\n\nDo you know what sustainability means?", rMayor, rpMayor, parser);
        
        String[] rMayor2 = new String[]{
                "Yes, i will", "No, i dont care"};
        
        int[] rpMayor2 = new int[]{30,0};
        Say dMayor2 = new Say("You have to walk around Sustainia and visit the different sights.\nTalk to the people that you meet and learn more about the city and sustainiability."
                + "\n\nDo you want to help me make Sustainia sustainiable?\nand learn more about sustainiability?", rMayor2, rpMayor2, parser);
        
        String[] rMayor3 = new String[]{
                "Ok..."};
        
        int[] rpMayor3 = new int[]{0};
        Say dMayor3 = new Say("Then you are of no use for our city. Goodbye!", rMayor3, rpMayor3, parser);
        
        //We take all the dialog and use when calling the constructor of the 
        //NPC class. 
        NPC mayorNpc = new NPC("The Mayor",new Say[]{dMayor1,dMayor2,dMayor3},"Alright " 
                + Game.name + "!\nLet´s get started!","City Security",30,"City Green Energy",10);
        
        //New NPC - Build in the same way as above^^   
        //BUSDRIVER
        String[] rBusDriver1 = new String[]{
                "Car", "Bike", "Bus", "walk" };
        
        int[] rpBusDriver1 = new int[]{0,25,25,25};
        Say dBusDriver1 = new Say("Hello " + Game.name+ "!"
                +"\nMy name is Bob and i transport people around the city in my bus.\n How do you move around your city?"
                + "", rBusDriver1, rpBusDriver1, parser);
        
        String[] rBusDriver2 = new String[]{
                "Nothing. it doesn't hurt anyone with a little air pollution", "I don´t know", "We could all carpool!", 
            "We could use the public transportation, ride our bikes or walk around in the city.\nIt would create less air pollution"};
        
        int[] rpBusDriver2 = new int[]{0,0,0,30};
        Say dBusDriver2 = new Say("We have a problem with to much air pollution in the city.\n What would do you think we should do about it?", rBusDriver2, rpBusDriver2, parser);
        
        NPC busDriverNpc = new NPC("The Bus driver",new Say[]{dBusDriver1,dBusDriver2},"Alright " 
                + Game.name + "!\nLet´s do that!","City Clean Air",30);
        
        //OFFICER      
        String[] rOfficer1 = new String[]{
                "Yes, of course ", "No, i don't think so", "I can try"};
        int[] rpOfficer1 = new int[]{25,0,25};
        Say dOfficer1 = new Say("Hello " + Game.name+ "!"
                +"\nMy name is Olivia and i'm an officer. My job is to make sure that everyone in Sustainia are safe.\nIn Sustainia, we have a problem with crime and criminalactivity.\nDo you think that you can help me?", rOfficer1, rpOfficer1, parser);
        
        String[] rOfficer2 = new String[]{
                "No, i don't want to help you ", "I can try", "Of course, i'll help you"};
        int[] rpOfficer2 = new int[]{0,30,30};
        Say dOfficer2 = new Say("Alright, listen!\nThere are a arrest warrant out for this criminal named Cato.\nI'm really busy with looking out for Sustainia, would you help me out with looking for Cato?\nFind him and talk to him!", rOfficer2, rpOfficer2, parser);
        
        NPC officerNpc = new NPC("the officer",new Say[]{dOfficer1,dOfficer2},"Alright " 
                + Game.name + "!\nI trust that you will help me","City Security",25);
        
        //CRIMINAL   
        String[] rCriminal1 = new String[]{
                "Nothing", "I want to talk", "I want to ask for directions"};
        int[] rpCriminal1 = new int[]{0,25,15};
        Say dCriminal1 = new Say("What do you want?", rCriminal1, rpCriminal1, parser);
        
        String[] rCriminal2 = new String[]{
                "Yes, i have", "No, i haven't"};
        int[] rpCriminal2 = new int[]{-10,-5};
        Say dCriminal2 = new Say("Listen " + Game.name + "!"
                +"\nHave you been at the police station and talked with Olivia?", rCriminal2, rpCriminal2, parser);
                
        String[] rCriminal3 = new String[]{
                "Actually, i don't care what you do", "Because you don't get anything out of it. You will end up in prison anyway, why not now?", "Because you are better then that!", "I don't care. Teach me to be a criminal!"};
        int[] rpCriminal3 = new int[]{-10,40,30,20};
        Say dCriminal3 = new Say("I know that there are a arrest warrant out for me. I don't want to go to prison!\nTell me why i shouldn't do criminal acts?",rCriminal3, rpCriminal3, parser);
        
        NPC criminalNpc = new NPC("the criminal",new Say[]{dCriminal1,dCriminal2,dCriminal3},"Alright " 
                + Game.name + "!\nI will turn myself in!","City Security",25);
        
        // BANK MANAGER
        String[] rBankManager1 = new String[]{
                "Yes, i will help you", "No, i don't"};
        int[] rpBankManager1 = new int[]{30,0};
        Say dBankManager1 = new Say("Hello " + Game.name+ "!"
                +"\nMy name is Bryan and i'm the bank manager of Sustainia Central Bank.\nWe had a terrible break-in a week ago. They trashed the entire bank. We are working on getting everything replaced and rebuilding."
                + "\nWill you help me get rid of all the borken items?", rBankManager1, rpBankManager1, parser);
        
        String[] rBankManager2 = new String[]{
                "I will do that", "I think that i will move on", "Oh, i'm good. I don't need to search", "I have already searched the bank"};
        int[] rpBankManager2 = new int[]{25,15,0,50};
        Say dBankManager2 = new Say("Okay, you can search the bank and look for items.", rBankManager2, rpBankManager2, parser);
        
        NPC bankManagerNpc = new NPC("the bank manager",new Say[]{dBankManager1,dBankManager2},"Alright " 
                + Game.name + "!\nGo search and look around!","City Cleanliness",30);
  
        //TEACHER
        String[] rTeacher1 = new String[]{
                "Bad pay", "Lack of respect", "Men are better end women therefore they don't need to work in a woman field", "Men don't want to work with children"};
        int[] rpTeacher1 = new int[]{20,20,-10,-10};
        Say dTeacher1 = new Say("Hello " + Game.name+ "!"
                +"\nMy name is Tiffany and i teach at the school of Sustainia.\nThis last week the entire school worked with the FN goals for the world and a student pointed out that there are fewer male teachers then female teachers."
                + "\nWhy do you think that is?", rTeacher1, rpTeacher1, parser);
        
        String[] rTeacher2 = new String[]{
                "Stop, looking down on men who want to became teachers", "Why do anything about it. I think it is a good distribution", "Better pay", "Talk more about it"};
        int[] rpTeacher2 = new int[]{50,-10,30,25};
        Say dTeacher2 = new Say("What do you think we could do to make it equal between men and women teachers?", rTeacher2, rpTeacher2, parser);
        
        NPC teacherNpc = new NPC("the teacher",new Say[]{dTeacher1,dTeacher2},"Alright " 
                + Game.name + "!\nLet´s try that!","City Equality",50);
        
        //TRASH CHILD
        String[] rTrashChild1 = new String[]{
                "Stop doing that!", "What are you doing?!","Sup dude"};
        int[] rpTrashChild1 = new int[]{20,25,25};
        Say dTrashChild1 = new Say("Hello " + Game.name+ "!"
                +"\nSup dude I'm Chad!"
                +"\n'Throws trash at trashcan but misses'", rTrashChild1, rpTrashChild1, parser);
        
        String[] rTrashChild2 = new String[]{
                "No, i don't want to because it's a stupid game", "Sure, why not!"};
        int[] rpTrashChild2 = new int[]{30,0};
        Say dTrashChild2 = new Say("I'm just having some fun, don't be a buzzkill."
                + "\nDo you wanna join me?", rTrashChild2, rpTrashChild2, parser);
        
        NPC trashChildNpc = new NPC("the child",new Say[]{dTrashChild1,dTrashChild2},"Alright " 
                + Game.name + "!\nI'll clean it up.","City Cleanliness",30);
        
        //FACTORY WORKER
        String[] rFactoryWorker1 = new String[]{
                "Don't have the lights on", "How about changing to LED bulbs", "Turn off all the machines and do everything by hand"};
        int[] rpFactoryWorker1 = new int[]{5,15,0};
        Say dFactoryWorker1 = new Say("Hello " + Game.name+ "!"
                +"\nMy name is Fiona and i'm the owner of this clohting factory."
                +"\nI really want my factory to use less power."
                +"\nWhat would you suggest that we do?", rFactoryWorker1, rpFactoryWorker1, parser);
        
        String[] rFactoryWorker2 = new String[]{
                "You could use second-hand clothing and make into new clothing", "I don't know what to do!", "You could make eco-labeled clothing"};
        int[] rpFactoryWorker2 = new int[]{20,0,30};
        Say dFactoryWorker2 = new Say("I really wanna create a great factory but the water bill is to much."
                +"\nDo you have any idea as to how we can reduce it?", rFactoryWorker2, rpFactoryWorker2, parser);
        
        String[] rFactoryWorker3 = new String[]{
                "No, i don't", "Eco-labeled clothing doesn't contain chemicals, dye or heavy metals which can harm the environment and also people",
                "Because it sounds better","Because it's in greater demand"};
        int[] rpFactoryWorker3 = new int[]{0,30,-10,10};
        Say dFactoryWorker3 = new Say("We are going to make eco-labeled clothing is actually a good idea."
                +"\nDo you know why?", rFactoryWorker3, rpFactoryWorker3, parser);
        
        NPC factoryWorkerNpc = new NPC("the factory worker",new Say[]{dFactoryWorker1,dFactoryWorker2,dFactoryWorker3},"Alright " 
                + Game.name + "!\nI will use your advice, thank you!","City Green Energy",30,"City Clean Water", 30,"City Clean Air",30);
        
        //HOUSE BUILDER
        String[] rhouseBuilder1 = new String[]{
                "Pressure-impregnated wood", "FSC wood", "Fire impregnated wood"};
        int[] rphouseBuilder1 = new int[]{15,20,15};
        Say dhouseBuilder1 = new Say("Hello " + Game.name+ "!"
                +"\nMy name is Hanna and i'm working on this house."
                +"My company and i are trying to build a sustainiable house but we need your help."
                +"\nWhat kind of wood would you use?", rhouseBuilder1, rphouseBuilder1, parser);
        
        String[] rhouseBuilder2 = new String[]{
                "Double-glazing windows", "Energi windows", "Regular windows"};
        int[] rphouseBuilder2 = new int[]{30,30,0};
        Say dhouseBuilder2 = new Say("We are gonna go with FSC wood, but what the windows!"
                + "\nWhat type of windows do we need?", rhouseBuilder2, rphouseBuilder2, parser);
        
        NPC houseBuilderNpc = new NPC("the factory worker",new Say[]{dhouseBuilder1,dhouseBuilder2},"Alright " 
                + Game.name + "!\nWe will do that!","Sustainiable Housing",50,"City Equality",30);
                
        //SANITATION WORKER - info
        String[] rSanitationWorker1 = new String[]{"Ok"};
        int[] rpSanitationWorker1 = new int[]{50};
        Say dSanitationWorker1 = new Say("Hello " + Game.name+ "!"
                +"\nMy name is Steve and welcome to the recycling station of Sustainia!"
                +"\nYou can walk around Sustainia and collect items in the different buildings"
                +"\nand theen bring them back to the recycling station."
                +"\nYou can then salvage the items and get points which will help make Sustainia sustainable", rSanitationWorker1, rpSanitationWorker1, parser);
        
        NPC sanitationWorkerNpc = new NPC("the sanitation worker",new Say[]{dSanitationWorker1},"Alright " 
                + Game.name + "!\nLet´s get started!");
 */