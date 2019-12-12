package com.mycompany.sustainia;
/* 
 * This class creates our objekt material, 
 * which consists of a name in form of a string, 
 * a value in form of a double, the weight as a double 
 * and the amount(count) of materials as an integer.
 */
public class Material implements Cloneable {
    String name;
    double value;           //How much the material is worth per unit.
    double weight = 0.1;    //How much it weighs per unit
    int count;              //The quantity of the material
    
    public Material(String matName, double matValue, double matWeight, int count){
        name = matName;
        value = matValue;
        weight = matWeight;
        this.count = count;
    }
    
    public void setCount(int newCount) {
        count = newCount;
    }
    
    /**
     * Returns the name of the material and the quantity.
     * @return 
     */
    @Override
    public String toString() {
        return "Name: "+this.name+"\t\t| Count: "+this.count;
    }
    
    /**
     * Clones the material
     * @return a copy of the Material instance.
     */
    @Override
    public Material clone() {
        //
        try {
            return (Material) super.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println("The superclass doesn't support cloning");
        }
        
        return null;
    }
}