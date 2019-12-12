package com.mycompany.sustainia;

/**This class keeps track of the dialog, how persuated the NPC is, where the
 * persuasion limit (trigger) lies and the final message that is shown when
 * reaching the persuasion limit.
 */

public class NPC {
    /**
     * @return the dialog
     */
    public Say[] getDialog() {
        return dialog;
    }

    private final Say[] dialog;
    private int persuasionValue = 0;

    public int getPersuasionTrigger() {
        return persuasionTrigger;
    }


    private final int persuasionTrigger = 50;   //The pointsvalue which the player has to react in order to persuad the NPC.


    /**
     * @return the EndtriggerMessage
     */
    public String getEndTriggerMessage() {
        return endTriggerMessage;
    }

    private final String endTriggerMessage; //The message showen when the NPC is persuaded.
    private boolean pointsGiven = false;    //Checks the points given when responding to the dialog.
    private boolean dialogRunning = false;  //Checks if the dialog is running.
    
    private String parameterName;   //The name of the parameter this NPC effects.
    private int points;             //The points the parameter change with.
    
    private final String npcName;

    private int npcX;
    private int npcY;
    private HitBox hb;
    
    private String allText = "";    //AllText contains all dialog.
    
    int i;
    
    /** The constructor for instantiating an instans of the NPC class.
     * 
     * @param npcY y-coordinate for the NPC
     * @param npcX x-coordinate for the NPC
     * @param npcName The name of the NPC.
     * @param dialog the instances of the Say class that dictates the content of the conversation.
     * @param endTriggerMessage the end message if the person is persuated.
     * @param parameterName the parameter you want to adjust.
     * @param points the points you want to adjust the parameter with.
     */
    
    public NPC (int npcX, int npcY, String npcName, Say[] dialog, String endTriggerMessage, String parameterName, int points){
        this.npcX = npcX;
        this.npcY = npcY;
        hb = new HitBox(npcX, npcY, 32, 32);
        this.npcName = npcName;
        this.dialog = dialog;
        this.endTriggerMessage = endTriggerMessage;
        this.parameterName = parameterName;
        this.points = points;
    }
    
    /**
     * @return the NPC name
     */
    String getNpcName() {
        return this.npcName;
    }

    /** The constructor for instantiating an instans of the NPC class.
     * 
     * @param npcName The name of the NPC.
     * @param dialog the instances of the Say class that dictates the content of the conversation.
     * @param endTriggerMessage the end message if the person is persuated.
     * @param parameterName the parameter you want to adjust.
     * @param points the points you want to adjust the parameter with.
     */ 
    public NPC (String npcName, Say[] dialog, String endTriggerMessage, String parameterName, int points) {
        this.npcName = npcName;
        this.dialog = dialog;
        this.endTriggerMessage = endTriggerMessage;
        this.parameterName = parameterName;
        this.points = points; 
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
    
    /** Places the NPC by using the coordinats from npcX and npcY.
     * 
     * @param npcX x-coordinate for the NPC.
     * @param npcY y-coordinate for the NPC.
     *
     */
    public void setPosition(int npcX, int npcY) {
        this.npcX = npcX;
        this.npcY = npcY;
        hb = new HitBox(npcX, npcY, 32, 32);
    }
    
    /**
     * @return the hitbox
     */
    public HitBox getHitBox() {
        return hb;
    }
    
    /**
     * @return the dialog
     */
    public Say getCurrentSay() {
        return dialog[i];
    }
    
    /**
     * @return allText 
     */
    public String getAllText() {
        return allText;
    }
    
    /**Initiates the dialog with the NPC.
     * 
     * The dialog is retrieved from the Say-array and runs through them, one by
     * one. Every iteration it returns the points given and adds them to the
     * persuasionValue attribute. It then checks if the limit (trigger) is
     * reached. If not and there is not anymore dialog, you get a fail message
     * and the dialog ends.
     */
    public void runDialog(String npcName) {
        dialogRunning = true;
        
        //Iterates through the Say objects and runs the print method. The points 
        //are added as it goes along. 
        for (i = 0; i < dialog.length; i++) {
            dialog[i].print(npcName, allText);
            while (getCurrentSay().getPoints() == 0){

            }

            persuasionValue += getCurrentSay().getPoints();


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

        givePoints();

        System.out.println("");
        System.out.println("---------------------------------");
        System.out.println("");
        System.out.println(endTriggerMessage);
        System.out.println("");
        
        dialogRunning = false;
    }

    public void givePoints() {
        //The succes message is printed out!
        //But only if they haven't given points yet and a parameter name is specified
        if (!pointsGiven && parameterName != null) {
            Parameter.mapAddScore(parameterName, points);
            pointsGiven = true;     //After this the player can't get anymore points from this npc.
            System.out.println("You've gained " + points + "% in '" + parameterName + "'!");
        }
    }

    
    /**
     * @return the dialogRunning
     */

    public boolean isDialogRunning (){
        return dialogRunning;
    }
}