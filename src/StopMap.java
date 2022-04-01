import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;

public class StopMap {
    private HashMap<Integer, BusStop> mapOfStops = new HashMap<>();

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
            while(currentLine != null) {
                currentLine = bufferStops.readLine();
                currentLineSeparated = currentLine.split(",", 3);
                stopID = Integer.parseInt(currentLineSeparated[0]);
                stopName = currentLineSeparated[2];
                stop = new BusStop(stopID, stopName);
                mapOfStops.put(stopID, stop);
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
            while(currentLine != null) {
                currentLine = bufferTransfers.readLine();
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
            currentLine = bufferStopTimes.readLine();
            int tripID_A;
            int tripID_B;

            currentLine = bufferStopTimes.readLine();
            currentLineSeparated = currentLine.split(",", 4);
            tripID_A = Integer.parseInt(currentLineSeparated[0]);
            stopID_A = Integer.parseInt(currentLineSeparated[4]);
            while ((currentLine = bufferStopTimes.readLine()) != null) {
                currentLineSeparated = currentLine.split(",", 4);
                tripID_B = Integer.parseInt(currentLineSeparated[0]);
                stopID_B = Integer.parseInt(currentLineSeparated[4]);
                if(tripID_A == tripID_B) {
                    stopA = mapOfStops.get(stopID_A);
                    stopB = mapOfStops.get(stopID_B);
                    newEdge = new Edge(stopA, stopB, 1);
                    stopA.addEdge(newEdge);
                }
                tripID_A = tripID_B;
                stopID_A = stopID_B;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, BusStop> getMapOfStops() {
        return mapOfStops;
    }
}
