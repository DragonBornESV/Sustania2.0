package com.mycompany.sustainia;

public class NPC {
    HitBox NPC;

    Room currentRoom;
    private final Say[] dialog;
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
    
    public NPC (String npcName, HitBox hitbox, Room currentRoom, Say[] dialog, String endTriggerMessage){
        this.npcName = npcName;
        this.NPC = hitbox;
        this.currentRoom = currentRoom;
        this.dialog = dialog;

	}
    
    public NPC (String npcName, int npcX, int npcY, String endTriggerMessage){
        this.npcName = npcName;
        this.npcX = npcX;
        this.npcY = npcY;
        this.endTriggerMessage = endTriggerMessage;
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
        
}