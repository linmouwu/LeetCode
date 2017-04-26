import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */
public class PathFinder {

    /**
     * Tests the method parseFile with an example test-case.
     * This method is completely for your own testing and is NOT
     * called/used while grading your answer.
     */
    public static void main(String[] args)
            throws FileNotFoundException, IOException {
        String filename = "input_1.txt";
        if (args.length > 0) {
            filename = args[0];
        }

        List<String> answer = parseFile(filename);
        System.out.println(answer);
    }

    static List<String> parseFile(String filename)
            throws FileNotFoundException, IOException {
        /*
         *  Don't modify this function
    	 */
        BufferedReader input = new BufferedReader(new FileReader(filename));
        List<String> allLines = new ArrayList<String>();
        String line;
        while ((line = input.readLine()) != null) {
            allLines.add(line);
        }
        input.close();

        return parseLines(allLines);
    }

    /**
     * Parse each log and calculate the ratio of connected time to total time.
     *
     * @param lines arrays of log string.
     * @return result as a string.
     */
    static List<String> parseLines(List<String> lines) {

        String[] endPoints = lines.get(0).split("\\s+");

        Map<String, Set<String>> neighbours = processNeigh(lines);

        List<String> resultList = new ArrayList<>();

        Set<String> visited = new HashSet<>();

        visited.add(endPoints[0]);

        process(neighbours, resultList, visited, endPoints[0], endPoints[1], new StringBuilder());

        return resultList;
    }

    private static Map<String, Set<String>> processNeigh(List<String> lines) {

        String line = "";

        Map<String, Set<String>> neighbours = new HashMap<>();

        for (int i = 1; i < lines.size(); i++) {

            line = lines.get(i);

            String[] parts = line.split(" : ");

            if (!neighbours.containsKey(parts[0])) {

                neighbours.put(parts[0], new HashSet<>());

            }

            String[] otherSides = parts[1].split("\\s+");

            neighbours.get(parts[0]).addAll(Arrays.asList(otherSides));

        }

        return neighbours;

    }

    private static void process(Map<String, Set<String>> neighbours,
                                List<String> result,
                                Set<String> visited,
                                String current,
                                String endPoint,
                                StringBuilder stringBuilder) {

        stringBuilder.append(current);

        if (current.equals(endPoint)) {

            result.add(stringBuilder.toString());

            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

            return;
        }

        Set<String> nextPoints = neighbours.get(current);

        for (String nextPoint : nextPoints) {

            if (!visited.contains(nextPoint)) {

                visited.add(nextPoint);
                process(neighbours, result, visited, nextPoint, endPoint, stringBuilder);
                visited.remove(nextPoint);

            }

        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

    }

}
