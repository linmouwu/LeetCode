import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */
public class LogParser {

    public static void main(String[] args) throws IOException {
        String filename = "log.txt";
        System.out.println(parseFile(filename));
    }

    private static String parseLines(String[] lines) {

        if (lines == null || lines.length == 0) {

            return "0%";

        }

        long startTime = 0;

        long endTime = 0;

        long connectTime = 0;

        long disconTime = 0;

        long totalConn = 0;

        boolean started = false;

        boolean connected = false;

        double percentage = 0.0;

        String result = "";

        for (String line : lines) {

            String[] parts = line.split(" :: ");

            String dateTime = parts[0].substring(1, parts[0].length() - 1);

            if (parts[1].equals("START")) {

                started = true;
                startTime = parseDate(dateTime).getTime();

            } else if (parts[1].equals("CONNECTED")) {

                if (started) {

                    connectTime = parseDate(dateTime).getTime();
                    connected = true;

                }

            } else if (parts[1].equals("DISCONNECTED")) {

                if (connected) {

                    disconTime = parseDate(dateTime).getTime();
                    connected = false;

                    totalConn += (disconTime - connectTime);

                    connectTime = 0;
                    disconTime = 0;

                }

            } else if (parts[1].equals("SHUTDOWN")) {

                if (started) {

                    endTime = parseDate(dateTime).getTime();

                    if (connected) {

                        totalConn += (endTime - connectTime);

                    }

                    break;
                }

            }

        }

        percentage = (double) totalConn / (endTime - startTime) * 100;

        result = (int) percentage + "%";

        return result;

    }


    /**
     * Parse the date string to a Date object.
     *
     * @param dateStr date string
     * @return a corresponding Date object.
     */
    private static Date parseDate(String dateStr) {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("ParseException!");
        }
        return null;
    }

    private static String parseFile(String filename) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(filename));
        List<String> allLines = new ArrayList<String>();
        String line = "";
        while ((line = input.readLine()) != null) {
            allLines.add(line);
        }
        input.close();
        return parseLines(allLines.toArray(new String[allLines.size()]));
    }

}
