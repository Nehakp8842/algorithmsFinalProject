public class stopTime {
    private final String arrivalTime;
    private final String fullDetails;
    private final int tripID;

    //class constructor
    stopTime(String details) {
        details = details.replace(" ", "");
        String[] detailsSplit = details.split(",");
        tripID = Integer.parseInt(detailsSplit[0]);
        arrivalTime = detailsSplit[1];
        detailsSplit = details.split(",", 2);
        fullDetails = (detailsSplit[1] + "," + detailsSplit[0]);
    }

    //return the fullDetails string
    public String getFullDetails() {
        return fullDetails;
    }

    //return the tripID
    public int getTripID() {
        return tripID;
    }

    //return the arrival time string
    public String getArrivalTime() {
        return arrivalTime;
    }
}
