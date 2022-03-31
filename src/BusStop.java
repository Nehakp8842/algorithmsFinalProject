public class BusStop {
    final private int stopID;
    private String stopName;

    BusStop(int ID, String name) {
        stopID = ID;
        stopName = name;

        if(stopName.contains("FLAGSTOP") || stopName.contains("WB") || stopName.contains("NB") || stopName.contains("SB")
                || stopName.contains("EB")) {
            String[] splitStopName = stopName.split("\\s+", 2);
            stopName = (splitStopName[1] + " " + splitStopName[0]);
        }
    }

    public int getStopID() {
        return stopID;
    }

    public String getStopName() {
        return stopName;
    }
}
