/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.random;

import java.util.Random;
import models.TShirt;
import models.enums.Color;
import models.enums.Fabric;
import models.enums.Size;

/**
 *
 * @author George.Pasparakis
 */
public class RandomTShirt extends TShirt{
    
    public RandomTShirt() {
        this.setSize(randomSize());
        this.setColor(randomColor());
        this.setFabric(randomFabric());
        this.setName(randomName());
    }
    
    public Size randomSize() {
        Random r = new Random();
        // Size.values().length = 7
        int enumLength = Size.values().length;
        //Size.values()[0]; <---- XS
        Size s = Size.values()[r.nextInt(enumLength)]; // <---- random number 0 - 7 
                                                       // and returns the actual element 
                                                       // from Size on the random int
        //return(s);
        return(Size.values()[r.nextInt(Size.values().length)]);
    }
    
    public Color randomColor() {
        Random r = new Random();
        // Size.values().length = 7
        return(Color.values()[r.nextInt(Color.values().length)]);
    }
    
    public Fabric randomFabric() {
        Random r = new Random();
        // Size.values().length = 7
        return(Fabric.values()[r.nextInt(Fabric.values().length)]);
    }
    
    public String randomName() {
        char chars[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String s = "";
        for (int i = 0; i < 8; i++) {
            s += chars[new Random().nextInt(chars.length)];
        }
        return(s);
    }
    
}
