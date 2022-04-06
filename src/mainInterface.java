import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class mainInterface {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean quit = false;

        System.out.println("Welcome to Super Bus System!");
        while (!quit) {
            System.out.print("To find the shortest path between two stops, type 1. \n" +
                    "To search for a stop by name, type 2. \nTo search for an arrival by time, type 3. \n" +
                    "To exit the program, type \"Quit\". ");
            if (input.hasNextInt()) {
                int inputNumber = input.nextInt();
                if (inputNumber == 1) {
                    StopMap makeMap = new StopMap();
                    HashMap<Integer, BusStop> mapOfStops = makeMap.getMapOfStops();
                    boolean back = false;

                    BusStop stopA = null;
                    BusStop stopB = null;

                    boolean stopAGiven = false;
                    while (!back && !stopAGiven) {
                        System.out.print("Enter starting stop (or type \"Back\" to return to Main Menu): ");
                        if (input.hasNextInt()) {
                            int stopID_A = input.nextInt();
                            if (mapOfStops.get(stopID_A) != null) {
                                stopA = mapOfStops.get(stopID_A);
                                stopAGiven = true;
                            } else {
                                System.out.println("Please enter a valid Stop ID");
                            }
                        } else if(input.hasNext()) {
                            String tmp = input.next();
                            if (tmp.equalsIgnoreCase("back")) {
                                back = true;
                            }
                        }
                        else {
                            System.out.println("Please enter a valid Stop ID");
                        }
                    }

                        boolean stopBGiven = false;
                        while (!back && !stopBGiven) {
                            System.out.print("Enter final stop (or type \"Back\" to return to Main Menu): ");
                            if (input.hasNextInt()) {
                                int stopID_B = input.nextInt();
                                if (mapOfStops.get(stopID_B) != null) {
                                    stopB = mapOfStops.get(stopID_B);
                                    stopBGiven = true;
                                } else {
                                    System.out.println("Please enter a valid Stop ID");
                                }
                            }
                            else if(input.hasNext()) {
                                String tmp = input.next();
                                if (tmp.equalsIgnoreCase("back")) {
                                    back = true;
                                }
                            }
                            else {
                                System.out.println("Please enter a valid Stop ID");
                            }
                        }

                        if(!back) {
                            int stopID_A = stopA.getStopID();
                            int stopID_B = stopB.getStopID();
                            HashMap<Integer, Double> distances = shortestPathBetweenStops.dijkstra(mapOfStops, stopID_A, stopID_B);
                            ArrayList<Edge> path = shortestPathBetweenStops.AtoBPath(stopID_A, stopID_B);

                            System.out.println("Distance between stops: " + distances.get(stopID_B));
                            if (path != null) {
                                System.out.println("Starting Stop: " + stopID_A);
                                for (int i = path.size() - 1; i > 0; i--) {
                                    if (i > 1) {
                                        System.out.println("Next Stop: " + path.get(i).getStopID_B());
                                    } else {
                                        System.out.println("Final Stop: " + stopID_B);
                                    }
                                }
                            }
                        }
                } else if (inputNumber == 2) {
                    stopNameSearch search = new stopNameSearch();
                    ternarySearchTree<Integer> stopTree = search.getStopTree();
                    boolean nameGiven = false;
                    boolean back = false;

                    while(!back && !nameGiven) {
                        System.out.print("Search for a stop name: ");
                        if (input.hasNext()) {
                            String userInput = input.next();
                            userInput = userInput.toUpperCase();
                            if (!userInput.equals("")) {
                                if (stopTree.keysWithPrefix(userInput) != null) {
                                    for (String s : stopTree.keysThatMatch(userInput)) {
                                        System.out.println(s);
                                    }
                                    for (String s : stopTree.keysWithPrefix(userInput)) {
                                        System.out.println(s);
                                    }
                                    nameGiven = true;
                                }
                                else if(userInput.equalsIgnoreCase("back")) {
                                    back = true;
                                }
                                else {
                                    System.out.println("Please enter a valid stop name.");
                                }
                            } else {
                                System.out.println("Please enter a valid stop name.");
                            }
                        }
                    }
                } else if (inputNumber == 3) {
                    arrivalTimeSearch search = new arrivalTimeSearch();
                    ternarySearchTree<stopTime> stopTimeTree = search.getStopTimeTree();
                    boolean timeGiven = false;
                    ArrayList<stopTime> searchedList = new ArrayList<>();
                    boolean back = false;

                    while(!back && !timeGiven) {
                        System.out.print("Search for an arrival time (or type \"Quit\"): ");
                        if (input.hasNext()) {
                            String userInput = input.next();
                            userInput = userInput.toUpperCase();
                            String[] userInputSplit = userInput.split(":");
                            if (!userInput.equals("")) {
                                if (userInputSplit.length == 3) {
                                    if (userInputSplit[0] != null && userInputSplit[1] != null && userInputSplit[2] != null &&
                                            Integer.parseInt(userInputSplit[0]) >= 0 && Integer.parseInt(userInputSplit[0]) <= 23 &&
                                            Integer.parseInt(userInputSplit[1]) >= 0 && Integer.parseInt(userInputSplit[1]) <= 59 &&
                                            Integer.parseInt(userInputSplit[2]) >= 0 && Integer.parseInt(userInputSplit[2]) <= 59) {
                                        if (stopTimeTree.keysWithPrefix(userInput) != null) {
                                            searchedList = new ArrayList<>();
                                            for (String s : stopTimeTree.keysWithPrefix(userInput)) {
                                                stopTime tmp = stopTimeTree.get(s);
                                                searchedList.add(tmp);
                                            }
                                            searchedList.sort(Comparator.comparing(stopTime::getTripID));
                                            for(int i = 0; i < searchedList.size(); i++ ) {
                                                System.out.println("Trip ID: " + searchedList.get(i).getTripID() +
                                                        "     Arrival Time: " + searchedList.get(i).getArrivalTime()
                                                        + "     Full Stop Details: " + searchedList.get(i).getFullDetails());
                                                        }
                                                timeGiven = true;

                                        } else {
                                            System.out.println("No arrivals at this time.");
                                        }
                                    }
                                }
                                else if(userInput.equalsIgnoreCase("back")) {
                                    back = true;
                                    continue;
                                }
                                else {
                                    System.out.println("Please enter a valid stop time.");
                                }
                            } else {
                                System.out.println("Please enter a valid stop time.");
                            }
                            System.out.println("");
                        }
                    }
                } else {
                    System.out.println("\nPlease enter 1, 2, 3, or Quit.");
                }
                System.out.println("");
            } else if (input.hasNext()) {
                String string = input.next();
                string = string.toLowerCase();
                if (string.equals("quit")) {
                    quit = true;
                } else {
                    System.out.println("\nPlease enter 1, 2, 3, or Quit.");
                    System.out.println("");
                }
            }
        }
    }
}
