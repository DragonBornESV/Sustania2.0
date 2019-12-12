package com.mycompany.sustainia;

public class HitBox {
    
    int topLeftX;
    int topLeftY;
    int width;
    int height;
   
    // The characters hitbox is defined by the in game coordinates of the characters top left corner and bottom right corner.
    int[] characterHitbox = {World.characterX -9*World.scale,
                             World.characterY +6*World.scale,
                             World.characterX + World.characterWidth/2 -7*World.scale,
                             World.characterY + World.characterHeight/2 -1*World.scale};
    
    boolean collisionTop;
    boolean collisionBottom;
    boolean collisionLeft;
    boolean collisionRight;
    private boolean triggered;
    
    public HitBox(int topLeftX, int topLeftY, int width, int height){
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.width = width;
        this.height = height;
    }
    
    // Checks if the player collides with the hitbox
    public void collisionWithObject(int x, int y){
        int TLX = topLeftX * World.scale -x;    //Converts it to pixelsize and in-game coordinates.
        int TLY = topLeftY * World.scale -y;
        int w   = TLX + width*World.scale;      //The same with width and height
        int h   = TLY + height*World.scale;     
        
        // First we check if the characters lowest or highest x-coordinates are within the hitbox of the objects x-coordinates.
        if (((characterHitbox[2] > TLX && characterHitbox[2] < w) ||
             (characterHitbox[0] > TLX && characterHitbox[0] < w)) ||
            ((TLX > characterHitbox[0] && w < characterHitbox[2]))){
            
            // If the characters lovest or higest x-coordinats is within the objects hitbox, we check if the characters highest y-coordinate is whithin the objects hitbox.
            if (characterHitbox[3] > TLY && characterHitbox[3] < TLY +6*World.scale){
                collisionTop = true; // If the characters highest y-coordinate is whithin the object, a collision on top of the object is detected.
            } else {
                collisionTop = false;
            }
            // If the characters lowest or higest x-coordinates is within the objects hitbox, we check if the characters lowest y-coordinate is whithin the objects hitbox.
            if (characterHitbox[1] < h && characterHitbox[1] > h -6*World.scale) {
                collisionBottom = true; // If the character lowest y-coordinate is within the object a collision on the bottom of the object is detected.
            } else {
                collisionBottom = false;
            }
            
        } else {
            collisionTop = false;
            collisionBottom = false;
        }
        
        // We check if the characters lowest or highest y-coordinate is within the objects lowest and highes y-coordinate.
        if ((characterHitbox[3] > TLY && characterHitbox[3] < h) ||
            (characterHitbox[1] > TLY && characterHitbox[1] < h)){
            
            // If the characters lowst or highes y-coordonate is within the object, we check if the characters highes x-coordinate is within the object. 
            if (characterHitbox[2] > TLX && characterHitbox[2] < TLX +6*World.scale){
                collisionLeft = true; // if the characters x-coordinate is within the objekt, a collision on the left side of the object is detected.
            } else {
                collisionLeft = false;
            }
            // If the characters lowest or highes y-coordonate is within the object, we check if the characters lowest x-coordinate is within the object. 
            if (characterHitbox[0] < w && characterHitbox[0] > w -6*World.scale){
                collisionRight = true; // If the characters lowest x-coordinate is whithin the object, a collision on the right side of the object is detected.
            } else {
                collisionRight = false;
            }
        } else {
            collisionLeft = false;
            collisionRight = false;
        }
    }
    
    // If a collision on either side of the object is detected, the object is declered triggerd.
    public boolean checkIfTriggered(){
        if (collisionLeft || collisionRight || collisionTop || collisionBottom){
            triggered = true;
        } else {
            triggered = false;
        }
        return triggered;
    }
    
    public String toString() {
        return "position: " + topLeftX + ", " + topLeftY + ", size: " + width + ", " + height;
    }

}