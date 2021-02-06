/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printing;

import java.util.List;
import models.TShirt;

/**
 *
 * @author George.Pasparakis
 */
public class Print {
    /* Prints the array */
    public void printArrayInt(List<Integer> arr) 
    { 
        int n = arr.size(); 
        for (int i=0; i<n; ++i) 
            System.out.print(arr.get(i) + ""); 
        System.out.println(); 
    } 
    
    public void printArrayTShirt(List<TShirt> arr) 
    { 
        int n = arr.size(); 
        for (int i=0; i<n; ++i) 
            System.out.print(arr.get(i) + ""); 
        System.out.println(); 
    } 
    
}
