import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

public class shortestPathBetweenStops {
    private static HashMap<Integer, BusStop> mapOfStops;
    private static HashMap<Integer, Edge> edgesPassed = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean quit = false;
        StopMap makeMap = new StopMap();
        mapOfStops = makeMap.getMapOfStops();

        BusStop stopA = new BusStop(0, "");
        BusStop stopB = new BusStop(0, "");;

        boolean stopAGiven = false;
        while(!quit && !stopAGiven) {
            System.out.print("Enter starting stop (or type \"Quit\"): ");
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
            System.out.print("Enter final stop (or type \"Quit\"): ");
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
            int stopID_B = stopB.getStopID();
            HashMap<Integer, Double> distances = dijkstra(stopID_A, stopID_B);
            ArrayList<Edge> path = AtoBPath(stopID_A, stopID_B);

            if(path != null) {
                System.out.println("Starting Stop: " + stopID_A);
                for (int i = path.size(); i > 0; i++) {
                    if(i > 1) {
                        System.out.println("Next Stop: " + path.get(i).getStopID_B());
                    }
                    else {
                        System.out.println("Final Stop: " + stopID_B);
                    }
                }
                System.out.println(distances.get(stopID_B));
            }
        }
    }

    public static HashMap<Integer, Double> dijkstra(int startVertex, int wantedVertex) {
        boolean skipV1 = false;
        int vertexCount = mapOfStops.size();
        HashMap<Integer, Double> shortestDistances = new HashMap<>();
        HashMap<Integer, Boolean> verticesAdded = new HashMap<>();


        for(Map.Entry<Integer, BusStop> i : mapOfStops.entrySet()) {
            shortestDistances.put(i.getKey(), Double.MAX_VALUE);
            verticesAdded.put(i.getKey(), false);
        }

        shortestDistances.put(startVertex, 0.0);

        for(int i : shortestDistances.keySet()) {
            if(skipV1) {
                int nearest = minDist(shortestDistances, verticesAdded);
                verticesAdded.put(nearest, true);

                Edge selectedEdge = null;
                double minWeight = Double.MAX_VALUE;
                for(int j = 0; j < mapOfStops.get(nearest).outEdges.size(); j++) {
                    if(mapOfStops.get(nearest).outEdges.get(j).getCost() < minWeight) {
                        minWeight = mapOfStops.get(nearest).outEdges.get(j).getCost();
                        selectedEdge = mapOfStops.get(nearest).outEdges.get(j);
                    }
                }
                if(selectedEdge != null) {
                    shortestDistances.put(selectedEdge.getStopID_B(), minWeight + shortestDistances.get(nearest));
                    edgesPassed.put(selectedEdge.getStopID_B(), selectedEdge);
                }
            }
            skipV1 = true;
        }

        return shortestDistances;
    }

    private static int minDist(HashMap<Integer, Double> distances, HashMap<Integer, Boolean> added) {
        double min = Double.MAX_VALUE;
        int min_index = -1;

        for(int i : added.keySet()) {
            if(!added.get(i) && distances.get(i) <= min) {
                min = distances.get(i);
                min_index = i;
            }
        }
        return min_index;
    }

    private static ArrayList<Edge> AtoBPath(int startStop, int EndStop) {
        ArrayList<Edge> path = new ArrayList<>();
        boolean startFound = false;
        boolean noPath = false;

        Edge currentEdge = edgesPassed.get(EndStop);
        if(currentEdge != null) {
            while (!startFound) {
                path.add(currentEdge);
                if (currentEdge.getStopID_A() == startStop) {
                    startFound = true;
                } else if (currentEdge.stopA != null) {
                    currentEdge = edgesPassed.get(currentEdge.getStopID_A());
                } else {
                    noPath = true;
                    break;
                }
            }
        }

        if(!noPath) {
            return path;
        }
        else {
            return null;
        }
    }
}
