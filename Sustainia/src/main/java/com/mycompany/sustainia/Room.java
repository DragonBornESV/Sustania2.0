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
    HitBox[] multipleDoors;
            
    public Room(String name, int spawnPX, int spawnPY, HitBox[] hitboxesInRoom, Door door) {
        this.name = name;
        this.spawnPX = spawnPX;
        this.spawnPY = spawnPY;
        this.hitboxesInRoom = hitboxesInRoom;
        this.door = door;
    }
    
    
    public Room(String name, int[][] multipleSpawnPoints, HitBox[] hitboxesInRoom, HitBox[] multipleDoors) {
        this.name = name;
        this.multipleSpawnPoints = multipleSpawnPoints;
        this.hitboxesInRoom = hitboxesInRoom;
        this.multipleDoors = multipleDoors;
    }
}