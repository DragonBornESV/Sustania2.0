package com.mycompany.sustainia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private NPC npc;
    private ArrayList<Item> itemsInRoom = new ArrayList<>();
    
    String name;
    int spawnPX;
    int spawnPY;
    HitBox[] hitboxesInRoom;
            
    public Room(String name, int spawnPX, int spawnPY, HitBox[] hitboxesInRoom) {
        this.name = name;
        this.spawnPX = spawnPX;
        this.spawnPY = spawnPY;
        this.hitboxesInRoom = hitboxesInRoom;
    }
    
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }
    
    public Room(String description, NPC npc) {
        this(description);
        this.npc = npc;
    }
    
    public Room(String description, ArrayList<Item> itemsInRoom) 
    {
        this(description);
        this.itemsInRoom = itemsInRoom;
    }
    
    public Room(String description, ArrayList<Item> itemsInRoom, NPC npc) 
    {
        this(description, itemsInRoom);
        this.npc = npc;
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        String npcNotification = "";
        
        if (npc != null) {
            npcNotification = "You can talk to " + npc.getNpcName() + ".\n";
        }
        
        return "You are " + description + ".\n" + npcNotification + getExitString();
    }

    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    public boolean hasNPC() {
        return npc != null; //Returns true if an address is assigned
    }
    
    /**
     * A get method for the npc
     * @return The address assigned to the npc attribute. If none is assigned
     * null is returned.
     */
    public NPC getNPC() {
        return npc;
    }
    
    public ArrayList<Item> getItemsInRoom() {
        return itemsInRoom;
    }
    
}
