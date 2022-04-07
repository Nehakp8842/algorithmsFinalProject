import java.util.ArrayList;

//class constructor
public class BusStop {
    final private int stopID;
    private String stopName;
    public ArrayList<Edge> outEdges = new ArrayList<>();

    BusStop(int ID, String name) {
        stopID = ID;
        stopName = name;

        if(stopName.contains("FLAGSTOP ") || stopName.contains("WB ") || stopName.contains("NB ") || stopName.contains("SB ")
                || stopName.contains("EB ")) {
            String[] splitStopName = stopName.split("\\s+", 2);
            stopName = (splitStopName[1] + " " + splitStopName[0]);
        }
        else {
            stopName = name;
        }
    }

    //return stopID
    public int getStopID() {
        return stopID;
    }

    //return stopName
    public String getStopName() {
        return stopName;
    }

    //add a new edge to the ArrayList outEdges
    public void addEdge(Edge newEdge) {
        outEdges.add(newEdge);
    }
}
