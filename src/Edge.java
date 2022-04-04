public class Edge {
    public BusStop stopA;
    public BusStop stopB;
    private final int stopID_A;
    private final int stopID_B;
    private final double cost;

    Edge(BusStop stopA, BusStop stopB, double cost) {
        this.stopA = stopA;
        this.stopB = stopB;
        stopID_A = this.stopA.getStopID();
        stopID_B = this.stopB.getStopID();
        this.cost = cost;
    }

    public int getStopID_A() {
        return stopID_A;
    }

    public int getStopID_B() {
        return stopID_B;
    }

    public double getCost() {
        return cost;
    }
}