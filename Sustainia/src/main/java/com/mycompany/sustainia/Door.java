package com.mycompany.sustainia;

public class Door {
    HitBox doorFrame;
    Room leadsTo;
    
    public Door(HitBox doorFrame, Room leadsTo){
        this.doorFrame = doorFrame;
        this.leadsTo = leadsTo;
    }
}
