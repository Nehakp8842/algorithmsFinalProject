import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class shortestPathBetweenStops {
    public static HashMap<Integer, BusStop> mapOfStops;
    public static HashMap<Integer, Edge> edgesPassed = new HashMap<>();

    //compute shortest distances using Dijkstra algorithm
    public static HashMap<Integer, Double> dijkstra(HashMap<Integer, BusStop> map, int startVertex, int wantedVertex) {
        HashMap<Integer, Double> shortestDistances = new HashMap<>();
        HashMap<Integer, Boolean> verticesAdded = new HashMap<>();

        for(Map.Entry<Integer, BusStop> i : map.entrySet()) {
            shortestDistances.put(i.getKey(), Double.MAX_VALUE);
            verticesAdded.put(i.getKey(), false);
        }

        shortestDistances.put(startVertex, 0.0);

        for(int i : shortestDistances.keySet()) {
            int nearest = minDist(shortestDistances, verticesAdded);
            //System.out.println(nearest);
            verticesAdded.put(nearest, true);

                Edge selectedEdge = null;
                double minWeight = Double.MAX_VALUE;
                for (int j = 0; j < map.get(nearest).outEdges.size(); j++) {
                    if (map.get(nearest).outEdges.get(j).getCost() < minWeight) {
                        minWeight = map.get(nearest).outEdges.get(j).getCost();
                        selectedEdge = map.get(nearest).outEdges.get(j);
                    }
                }
                if (selectedEdge != null) {
                    shortestDistances.put(selectedEdge.getStopID_B(), minWeight + shortestDistances.get(nearest));
                    edgesPassed.put(selectedEdge.getStopID_B(), selectedEdge);
                }
        }

        return shortestDistances;
    }

    //find the minimum distance that has not been already visited
    private static int minDist(HashMap<Integer, Double> distances, HashMap<Integer, Boolean> added) {
        double min = Double.MAX_VALUE;
        int min_index = 0;

        for(int i : added.keySet()) {
            if(!added.get(i) && distances.get(i) <= min) {
                min = distances.get(i);
                min_index = i;
            }
        }
        return min_index;
    }

    //return the list of edges passed to take the shortest route from stop A to stop B
    public static ArrayList<Edge> AtoBPath(int startStop, int EndStop) {
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

    //return the mapOfStops hash map
    public HashMap<Integer, BusStop> getMapOfStops() {
        return mapOfStops;
    }
}
