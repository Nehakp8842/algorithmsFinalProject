import java.util.HashMap;

public class stopNameSearch {
    private ternarySearchTree<Integer> stopTree;

    //construct ternary search tree of stops organised by name
    public stopNameSearch() {
        stopTree = new ternarySearchTree<>();
        StopMap makeMap = new StopMap();
        HashMap<Integer, BusStop> mapOfStops = makeMap.getMapOfStops();
        int j = 0;
        for(int i : mapOfStops.keySet()) {
            stopTree.put(mapOfStops.get(i).getStopName(), j);
            j++;
        }
    }

    //return the TST
    public ternarySearchTree<Integer> getStopTree() {
        return stopTree;
    }
}
