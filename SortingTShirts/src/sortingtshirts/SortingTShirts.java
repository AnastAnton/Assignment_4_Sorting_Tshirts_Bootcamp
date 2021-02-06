/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortingtshirts;

import java.util.ArrayList;
import java.util.List;
import models.TShirt;
import models.Timer;
import models.random.RandomTShirt;
import printing.Print;
import sort.Sort;

/**
 *
 * @author George.Pasparakis
 */
public class SortingTShirts {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Print p = new Print();
        Sort s = new Sort();
        int numberOfRandomTShirts = 50;                   
        System.out.println("Original Array");
        List<TShirt> randomTShirts = new ArrayList<>(numberOfRandomTShirts);
        for (int i = 0; i < numberOfRandomTShirts; i++) {
            randomTShirts.add(new RandomTShirt());
        }
        // Show random tshirts
        p.printArrayTShirt(randomTShirts);

        // Show tshirts, sorted ascending by Quick sort
        showQuickTShirtAsc(randomTShirts, p, s, numberOfRandomTShirts);
        // Show tshirts, sorted descending by Quick sort
        showQuickTShirtDesc(randomTShirts, p, s, numberOfRandomTShirts);
       
        //Show tshirts, sorted ascending by Bubble sort
        showBubbleTShirtAsc(randomTShirts, p, s);
        // Show tshirts, sorted descending by Bubble sort
        showBubbleTShirtDesc(randomTShirts, p, s);
        
