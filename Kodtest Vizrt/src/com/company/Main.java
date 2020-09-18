package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        //Example 1
        int exclude[] = createArray(20, 30);
        int include[] = createArray(10, 100);
        int newLowerNumbers[] = findAndExcludeNumbersLower(exclude, include);
        int newHigherNumbers[] = findNumbersHigherThanExclude(exclude, include);
        printArrays(newLowerNumbers);
        printArrays(newHigherNumbers);

        System.out.println();

        //Example 2
        int combineA[] = createArray(50, 5000);
        int combineB[] = createArray(10, 100);
        int combined[] = combineArraysWithoutExclude(combineA, combineB);
        printArrays(combined);

        System.out.println();

        //Example 3
        int combineA3[] = createArray(10,100);
        int combineB3 []= createArray(200, 300);
        int combined3[] = combineArraysWithoutExclude(combineA3, combineB3);
        int exclude3[] = createArray(95, 205);
        int newLowerNumbers3[] = findAndExcludeNumbersLower(exclude3, combined3);
        int newHigherNumbers3[] = findNumbersHigherThanExclude(exclude3, combined3);
        printArrays(newLowerNumbers3);
        printArrays(newHigherNumbers3);

    }

    public static int[] combineArraysWithoutExclude(int a[], int b[]) {
        int combined[] = new int[a.length + b.length];

        System.arraycopy(a, 0, combined, 0, a.length);
        System.arraycopy(b, 0, combined, a.length, b.length);

        Set<Integer> noDuplicates = new HashSet<>();

        for(int i = 0;i < combined.length;i++){
            noDuplicates.add(combined[i]);
        }

        int[] noDuplicatesArray = new int[noDuplicates.size()];
        int i = 0;
        Iterator<Integer> it = noDuplicates.iterator();
        while(it.hasNext()){
            noDuplicatesArray[i] = it.next();
            i++;
        }
        Arrays.sort(combined);
        return combined;
    }

    public static void printArrays(int array[]) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static int[] createArray(int min, int max) {
        int array[] = new int[max - min + 1];

        for (int i = 0; i < array.length; i++) {
            array[i] = min;
            min++;
        }
        return array;
    }

    public static int[] findAndExcludeNumbersLower(int[] exclude, int[] include) {
        int[] newLowNumbers = new int[include.length];

        int firstExclude = exclude[0];
        int counter = 0;

        for (int i = 0; i < include.length; i++) {
            if (include[i] == firstExclude) {
                break;
            }
            if (include[i] != exclude[i] && include[i] < firstExclude) {
                newLowNumbers[counter] = include[i];
                counter++;
            }
        }
        newLowNumbers = removeEmptyNumbers(newLowNumbers);
        return newLowNumbers;
    }

    public static int[] findNumbersHigherThanExclude(int[] exclude, int[] include) {
        int[] newHighNumbers = new int[include.length];
        int lastExclude = exclude[exclude.length - 1];
        int counter = 0;

        for (int i = 0; i < include.length; i++) {
            if (include[i] > lastExclude) {
                newHighNumbers[counter] = include[i];
                counter++;
            }
        }
        newHighNumbers = removeEmptyNumbers(newHighNumbers);
        return newHighNumbers;
    }

    public static int[] removeEmptyNumbers(int remove[]) {
        int breakpoint;
        for (int i = 0; i < remove.length; i++) {
            if (remove[i] == 0) {
                breakpoint = i;
                remove = Arrays.copyOf(remove, breakpoint);
            }
        }
        return remove;
    }
}
