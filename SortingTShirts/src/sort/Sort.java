/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import java.util.ArrayList;
import java.util.List;
import models.TShirt;
import models.enums.Size;

/**
 *
 * @author George.Pasparakis
 */
public class Sort {
    
        /* The main function that implements QuickSort() 
      arr[] --> Array to be sorted, 
      low  --> Starting index, 
      high  --> Ending index */
    public void quickSortTShirts(List<TShirt> arr, int low, int high, int sortByAttribute, int sortingType) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = 0;
            switch (sortingType) {
                // ASC
                case 0:
                    pi = partition(arr, low, high, sortByAttribute, sortingType); // part <--- Size, Color, Fabric

                    // Recursively sort elements before 
                    // partition and after partition 
                    quickSortTShirts(arr, low, pi - 1, sortByAttribute, 0);
                    quickSortTShirts(arr, pi + 1, high, sortByAttribute, 0);
                    break;
                // DESC
                case 1:
                    pi = partition(arr, low, high, sortByAttribute, sortingType); // part <--- Size, Color, Fabric

                    // Recursively sort elements before 
                    // partition and after partition 
                    quickSortTShirts(arr, low, pi - 1, sortByAttribute, 1);
                    quickSortTShirts(arr, pi + 1, high, sortByAttribute, 1);
                    break;
            }
        }
    }
    
    int partition(List<TShirt> arr, int low, int high, int sortByAttribute, int sortingType) {
        TShirt pivot = arr.get(high);
        int i = (low - 1); // index of smaller element 
        for (int j = low; j < high; j++) {
            if (sortingType == 0) { // ASC
                switch (sortByAttribute) {
                    // Size
                    case 0:
                        // If current element is smaller than the pivot 
                        if (arr.get(j).getSize().ordinal() < pivot.getSize().ordinal()) {
                            i++;

                            // swap arr[i] and arr[j] 
                            TShirt temp = arr.get(i);
                            arr.set(i, arr.get(j));
                            arr.set(j, temp);
                        }
                        break;
                    // Color
                    case 1:
                        if (arr.get(j).getColor().ordinal() < pivot.getColor().ordinal()) {
                            i++;

                            // swap arr[i] and arr[j] 
                            TShirt temp = arr.get(i);
                            arr.set(i, arr.get(j));
                            arr.set(j, temp);
                        }
                        break;
                    // Fabric
                    case 2:
                        if (arr.get(j).getFabric().ordinal() < pivot.getFabric().ordinal()) {
                            i++;

                            // swap arr[i] and arr[j] 
                            TShirt temp = arr.get(i);
                            arr.set(i, arr.get(j));
                            arr.set(j, temp);
                        }
                        break;
                }
            } else { // DESC
                switch (sortByAttribute) {
                    // Size
                    case 0:
                        // If current element is smaller than the pivot 
                        if (arr.get(j).getSize().ordinal() > pivot.getSize().ordinal()) {
                            i++;

                            // swap arr[i] and arr[j] 
                            TShirt temp = arr.get(i);
                            arr.set(i, arr.get(j));
                            arr.set(j, temp);
                        }
                        break;
                    // Color
                    case 1:
                        if (arr.get(j).getColor().ordinal() > pivot.getColor().ordinal()) {
                            i++;

                            // swap arr[i] and arr[j] 
                            TShirt temp = arr.get(i);
                            arr.set(i, arr.get(j));
                            arr.set(j, temp);
                        }
                        break;
                    // Fabric
                    case 2:
                        if (arr.get(j).getFabric().ordinal() > pivot.getFabric().ordinal()) {
                            i++;

                            // swap arr[i] and arr[j] 
                            TShirt temp = arr.get(i);
                            arr.set(i, arr.get(j));
                            arr.set(j, temp);
                        }
                        break;
                }
            }

        }

        // swap arr[i+1] and arr[high] (or pivot) 
        TShirt temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);

        return i + 1;
    }
    
    public void quickSortTShirtsBySizeThenColorThenFabric(List<TShirt> randomTShirts, int sortingType) {
        List<TShirt> tShirts = new ArrayList<>();
        String sType = "";
        
        // Step 1 - Create noOfBuckets buckets, Size.length
        int noOfBuckets = Size.values().length;

        List<TShirt>[] buckets = new ArrayList[noOfBuckets];
        for (int i = 0; i < noOfBuckets; i++) { // noOfBuckets
            buckets[i] = new ArrayList<TShirt>(); // initialize the buckets
        }

        // Step 2, divide into buckets, Sort By Size
        for (TShirt tShirt : randomTShirts) {
            buckets[tShirt.getSize().ordinal()].add(tShirt);
        }
        if (sortingType == 0) { //ASC
            sType = "Ascending";
            // Step 3, Sort By Color
            for (List<TShirt> bucket : buckets) {
                quickSortTShirts(bucket, 0, bucket.size() - 1, 1, 0);
            }

            // Step 4, Sort By Fabric
            for (List<TShirt> bucket : buckets) {
                for (int i=1; i<bucket.size(); i++) {
                    if(bucket.get(i-1).getColor().ordinal() == bucket.get(i).getColor().ordinal()){
                        if(bucket.get(i-1).getFabric().ordinal() > bucket.get(i).getFabric().ordinal()){
                            // swap arr[j+1] and arr[j] 
                            TShirt temp = bucket.get(i-1);
                            bucket.set(i-1, bucket.get(i));  // arr[j] <- arr[j+1]
                            bucket.set(i, temp); // arr[j+1] = temp; 
                        }
                    }    
                }
            }

            // combine all the buckets to 1 List
            for (List<TShirt> bucket : buckets) {
                for (TShirt tShirt : bucket) {
                    tShirts.add(tShirt);
                }
            }
        }else{  //DESC
            sType = "Descending";
            // Step 3, Sort By Color
            for (List<TShirt> bucket : buckets) {
                quickSortTShirts(bucket, 0, bucket.size() - 1, 1, 1);
            }
            
            // Step 4, Sort By Fabric
            for (List<TShirt> bucket : buckets) {
                for (int i=1; i<bucket.size(); i++) {
                    if(bucket.get(i-1).getColor().ordinal() == bucket.get(i).getColor().ordinal()){
                        if(bucket.get(i-1).getFabric().ordinal() < bucket.get(i).getFabric().ordinal()){
                            // swap arr[j+1] and arr[j] 
                            TShirt temp = bucket.get(i-1);
                            bucket.set(i-1, bucket.get(i));  // arr[j] <- arr[j+1]
                            bucket.set(i, temp); // arr[j+1] = temp; 
                        }
                    }    
                }
            }
            
            // combine all the buckets to 1 List
            for (int i=buckets.length-1; i>=0; i--){
                for (TShirt tShirt : buckets[i]) {
                    tShirts.add(tShirt);
                }
            }
        }
        System.out.printf("%nQuick Sorted Array By Size %s, By Color %s And By Fabric %s%n", sType, sType, sType);
        for (TShirt tShirt : tShirts) {
            System.out.print(tShirt + "");
        }
    }

    public List<Integer> bubbleSort(List<Integer> arr) {
        List<Integer> arr2 = new ArrayList<>(arr);
        int n = arr2.size(); // 5
        for (int i = 0; i < n - 1; i++) // i = 0, 1
        {
            for (int j = 0; j < n - i - 1; j++) // j = 5-0-1 = 4, 5-1-1 = 3
            {
                if (arr2.get(j) > arr2.get(j + 1)) {
                    // swap arr[j+1] and arr[j] 
                    int temp = arr2.get(j);
                    arr2.set(j, arr2.get(j + 1));  // arr[j] <- arr[j+1]
                    arr2.set(j + 1, temp); // arr[j+1] = temp; 
                }
            }
        }
        return (arr2);
    }

    private List<TShirt> bubbleSortTShirtsBySize(List<TShirt> arr, int sortingType) // sortingType = 0 - ASC
    // sortingType = 1 - DESC
    {
        List<TShirt> arr2 = new ArrayList<>(arr);
        int n = arr2.size();
        switch (sortingType) {
            case 0:
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        if (arr2.get(j).getSize().ordinal() > arr2.get(j + 1).getSize().ordinal()) {
                            // swap arr[j+1] and arr[j] 
                            TShirt temp = arr2.get(j);
                            arr2.set(j, arr2.get(j + 1));  // arr[j] <- arr[j+1]
                            arr2.set(j + 1, temp); // arr[j+1] = temp; 
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        if (arr2.get(j).getSize().ordinal() < arr2.get(j + 1).getSize().ordinal()) {
                            // swap arr[j+1] and arr[j] 
                            TShirt temp = arr2.get(j);
                            arr2.set(j, arr2.get(j + 1));  // arr[j] <- arr[j+1]
                            arr2.set(j + 1, temp); // arr[j+1] = temp; 
                        }
                    }
                }
                break;
        }

        return (arr2);
    }

    private List<TShirt> bubbleSortTShirtsByColor(List<TShirt> arr, int sortingType) // sortingType = 0 - ASC
    // sortingType = 1 - DESC
    {
        List<TShirt> arr2 = new ArrayList<>(arr);
        int n = arr2.size();
        switch (sortingType) {
            case 0:
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        if (arr2.get(j).getColor().ordinal() > arr2.get(j + 1).getColor().ordinal()) {
                            // swap arr[j+1] and arr[j] 
                            TShirt temp = arr2.get(j);
                            arr2.set(j, arr2.get(j + 1));  // arr[j] <- arr[j+1]
                            arr2.set(j + 1, temp); // arr[j+1] = temp; 
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        if (arr2.get(j).getColor().ordinal() < arr2.get(j + 1).getColor().ordinal()) {
                            // swap arr[j+1] and arr[j] 
                            TShirt temp = arr2.get(j);
                            arr2.set(j, arr2.get(j + 1));  // arr[j] <- arr[j+1]
                            arr2.set(j + 1, temp); // arr[j+1] = temp; 
                        }
                    }
                }
                break;
        }

        return (arr2);
    }

    private List<TShirt> bubbleSortTShirtsByFabric(List<TShirt> arr, int sortingType) // sortingType = 0 - ASC
    // sortingType = 1 - DESC
    {
        List<TShirt> arr2 = new ArrayList<>(arr);
        int n = arr2.size();
        switch (sortingType) {
            case 0:
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        if (arr2.get(j).getFabric().ordinal() > arr2.get(j + 1).getFabric().ordinal()) {
                            // swap arr[j+1] and arr[j] 
                            TShirt temp = arr2.get(j);
                            arr2.set(j, arr2.get(j + 1));  // arr[j] <- arr[j+1]
                            arr2.set(j + 1, temp); // arr[j+1] = temp; 
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        if (arr2.get(j).getFabric().ordinal() < arr2.get(j + 1).getFabric().ordinal()) {
                            // swap arr[j+1] and arr[j] 
                            TShirt temp = arr2.get(j);
                            arr2.set(j, arr2.get(j + 1));  // arr[j] <- arr[j+1]
                            arr2.set(j + 1, temp); // arr[j+1] = temp; 
                        }
                    }
                }
                break;
        }

        return (arr2);
    }

    private List<TShirt> bubbleSortTShirtsBySizeColorFabric(List<TShirt> arr, int sortingType) // sortingType = 0 - ASC
    // sortingType = 1 - DESC
    {
        List<TShirt> arr2 = new ArrayList<>(arr);
        int n = arr2.size();
        switch (sortingType) {
            case 0:
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        if (arr2.get(j).getSize().ordinal() > arr2.get(j + 1).getSize().ordinal()) {
                            // swap arr[j+1] and arr[j] 
                            TShirt temp = arr2.get(j);
                            arr2.set(j, arr2.get(j + 1));  // arr[j] <- arr[j+1]
                            arr2.set(j + 1, temp); // arr[j+1] = temp; 
                        }
                    }
                }
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        if (arr2.get(j).getSize().ordinal() == arr2.get(j + 1).getSize().ordinal()) {
                            if (arr2.get(j).getColor().ordinal() > arr2.get(j + 1).getColor().ordinal()) {
                                // swap arr[j+1] and arr[j] 
                                TShirt temp = arr2.get(j);
                                arr2.set(j, arr2.get(j + 1));  // arr[j] <- arr[j+1]
                                arr2.set(j + 1, temp); // arr[j+1] = temp; 
                            }
                        }
                    }
                }
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        if (arr2.get(j).getSize().ordinal() == arr2.get(j + 1).getSize().ordinal()) {
                            if (arr2.get(j).getColor().ordinal() == arr2.get(j + 1).getColor().ordinal()) {
                                if (arr2.get(j).getFabric().ordinal() > arr2.get(j + 1).getFabric().ordinal()) {
                                    // swap arr[j+1] and arr[j] 
                                    TShirt temp = arr2.get(j);
                                    arr2.set(j, arr2.get(j + 1));  // arr[j] <- arr[j+1]
                                    arr2.set(j + 1, temp); // arr[j+1] = temp; 
                                }
                            }
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        if (arr2.get(j).getSize().ordinal() < arr2.get(j + 1).getSize().ordinal()) {
                            // swap arr[j+1] and arr[j] 
                            TShirt temp = arr2.get(j);
                            arr2.set(j, arr2.get(j + 1));  // arr[j] <- arr[j+1]
                            arr2.set(j + 1, temp); // arr[j+1] = temp; 
                        }
                    }
                }
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        if (arr2.get(j).getSize().ordinal() == arr2.get(j + 1).getSize().ordinal()) {
                            if (arr2.get(j).getColor().ordinal() < arr2.get(j + 1).getColor().ordinal()) {
                                // swap arr[j+1] and arr[j] 
                                TShirt temp = arr2.get(j);
                                arr2.set(j, arr2.get(j + 1));  // arr[j] <- arr[j+1]
                                arr2.set(j + 1, temp); // arr[j+1] = temp; 
                            }
                        }
                    }
                }
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        if (arr2.get(j).getSize().ordinal() == arr2.get(j + 1).getSize().ordinal()) {
                            if (arr2.get(j).getColor().ordinal() == arr2.get(j + 1).getColor().ordinal()) {
                                if (arr2.get(j).getFabric().ordinal() < arr2.get(j + 1).getFabric().ordinal()) {
                                    // swap arr[j+1] and arr[j] 
                                    TShirt temp = arr2.get(j);
                                    arr2.set(j, arr2.get(j + 1));  // arr[j] <- arr[j+1]
                                    arr2.set(j + 1, temp); // arr[j+1] = temp; 
                                }
                            }
                        }
                    }
                }
                break;
        }

        return (arr2);
    }

    public List<TShirt> bubbleSortTShirts(List<TShirt> arr, int sortingType, int sortByAttribute) {
        List<TShirt> arr2 = new ArrayList<>(arr);
        if (sortingType == 0) {
            switch (sortByAttribute) {
                // Size
                case 0:
                    arr2 = bubbleSortTShirtsBySize(arr, 0);
                    break;
                // Color    
                case 1:
                    arr2 = bubbleSortTShirtsByColor(arr, 0);
                    break;
                // Fabric
                case 2:
                    arr2 = bubbleSortTShirtsByFabric(arr, 0);
                    break;
                // Size, Color and Fabric
                case 3:
                    arr2 = bubbleSortTShirtsBySizeColorFabric(arr, 0);
                    break;
            }
        } else {
            switch (sortByAttribute) {
                // Size
                case 0:
                    arr2 = bubbleSortTShirtsBySize(arr, 1);
                    break;
                // Color    
                case 1:
                    arr2 = bubbleSortTShirtsByColor(arr, 1);
                    break;
                // Fabric
                case 2:
                    arr2 = bubbleSortTShirtsByFabric(arr, 1);
                    break;
                // Size, Color and Fabric
                case 3:
                    arr2 = bubbleSortTShirtsBySizeColorFabric(arr, 1);
                    break;
            }
        }
        return (arr2);
    }

    // Step 1: divide to n + 1 buckets as the number of elements, 
    // e.g. if we have 10 elements then we create 10 buckets
    // Step 2: place similar data to each bucket, 
    // e.g. 1: { 10, 11, 22, 2, 19, 3, 17}
    // n = 7, b[0], b[1], b[2], b[3], b[4], b[5], b[6], b[7]
    // b[1] = { 10, 11, 19, 17}
    // b[2] = { 22, 2 }
    // b[3] = { 3 }
    // e.g. 2: { 0, 0, 1, 3, 0, 4, 2, 3 }
    // int n = randomTShirts.size();
    // List<TShirt>[] buckets = new List<TShirt>[randomTShirts.size()]();
    // b[0] = { 0, 0, 0 }
    // b[1] = { 1, 1, 1, 1, 1 } <----- randomTShirts.get(22).getSize().ordinal() = 1
    // b[2] = { 2 }
    // b[3] = { 3, 3 }
    // b[4] = { 4 }
    // Step 3: sort each bucket ??????????
    public void bucketSortTShirts(List<TShirt> randomTShirts, int sortByAttribute, int sortingType) {
        // Step 1 - Create buckets, Size.length
        int noOfBuckets = Size.values().length;

        // Step 1, create noOfBuckets buckets  
        List<TShirt>[] buckets = new ArrayList[noOfBuckets];
        for (int i = 0; i < noOfBuckets; i++) { // noOfBuckets
            buckets[i] = new ArrayList<TShirt>(); // initialize the buckets
        }

        // Step 2, divide into buckets
        for (TShirt tShirt : randomTShirts) {
            switch (sortByAttribute) {
                case 0:
                    buckets[tShirt.getSize().ordinal()].add(tShirt);
                    break;
                case 1:
                    buckets[tShirt.getColor().ordinal()].add(tShirt);
                    break;
                case 2:
                    buckets[tShirt.getFabric().ordinal()].add(tShirt);
                    break;
            }
        }

        // Step 3, print
        // just print the contents of each bucket
        if (sortingType == 0) {
            for (int i = 0; i < noOfBuckets; i++) { // noOfBuckets
                for (int j = 0; j < buckets[i].size(); j++) {
                    System.out.print(buckets[i].get(j) + "");
                }
            }
        } else {
            for (int i = noOfBuckets - 1; i >= 0; i--) { // noOfBuckets
                for (int j = 0; j < buckets[i].size(); j++) {
                    System.out.print(buckets[i].get(j) + "");
                }
            }
        }
    }

    public void bucketSortTShirtsBySizeThenColorThenFabric(List<TShirt> randomTShirts, int sortingType) {
        List<TShirt> tShirts = new ArrayList<>();
        String sType = "";
        
        // Step 1 - Create noOfBuckets buckets, Size.length
        int noOfBuckets = Size.values().length;

        List<TShirt>[] buckets = new ArrayList[noOfBuckets];
        for (int i = 0; i < noOfBuckets; i++) { // noOfBuckets
            buckets[i] = new ArrayList<TShirt>(); // initialize the buckets
        }

        // Step 2, divide into buckets, Sort By Size
        for (TShirt tShirt : randomTShirts) {
            buckets[tShirt.getSize().ordinal()].add(tShirt);
        }
        if (sortingType == 0) { //ASC
            sType = "Ascending";
            // Step 3, Sort By Color
            for (List<TShirt> bucket : buckets) {
                quickSortTShirts(bucket, 0, bucket.size() - 1, 1, 0);
            }

            // Step 4, Sort By Fabric
            for (List<TShirt> bucket : buckets) {
                for (int i=1; i<bucket.size(); i++) {
                    if(bucket.get(i-1).getColor().ordinal() == bucket.get(i).getColor().ordinal()){
                        if(bucket.get(i-1).getFabric().ordinal() > bucket.get(i).getFabric().ordinal()){
                            // swap arr[j+1] and arr[j] 
                            TShirt temp = bucket.get(i-1);
                            bucket.set(i-1, bucket.get(i));  // arr[j] <- arr[j+1]
                            bucket.set(i, temp); // arr[j+1] = temp; 
                        }
                    }    
                }
            }

            // combine all the buckets to 1 List
            for (List<TShirt> bucket : buckets) {
                for (TShirt tShirt : bucket) {
                    tShirts.add(tShirt);
                }
            }
        }else{  //DESC
            sType = "Descending";
            // Step 3, Sort By Color
            for (List<TShirt> bucket : buckets) {
                quickSortTShirts(bucket, 0, bucket.size() - 1, 1, 1);
            }
            
            // Step 4, Sort By Fabric
            for (List<TShirt> bucket : buckets) {
                 for (int i=1; i<bucket.size(); i++) {
                    if(bucket.get(i-1).getColor().ordinal() == bucket.get(i).getColor().ordinal()){
                        if(bucket.get(i-1).getFabric().ordinal() < bucket.get(i).getFabric().ordinal()){
                            // swap arr[j+1] and arr[j] 
                            TShirt temp = bucket.get(i-1);
                            bucket.set(i-1, bucket.get(i));  // arr[j] <- arr[j+1]
                            bucket.set(i, temp); // arr[j+1] = temp; 
                        }
                    }    
                }
            }
            
            // combine all the buckets to 1 List
            for (int i=buckets.length-1; i>=0; i--){
                for (TShirt tShirt : buckets[i]) {
                    tShirts.add(tShirt);
                }
            }
        }
        System.out.printf("%nBucket Sorted Array By Size %s, By Color %s And By Fabric %s%n", sType, sType, sType);
        for (TShirt tShirt : tShirts) {
            System.out.print(tShirt + "");
        }
    }
}
