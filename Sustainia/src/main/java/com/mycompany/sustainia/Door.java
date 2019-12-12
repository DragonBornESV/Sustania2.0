package com.mycompany.sustainia;

public class Door {
    //The hitbox to collide with player
    HitBox doorFrame;
    //The room this door leads to
    Room leadsTo;
    
    //Constructor for a door
    public Door(HitBox doorFrame, Room leadsTo){
        this.doorFrame = doorFrame;
        this.leadsTo = leadsTo;
    }
}
