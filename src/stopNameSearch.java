import java.util.Scanner;
import java.util.HashMap;

public class stopNameSearch {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean quit = false;

        ternarySearchTree<Integer> stopTree = new ternarySearchTree<>();
        StopMap makeMap = new StopMap();
        HashMap<Integer, BusStop> mapOfStops = makeMap.getMapOfStops();
        int j = 0;
        for(int i : mapOfStops.keySet()) {
            stopTree.put(mapOfStops.get(i).getStopName(), j);
            j++;
        }

        while(!quit) {
            System.out.print("Search for a stop name (or type \"Quit\"): ");
            String userInput = input.nextLine();
            userInput = userInput.toUpperCase();
            if (!userInput.equals("")) {
                if (userInput.equalsIgnoreCase("quit")) {
                    quit = true;
                    continue;
                } else if (stopTree.keysWithPrefix(userInput) != null) {
                    for (String s : stopTree.keysThatMatch(userInput)) {
                        System.out.println(s);
                    }
                    for (String s : stopTree.keysWithPrefix(userInput)) {
                        System.out.println(s);
                    }
                } else {
                    System.out.println("Please enter a valid stop name.");
                }
            }
            else {
                System.out.println("Please enter a valid stop name.");
            }
            System.out.println("");
        }
    }
}
