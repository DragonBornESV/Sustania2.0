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
    int[] multiSpawnPX;
    int[] multiSpawnPY;
    int[][] multipleSpawnPoints;
    
    HitBox[] hitboxesInRoom;
    
    Door door;
    HitBox[] multipleDoors;
            
    /**
     * 
     * @param name name of the room
     * @param spawnPX x-coordinate for the player
     * @param spawnPY y-coordinate for the player
     * @param hitboxesInRoom array fo hitboxes in room
     * @param door hotbox of the door
     * @param itemsInRoom array of items in room
     */
    public Room(String name, int spawnPX, int spawnPY, HitBox[] hitboxesInRoom, Door door, ArrayList<Item> itemsInRoom) {
        this.name = name;
        this.spawnPX = spawnPX;
        this.spawnPY = spawnPY;
        this.hitboxesInRoom = hitboxesInRoom;
        this.door = door;
        this.itemsInRoom = itemsInRoom;
    }
    
    /**
     * Constructor for room with multiple doors
     * @param name name of the room
     * @param multipleSpawnPoints
     * @param hitboxesInRoom array of hitboxes in room
     * @param multipleDoors array of hitboxes for the doors
     * @param itemsInRoom array of items in roon
     */
    public Room(String name, int[][] multipleSpawnPoints, HitBox[] hitboxesInRoom, HitBox[] multipleDoors, ArrayList<Item> itemsInRoom) {
        this.name = name;
        this.multipleSpawnPoints = multipleSpawnPoints;
        this.hitboxesInRoom = hitboxesInRoom;
        this.multipleDoors = multipleDoors;
        this.itemsInRoom = itemsInRoom;
    
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
    
    
    public void setNPC(NPC npc) {
        this.npc = npc;
    }
    

    public ArrayList<Item> getItemsInRoom() {
        return itemsInRoom;
    }
    
}
