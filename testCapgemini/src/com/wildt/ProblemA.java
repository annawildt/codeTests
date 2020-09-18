package com.wildt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProblemA {
    public static void main() {
        Scanner userInput = new Scanner(System.in);
        List<List<String>> workTrips = new ArrayList<>();

        int testCases = getAmountOfTestCases(userInput);

        for (int i = 0; i < testCases; i++) {
            workTrips.add(createListOfCities(userInput));
        }

        for (int i = 0; i < countWorkTrips(workTrips); i++) {
            System.out.println(countCities(workTrips.get(i)));
        }
    }

    private static int getAmountOfTestCases (Scanner userInput) {
        int testCases = 0;
        //System.out.println("Enter amount of test cases");
        try {
            testCases = userInput.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test cases input failed.");
        }
        return testCases;
    }

    private static int countWorkTrips(List<List<String>> workTrips) {
        int listCounter = 0;
        for (List<String> worktrip : workTrips) {
            listCounter++;
        }
        return  listCounter;
    }

    private static List<String> createListOfCities(Scanner userInput) {
        List<String> citiesVisited = new ArrayList<>();
        int amountOfVisitedCities = 0;

        try {
            amountOfVisitedCities = userInput.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Amount of cities input failed.");
        }

        for (int i = 0; i < amountOfVisitedCities; i++) {
            addCity(userInput, citiesVisited);
        }

        return citiesVisited;
    }

    private static int countCities(List<String> workTrips) {
        int numberOfCities = 0;

        for (String city : workTrips)  {
            numberOfCities++;
        }
        return numberOfCities;
    }

    private static void addCity(Scanner userInput, List<String> workTrips) {
        String visitedCity = "";

        //System.out.println("Enter the city:");
        try {
            visitedCity = userInput.next();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!workTrips.contains(visitedCity)) {
            workTrips.add(visitedCity);
        }

    }
}
