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
    
    public NPC (String npcName, HitBox hitbox, Room currentRoom, Say[] dialog, String endTriggerMessage){
        this.npcName = npcName;
        this.NPC = hitbox;
        this.currentRoom = currentRoom;
        this.dialog = dialog;
        this.endTriggerMessage = endTriggerMessage;
    }
    
    String getNpcName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        
}