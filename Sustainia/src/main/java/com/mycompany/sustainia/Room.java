package com.mycompany.sustainia;

import java.util.HashMap;
import java.util.Set;

public class Room {
    String name;
    int spawnPX;
    int spawnPY;
    HitBox[] hitboxesInRoom;
    Door door;
            
    public Room(String name, int spawnPX, int spawnPY, HitBox[] hitboxesInRoom, Door door) {
        this.name = name;
        this.spawnPX = spawnPX;
        this.spawnPY = spawnPY;
        this.hitboxesInRoom = hitboxesInRoom;
        this.door = door;
    }
    
}