package com.mycompany.sustainia;

public class World {
    /*
     * Because of the sudo 3d overhead prespective the horizontal and vertical movementspeed is not the same.
     * Because you view the game ad a 45 degree angle, the horizontal movmentspeed will alwayes equal sqrt((Vspeed^2)*2)) 
     */
    
    public static int characterWidth = 32*4;
    public static int characterHeight = 32*4;
    public static int characterMovementSpeedV = 4;
    public static int characterMovementSpeedH = 6;
    
    public static int gameScreenWidth = 600;
    public static int gameScreenHeight = 600;
    public static int gameX = -483*4;
    public static int gameY = -537*4;
    
    public static int characterX = (gameScreenWidth/2)-characterWidth/2;
    public static int characterY = (gameScreenHeight/2)-characterHeight/2;
    
}
