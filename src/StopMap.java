import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;

public class StopMap {
    private HashMap<Integer, BusStop> mapOfStops = new HashMap<>();

    StopMap() {
        try{
            FileReader readFile = new FileReader("stops.txt");
            BufferedReader buffer = new BufferedReader(readFile);

            String currentLine = buffer.readLine();
            String[] currentLineSeparated;
            BusStop stop;
            int stopID;
            String stopName;
            while(currentLine != null) {
                currentLine = buffer.readLine();
                currentLineSeparated = currentLine.split(",", 3);
                stopID = Integer.parseInt(currentLineSeparated[0]);
                stopName = currentLineSeparated[2];
                stop = new BusStop(stopID, stopName);
                mapOfStops.put(stopID, stop);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
