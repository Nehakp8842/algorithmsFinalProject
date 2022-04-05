import java.util.ArrayList;

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

    public int getStopID() {
        return stopID;
    }

    public String getStopName() {
        return stopName;
    }

    public void addEdge(Edge newEdge) {
        outEdges.add(newEdge);
    }
}
