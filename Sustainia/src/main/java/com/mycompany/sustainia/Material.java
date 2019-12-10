package com.mycompany.sustainia;
/* 
 * This class creates our objekt material, 
 * which consists of a name in form of a string, 
 * a value in form of a double, the weight as a double 
 * and the amount(count) of materials as an integer.
 */
public class Material {
    String name = "Name";
    double value = 0;
    double weight = 0.1;
    int count;
    
    public Material(String matName, double matValue, double matWeight, int count){
        name = matName;
        value = matValue;
        weight = matWeight;
        this.count = count;
    }
    
    public void setCount(int newCount) {
        count = newCount;
    }

    @Override
    public String toString(){
        return  "Material:"+this.name+"\t"+"| "+"Value:"+this.value+"\t"+"| " +"Weight:"+this.weight;
    }
}
