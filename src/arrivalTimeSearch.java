import java.util.Scanner;
import java.util.ArrayList;

public class arrivalTimeSearch {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean quit = false;

        ternarySearchTree<stopTime> stopTimeTree = new ternarySearchTree<>();
        stopTimeList makeList = new stopTimeList();
        ArrayList<stopTime> list = makeList.getList();

        for(int i = 0; i < list.size(); i++) {
            stopTimeTree.put(list.get(i).getFullDetails(), list.get(i));
        }

        while(!quit) {
            System.out.print("Search for an arrival time (or type \"Quit\"): ");
            String userInput = input.nextLine();
            userInput = userInput.toUpperCase();
            String[] userInputSplit = userInput.split(":");
            if (!userInput.equals("")) {
                if(userInputSplit.length == 3) {
                    if (userInputSplit[0] != null &&
                            userInputSplit[1] != null && userInputSplit[2] != null &&
                            Integer.parseInt(userInputSplit[0]) >= 0 && Integer.parseInt(userInputSplit[0]) <= 23 &&
                            Integer.parseInt(userInputSplit[1]) >= 0 && Integer.parseInt(userInputSplit[1]) <= 59 &&
                            Integer.parseInt(userInputSplit[2]) >= 0 && Integer.parseInt(userInputSplit[2]) <= 59) {
                        if (stopTimeTree.keysWithPrefix(userInput) != null) {
                            for (String s : stopTimeTree.keysWithPrefix(userInput)) {
                                stopTime tmp = stopTimeTree.get(s);
                                System.out.println("Trip ID: " + tmp.getTripID() + ",    Arrival Time: " + tmp.getArrivalTime()
                                        + ",    Full Stop Details: " + tmp.getFullDetails());
                            }
                        } else {
                            System.out.println("No arrivals at this time.");
                        }
                    }
                }else if (userInput.equalsIgnoreCase("quit")) {
                    quit = true;
                    continue;
                } else {
                    System.out.println("Please enter a valid stop time.");
                }
            }
            else {
                System.out.println("Please enter a valid stop time.");
            }
            System.out.println("");
        }
    }
}
