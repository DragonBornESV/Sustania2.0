package com.mycompany.sustainia;

public class NPC {
    HitBox NPC;
    Room currentRoom;
    private final Say[] dialog;
    private int persuasionValue = 0;
    private final int persuasionTrigger = 50;
    private final String endTriggerMessage;
    private boolean pointsGiven = false;
    private boolean dialogRunning = false;
    
    private String parameterName;   //The name of the parameter this NPC effects.
    private int points;             //The points the parameter change with.
    
    private final String npcName;

    private int npcX;
    private int npcY;
    private HitBox hb = new HitBox(getNpcX(), getNpcY(),32,32);
    
    private String allText = "";
    
    int i;
    
    public NPC (String npcName, HitBox hitbox, Room currentRoom, Say[] dialog, String endTriggerMessage){
        this.npcName = npcName;
        this.NPC = hitbox;
        this.currentRoom = currentRoom;
        this.dialog = dialog;
        this.endTriggerMessage = endTriggerMessage;
    }
    
    public NPC (String npcName, int npcX, int npcY, String endTriggerMessage, Say[] dialog){
        this.npcName = npcName;
        this.npcX = npcX;
        this.npcY = npcY;
        this.endTriggerMessage = endTriggerMessage;
        this.dialog = dialog;
    }
    
    String getNpcName() {
        return this.npcName;
    }

     
    public NPC (String npcName, Say[] dialog, String endTriggerMessage, String parameterName, int points) {
        this.npcName = npcName;
        this.dialog = dialog;
        this.endTriggerMessage = endTriggerMessage;
        this.parameterName = parameterName;
        this.points = points; 
    } 
       
    
    public NPC (String npcName, Say[] dialog, String endTriggerMessage) {
        this.npcName = npcName;
        this.dialog = dialog;
        this.endTriggerMessage = endTriggerMessage;
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
    
    
    public Say getCurrentSay() {
        return dialog[i];
    }
    
    public String getAllText() {
        return allText;
    }
    
    
    public void runDialog(String npcName) {
        dialogRunning = true;
        
        //Iterates through the Say objects and runs the print method. The points 
        //are added as it goes along. 
        for (i = 0; i < dialog.length; i++) {
            persuasionValue += dialog[i].print(npcName, allText);
            
            //Checks if the player wants to leave the conversation
            if (dialog[i].isWantToLeave() == true) {
                return;
            }
            
            //Checks if the limit value is reached. Breaks if true.
            if (persuasionValue >= persuasionTrigger) {
                break;
            }
        }
        
        //Checks if the player succeeded. If not a fail message is printed.
        if (persuasionValue < persuasionTrigger) {
            System.out.println("");
            System.out.println("You failed to convince " + npcName + "...");
            System.out.println("Talk to the person again. "
                    + "Try to be more convincing this time...");
            System.out.println("");
            return;
        }
        
        //The succes message is printed out!
        //But only if they haven't given points yet and a parameter name is specified
        if (!pointsGiven && parameterName != null) {
            Parameter.mapAddScore(parameterName, points);
            pointsGiven = true;     //After this the player can't get anymore points from this npc.
            System.out.println("You've gained " + points + "% in '" + parameterName + "'!");
        }
        
        System.out.println("");
        System.out.println("---------------------------------");
        System.out.println("");
        System.out.println(endTriggerMessage);
        System.out.println("");
        
        dialogRunning = false;
    }
    
    public boolean isDialogRunning (){
        return dialogRunning;
    }
}