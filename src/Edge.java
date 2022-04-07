public class Edge {
    public BusStop stopA;
    public BusStop stopB;
    private final int stopID_A;
    private final int stopID_B;
    private final double cost;

    //class constructor
    Edge(BusStop stopA, BusStop stopB, double cost) {
        this.stopA = stopA;
        this.stopB = stopB;
        stopID_A = this.stopA.getStopID();
        stopID_B = this.stopB.getStopID();
        this.cost = cost;
    }

    //return ID of stopA (the stop the edge goes from)
    public int getStopID_A() {
        return stopID_A;
    }

    //return ID of stopB (the stop the edge goes to)
    public int getStopID_B() {
        return stopID_B;
    }

    //return the cost of the edge
    public double getCost() {
        return cost;
    }
}