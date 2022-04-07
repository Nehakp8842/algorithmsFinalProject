import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;

public class StopMap {
    private HashMap<Integer, BusStop> mapOfStops = new HashMap<>();

    //construct graph with nodes(busStop class) and edges (Edge class) in the form of a HashMap<Integer, BusStop>
    StopMap() {
        try{
            FileReader readFileStops = new FileReader("stops.txt");
            BufferedReader bufferStops = new BufferedReader(readFileStops);

            FileReader readFileTransfers = new FileReader("transfers.txt");
            BufferedReader bufferTransfers = new BufferedReader(readFileTransfers);

            FileReader readFileStopTimes = new FileReader("stop_times.txt");
            BufferedReader bufferStopTimes = new BufferedReader(readFileStopTimes);

            //create hash map of stops
            String currentLine = bufferStops.readLine();
            String[] currentLineSeparated;
            BusStop stop;
            int stopID;
            String stopName;
            currentLine = bufferStops.readLine();
            while(currentLine != null) {
                currentLineSeparated = currentLine.split(",", 3);
                stopID = Integer.parseInt(currentLineSeparated[0]);
                stopName = currentLineSeparated[2];
                stop = new BusStop(stopID, stopName);
                mapOfStops.put(stopID, stop);
                currentLine = bufferStops.readLine();
            }

            //add edges from transfers.txt
            currentLine = bufferTransfers.readLine();
            Edge newEdge;
            BusStop stopA;
            BusStop stopB;
            int stopID_A;
            int stopID_B;
            int transferType;
            int transferTime;
            while((currentLine = bufferTransfers.readLine()) != null) {
                currentLineSeparated = currentLine.split(",", 4);
                stopID_A = Integer.parseInt(currentLineSeparated[0]);
                stopID_B = Integer.parseInt(currentLineSeparated[1]);
                transferType = Integer.parseInt(currentLineSeparated[2]);
                if(transferType == 0) {
                    transferTime = 2;
                }
                else {
                    transferTime = Integer.parseInt(currentLineSeparated[3])/100;
                }
                stopA = mapOfStops.get(stopID_A);
                stopB = mapOfStops.get(stopID_B);
                newEdge = new Edge(stopA, stopB, transferTime);
                stopA.addEdge(newEdge);
            }

            //add edges from stop_times.txt
            int prevStopID;
            int currentStopID;
            currentLine = bufferStopTimes.readLine();
            currentLine = bufferStopTimes.readLine();
            currentLine = currentLine.replaceAll(" ", "");
            currentLineSeparated = currentLine.split(",");
            prevStopID = Integer.parseInt(currentLineSeparated[3]);

            while ((currentLine = bufferStopTimes.readLine()) != null) {
                currentLine = currentLine.replaceAll(" ", "");
                currentLineSeparated = currentLine.split(",");
                currentStopID = Integer.parseInt(currentLineSeparated[3]);
                if(Integer.parseInt(currentLineSeparated[4]) == 1) {
                    prevStopID = currentStopID;
                }
                else {
                    stopA = mapOfStops.get(prevStopID);
                    stopB = mapOfStops.get(currentStopID);
                    if(stopA != null && stopB != null) {
                        newEdge = new Edge(stopA, stopB, 1);
                        stopA.addEdge(newEdge);
                    }
                    prevStopID = currentStopID;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //return the map of Stops
    public HashMap<Integer, BusStop> getMapOfStops() {
        return mapOfStops;
    }
}
