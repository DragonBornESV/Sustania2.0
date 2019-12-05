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
    
    // Here the materials are constructed. In the following order given a name, value, weight and quantity.
    static Material aluminum = new Material("Aluminum", 0.81, 0.1, 0);
    static Material cloth    = new Material("Cloth   ", 2.10, 0.1, 0);
    static Material compost  = new Material("Compost ", 0.37, 0.1, 0);
    static Material concrete = new Material("Concrete", 2.42, 0.1, 0);
    static Material copper   = new Material("Copper  ", 3.50, 0.1, 0);
    static Material glass    = new Material("Glass   ", 0.60, 0.1, 0);
    static Material iron     = new Material("Iron    ", 0.38, 0.1, 0);
    static Material oakWood  = new Material("Oak Wood", 2.70, 0.1, 0);
    static Material paper    = new Material("Paper   ", 1.75, 0.1, 0);
    static Material plastic  = new Material("Plastic ", 2.10, 0.1, 0);
    static Material rubber   = new Material("Rubber  ", 1.00, 0.1, 0);
    
    // Here the items are constructed using the item datatype.
    
    // Aluminum Can
    static Material[] aluminumCanMaterialArray = {aluminum};
    static int[] aluminumCanMaterialcountArray = {1}; 
    public static Item aluminumCan   = new Item("Aluminum can", aluminumCanMaterialArray, 0);
    // Axe
    static Material[] axeMaterialArray = {iron, aluminum};
    static int[] axeMaterialcountArray = {56, 6};
    public static Item axe = new Item("Axe", aluminumCanMaterialArray, 1);
    // Cardboard Box
    static Material[] cardboardBoxMaterialArray = {paper};
    static int[] cardboardBoxMaterialCount = {1};
    public static Item cardboardBox = new Item("Cardboard Box", cardboardBoxMaterialArray, 2);
    // Clothes
    static Material[] clothesMaterialArray = {cloth};
    static int[] clothesMaterialCount = {5};
    public static Item clothes   = new Item("Clothes", clothesMaterialArray, 3);
    // Computer
    static Material[] computerMaterialArray = {copper, plastic, aluminum, iron};
    static int[] computerMaterialcountArray = {22, 34, 11, 45};
    public static Item computer   = new Item("Computer", computerMaterialArray, 4);
    // Glass Bottle
    static Material[] glassBottleMaterialArray = {glass};
    static int[] glassBottleMaterialCount = {4};
    public static Item glassBottle   = new Item("Glass bottle", glassBottleMaterialArray, 5);
    // Iron Can
    static Material[] ironCanMaterialArray = {iron};
    static int[] ironCanMaterialcountArray = {1};
    public static Item ironCan   = new Item("Iron can", ironCanMaterialArray, 6);
    // Organic Waste
    static Material[] organicWasteMaterialArray = {compost};
    static int[] organicWasteMaterialCount = {10};
    public static Item organicWaste   = new Item("Organic waste", organicWasteMaterialArray, 7);
    // Plastic Bottle
    static Material[] plasticBottleMaterialArray = {plastic};
    static int[] plasticBottleMaterialCount = {1};
    public static Item plasticBottle   = new Item("Plastic bottle", plasticBottleMaterialArray, 8);
    // Tire
    static Material[] tireMaterialArray = {rubber, iron};
    static int[] tireMaterialcountArray = {160, 40};
public static Item tire   = new Item("Tire", tireMaterialArray, 9);

    
    // An array is created, which contains one of each material. it is used to print the materials.
    public static Material[] materialArray = {aluminum, cloth, compost, concrete, copper, glass, iron, paper, oakWood, plastic, rubber}; 
    
}