        // Show tshirts, sorted ascending by Bucket sort
        showBucketTShirtAsc(randomTShirts, s);
        // Show tshirts, sorted descending by Bucket sort
        showBucketTShirtDesc(randomTShirts, s);
    }
    
    public static void showQuickTShirtAsc(List<TShirt> randomTShirts, Print p, Sort s, int numberOfRandomTShirts){
        // By Size
        System.out.println("\nQuick Sorted Array By Size Ascending");
        List<TShirt> quickSortRandomTShirtsBySize = new ArrayList<TShirt>(randomTShirts);
        Timer quickTimer = new Timer(); 
        s.quickSortTShirts(quickSortRandomTShirtsBySize, 0, numberOfRandomTShirts - 1, 0, 0);
        quickTimer.stop();
        p.printArrayTShirt(quickSortRandomTShirtsBySize);
        System.out.println("Quick Sort Time: " + quickTimer.elapsedTime());
        
        // By Color
        System.out.println("\nQuick Sorted Array By Color Ascending");
        List<TShirt> bubbleSortRandomTShirtsByColor = new ArrayList<TShirt>(randomTShirts);       
        s.quickSortTShirts(bubbleSortRandomTShirtsByColor, 0, numberOfRandomTShirts - 1, 1, 0);
        p.printArrayTShirt(bubbleSortRandomTShirtsByColor);
        
        // By Fabric
        System.out.println("\nQuick Sorted Array By Fabric Ascending");
        List<TShirt> bubbleSortRandomTShirtsByFabric = new ArrayList<TShirt>(randomTShirts);       
        s.quickSortTShirts(bubbleSortRandomTShirtsByFabric, 0, numberOfRandomTShirts - 1, 2, 0);
        p.printArrayTShirt(bubbleSortRandomTShirtsByFabric);
        
        // By Size, Color, Fabric
        List<TShirt> bubbleSortRandomTShirtsByAll = new ArrayList<TShirt>(randomTShirts);       
        s.quickSortTShirtsBySizeThenColorThenFabric(bubbleSortRandomTShirtsByAll, 0);

    }
    
    public static void showQuickTShirtDesc(List<TShirt> randomTShirts, Print p, Sort s, int numberOfRandomTShirts){
        // By Size
        System.out.println("\nQuick Sorted Array By Size Descending");
        List<TShirt> quickSortRandomTShirtsBySize = new ArrayList<TShirt>(randomTShirts);
        s.quickSortTShirts(quickSortRandomTShirtsBySize, 0, numberOfRandomTShirts - 1, 0, 1);
        p.printArrayTShirt(quickSortRandomTShirtsBySize);
        
        // By Color
        System.out.println("\nQuick Sorted Array By Color Descending");
        List<TShirt> bubbleSortRandomTShirtsByColor = new ArrayList<TShirt>(randomTShirts);       
        s.quickSortTShirts(bubbleSortRandomTShirtsByColor, 0, numberOfRandomTShirts - 1, 1, 1);
        p.printArrayTShirt(bubbleSortRandomTShirtsByColor);
        
        // By Fabric
        System.out.println("\nQuick Sorted Array By Fabric Descending");
        List<TShirt> bubbleSortRandomTShirtsByFabric = new ArrayList<TShirt>(randomTShirts);       
        s.quickSortTShirts(bubbleSortRandomTShirtsByFabric, 0, numberOfRandomTShirts - 1, 2, 1);
        p.printArrayTShirt(bubbleSortRandomTShirtsByFabric);
        
        // By Size, Color, Fabric
        List<TShirt> bubbleSortRandomTShirtsByAll = new ArrayList<TShirt>(randomTShirts);       
        s.quickSortTShirtsBySizeThenColorThenFabric(bubbleSortRandomTShirtsByAll, 1);
    }
    
    public static void showBubbleTShirtAsc(List<TShirt> randomTShirts, Print p, Sort s){
        // By Size
        System.out.println("\nBubble Sorted Array By Size Ascending");
        Timer bubbleTimer = new Timer();
        List<TShirt> bubbleSortRandomTShirtsBySize = s.bubbleSortTShirts(randomTShirts, 0, 0);
        bubbleTimer.stop();
        p.printArrayTShirt(bubbleSortRandomTShirtsBySize);
        System.out.println("Bubble Sort Time: " + bubbleTimer.elapsedTime());
        
        // By Color
        System.out.println("\nBubble Sorted Array By Color Ascending");
        List<TShirt> bubbleSortRandomTShirtsByColor = s.bubbleSortTShirts(randomTShirts, 0, 1);
        p.printArrayTShirt(bubbleSortRandomTShirtsByColor);
        
        // By Fabric
        System.out.println("\nBubble Sorted Array By Fabric Ascending");
        List<TShirt> bubbleSortRandomTShirtsByFabric = s.bubbleSortTShirts(randomTShirts, 0, 2);
        p.printArrayTShirt(bubbleSortRandomTShirtsByFabric);
        
        // By Size, Color and Fabric    <-- if sizes are same then sorted by Color, if colors are same then sorted by Fabric
        System.out.println("\nBubble Sorted Array By Size, Color and Fabric Ascending");
        List<TShirt> bubbleSortRandomTShirtsByAll = s.bubbleSortTShirts(randomTShirts, 0, 3);
        p.printArrayTShirt(bubbleSortRandomTShirtsByAll);
    }
    
    public static void showBubbleTShirtDesc(List<TShirt> randomTShirts, Print p, Sort s){
        // By Size
        System.out.println("\nBubble Sorted Array By Size Descending");
        List<TShirt> bubbleSortRandomTShirtsBySize = s.bubbleSortTShirts(randomTShirts, 1, 0);
        p.printArrayTShirt(bubbleSortRandomTShirtsBySize);
        
        // By Color
        System.out.println("\nBubble Sorted Array By Color Descending");
        List<TShirt> bubbleSortRandomTShirtsByColor = s.bubbleSortTShirts(randomTShirts, 1, 1);
        p.printArrayTShirt(bubbleSortRandomTShirtsByColor);
        
        // By Fabric
        System.out.println("\nBubble Sorted Array By Fabric Descending");
        List<TShirt> bubbleSortRandomTShirtsByFabric = s.bubbleSortTShirts(randomTShirts, 1, 2);
        p.printArrayTShirt(bubbleSortRandomTShirtsByFabric);
        
        // By Size, Color and Fabric    <-- if sizes are same then sorted by Color, if colors are same then sorted by Fabric
        System.out.println("\nBubble Sorted Array By Size, Color and Fabric Descending");
        List<TShirt> bubbleSortRandomTShirtsByAll = s.bubbleSortTShirts(randomTShirts, 1, 3);
        p.printArrayTShirt(bubbleSortRandomTShirtsByAll);
    }

    public static void showBucketTShirtAsc(List<TShirt> randomTShirts, Sort s){
        // By Size
        System.out.println("\nBucket Sorted Array By Size Ascending");
        List<TShirt> bucketSortRandomTShirtsBySize = new ArrayList<TShirt>(randomTShirts);
        Timer bucketTimer = new Timer();
        s.bucketSortTShirts(bucketSortRandomTShirtsBySize, 0, 0);
        bucketTimer.stop();
        System.out.println("Bucket Sort Time: " + bucketTimer.elapsedTime());
        // By Color
        System.out.println("\nBucket Sorted Array By Color Ascending");
        List<TShirt> bucketSortRandomTShirtsByColor = new ArrayList<TShirt>(randomTShirts); 
        s.bucketSortTShirts(bucketSortRandomTShirtsByColor, 1, 0);
        // By Fabric
        System.out.println("\nBucket Sorted Array By Fabric Ascending");
        List<TShirt> bucketSortRandomTShirtsByFabric = new ArrayList<TShirt>(randomTShirts);
        s.bucketSortTShirts(bucketSortRandomTShirtsByFabric, 2, 0);
        // By Size, Color, Fabric
        List<TShirt> bucketSortRandomTShirtsByAll = new ArrayList<TShirt>(randomTShirts); 
        s.bucketSortTShirtsBySizeThenColorThenFabric(bucketSortRandomTShirtsByAll, 0);
    }
    
    public static void showBucketTShirtDesc(List<TShirt> randomTShirts, Sort s){
        // By Size
        System.out.println("\nBucket Sorted Array By Size Descending");
        List<TShirt> bucketSortRandomTShirtsBySize = new ArrayList<TShirt>(randomTShirts); 
        s.bucketSortTShirts(bucketSortRandomTShirtsBySize, 0, 1);
        // By Color
        System.out.println("\nBucket Sorted Array By Color Descending");
        List<TShirt> bucketSortRandomTShirtsByColor = new ArrayList<TShirt>(randomTShirts); 
        s.bucketSortTShirts(bucketSortRandomTShirtsByColor, 1, 1);
        // By Fabric
        System.out.println("\nBucket Sorted Array By Fabric Descending");
        List<TShirt> bucketSortRandomTShirtsByFabric = new ArrayList<TShirt>(randomTShirts);
        s.bucketSortTShirts(bucketSortRandomTShirtsByFabric, 2, 1);
        // By Size, Color, Fabric
        List<TShirt> bucketSortRandomTShirtsByAll = new ArrayList<TShirt>(randomTShirts);       
        s.bucketSortTShirtsBySizeThenColorThenFabric(bucketSortRandomTShirtsByAll, 1);
    }
}
