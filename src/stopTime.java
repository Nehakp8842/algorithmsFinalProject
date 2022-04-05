public class stopTime {
    private final String arrivalTime;
    private final String fullDetails;
    private final int tripID;

    stopTime(String details) {
        details = details.replace(" ", "");
        String[] detailsSplit = details.split(",");
        tripID = Integer.parseInt(detailsSplit[0]);
        arrivalTime = detailsSplit[1];
        detailsSplit = details.split(",", 2);
        fullDetails = (detailsSplit[1] + "," + detailsSplit[0]);
    }

    public String getFullDetails() {
        return fullDetails;
    }

    public int getTripID() {
        return tripID;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }
}
