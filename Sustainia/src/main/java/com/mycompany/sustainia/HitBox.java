package com.mycompany.sustainia;

public class HitBox {
    
    int topLeftX;
    int topLeftY;
    int width;
    int height;
   
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
    
    
    public void collisionWithObject(int x, int y){
        int TLX = topLeftX*World.scale -x;
        int TLY = topLeftY*World.scale -y;
        int w   = TLX + width*World.scale;
        int h   = TLY + height*World.scale;
        
        if ((characterHitbox[2] > TLX && characterHitbox[2] < w) ||
            (characterHitbox[0] > TLX && characterHitbox[0] < w)){
            
            if (characterHitbox[3] > TLY && characterHitbox[3] < TLY +6*World.scale){
                collisionTop = true;
            } else {
                collisionTop = false;
            }
            if (characterHitbox[1] < h && characterHitbox[1] > h -6*World.scale) {
                collisionBottom = true;
            } else {
                collisionBottom = false;
            }
            
        } else {
            collisionTop = false;
            collisionBottom = false;
        }
        
        
        if ((characterHitbox[3] > TLY && characterHitbox[3] < h) ||
            (characterHitbox[1] > TLY && characterHitbox[1] < h)){
            
            if (characterHitbox[2] > TLX && characterHitbox[2] < TLX +6*World.scale){
                collisionLeft = true;
            } else {
                collisionLeft = false;
            }
            if (characterHitbox[0] < w && characterHitbox[0] > w -6*World.scale){
                collisionRight = true;
            } else {
                collisionRight = false;
            }
        } else {
            collisionLeft = false;
            collisionRight = false;
        }
    }
    
    
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