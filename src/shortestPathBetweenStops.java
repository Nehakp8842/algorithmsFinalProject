import java.util.HashMap;
import java.util.Scanner;

public class shortestPathBetweenStops {
    private static HashMap<Integer, BusStop> mapOfStops;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean quit = false;
        StopMap makeMap = new StopMap();
        mapOfStops = makeMap.getMapOfStops();

        BusStop stopA = new BusStop(0, "");
        BusStop stopB = new BusStop(0, "");;

        boolean stopAGiven = false;
        while(!quit && !stopAGiven) {
            System.out.print("Enter starting stop (or type \"Quit\": ");
            if(input.hasNextInt()) {
                int stopID_A = input.nextInt();
                if(mapOfStops.get(stopID_A) != null) {
                    stopA = mapOfStops.get(stopID_A);
                    stopAGiven = true;
                }
                else {
                    System.out.println("Please enter a valid Stop ID");
                }
            }
            else if(input.hasNext()){
                String string = input.next();
                string = string.toLowerCase();
                if (string.equals("quit")) {
                    quit = true;
                }
            }
            else {
                System.out.println("Please enter a valid Stop ID");
            }
        }

        boolean stopBGiven = false;
        while(!quit && !stopBGiven) {
            System.out.print("Enter final stop (or type \"Quit\": ");
            if(input.hasNextInt()) {
                int stopID_B = input.nextInt();
                if(mapOfStops.get(stopID_B) != null) {
                    stopB = mapOfStops.get(stopID_B);
                    stopBGiven = true;
                }
                else {
                    System.out.println("Please enter a valid Stop ID");
                }
            }
            else if(input.hasNext()){
                String string = input.next();
                string = string.toLowerCase();
                if (string.equals("quit")) {
                    quit = true;
                }
            }
            else {
                System.out.println("Please enter a valid Stop ID");
            }
        }

        if(!quit) {
            int stopID_A = stopA.getStopID();
            double[] distances = dijkstra(stopID_A);
        }
    }

    public static double[] dijkstra(int startVertex) {
        int vertexCount = mapOfStops.size();
        double[] shortestDistances = new double[vertexCount];
        boolean[] verticesAdded = new boolean[vertexCount];

        for(int i = 0; i < vertexCount; i++) {
            shortestDistances[i] = Double.MAX_VALUE;
            verticesAdded[i] = false;
        }

        shortestDistances[startVertex] = 0;



        return shortestDistances;
    }

    private static int minDist(double[] distances, boolean[] added, int vertexCount) {
        double min = Double.MAX_VALUE;
        int min_index = -1;

        for(int i = 0; i < vertexCount; i++) {
            if(!added[i] && distances[i] <= min) {
                min = distances[i];
                min_index = i;
            }
        }
        return min_index;
    }
}
