import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class stopTimeList {
    private ArrayList<stopTime> list = new ArrayList<>();

    //class constructor (make an ArrayList of all valid stop times)
    stopTimeList() {
        try {
            FileReader fileReader = new FileReader("stop_times.txt");
            BufferedReader buffer = new BufferedReader(fileReader);
            String currentLine = buffer.readLine();
            stopTime currentStopTime;
            while((currentLine = buffer.readLine()) != null) {
                currentStopTime = new stopTime(currentLine);
                String[] time = currentStopTime.getArrivalTime().split(":");
                if(Integer.parseInt(time[0]) >= 0 && Integer.parseInt(time[0]) <= 23 && Integer.parseInt(time[1]) >= 0
                && Integer.parseInt(time[1]) <= 59 && Integer.parseInt(time[2]) >= 0 && Integer.parseInt(time[2]) <= 59 ) {
                    list.add(currentStopTime);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //return the list of stop times
    public ArrayList<stopTime> getList() {
        return list;
    }
}
