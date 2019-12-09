package com.mycompany.sustainia;

public class World {
    
    // the scale variable, defines the factor, that the pixels in the images are scaled up with.
    public static int scale = 4;
    
    // here the width and hight of the charecter (in pixels) are difined.
    public static int characterWidth = 32*scale;
    public static int characterHeight = 32*scale;
    
    /*
     * Because of the sudo 3d overhead prespective the horizontal and vertical movementspeed is not the same.
     * Because you view the game ad a 45 degree angle, the horizontal movmentspeed will alwayes equal sqrt((Vspeed^2)*2)) 
     */
    public static int characterMovementSpeedV = 1*scale;
    public static int characterMovementSpeedH = (int)(1.5*scale);
    
    // here the starting width and hight of the game part of the screen is defined.
    public static int gameScreenWidth = 800;
    public static int gameScreenHeight = 600;
    
    // here the placment of the main character is defined.
    public static int characterX = gameScreenWidth/2;
    public static int characterY = gameScreenHeight/2;
    
    // depending on the room, the spawn point of the character changes.
    public static int spawnPointX = 0;
    public static int spawnPointY = 0;
    
    public static int gameX = 0;
    public static int gameY = 0;
    
    /*
     * here the width and hight of the roomes are defined.
     * The room "streets" is the only room that doesn't share the same width and hight as all of the other rooms, and theirfore it is defined sepretly.
     */
    public static int roomWidth = 256*scale;
    public static int roomHeight = 209*scale;
    public static int streetRoomWidth = 1120*scale;
    public static int streetRoomHeight = 770*scale;
}
