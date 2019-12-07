package com.mycompany.sustainia;

import java.util.HashMap;
import java.util.Set;

public class Room {
    String name;
    
    int spawnPX;
    int spawnPY;
    int[] multiSpawnPX;
    int[] multiSpawnPY;
    int[][] multipleSpawnPoints;
    
    HitBox[] hitboxesInRoom;
    
    Door door;
    Door[] multipleDoors;
            
    public Room(String name, int spawnPX, int spawnPY, HitBox[] hitboxesInRoom, Door door) {
        this.name = name;
        this.spawnPX = spawnPX;
        this.spawnPY = spawnPY;
        this.hitboxesInRoom = hitboxesInRoom;
        this.door = door;
    }
    
    
    public Room(String name, int[] multiSpawnPX, int[] multiSpawnPY, HitBox[] hitboxesInRoom, Door door) {
        this.name = name;
        this.multiSpawnPX = multiSpawnPX;
        this.multiSpawnPY = multiSpawnPY;
        this.hitboxesInRoom = hitboxesInRoom;
        this.door = door;
    }
}