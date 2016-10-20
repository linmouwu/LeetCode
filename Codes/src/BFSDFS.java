/**
 * Created by mowerlin on 25/09/2016.
 */
import java.util.*;
public class BFSDFS {

    private int result = 0;

    public int numberOfPatterns(int m, int n) {
        int skip[][] = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
        int count = 1;
        boolean[] visited = new boolean[10];
        for (int i = 1; i <= 9; i++) {
            visited[i] = true;
            DFS(visited, skip, count, m, n, i);
            visited[i] = false;
        }
        return result;
    }

    private void DFS(boolean[] visited, int[][] skip, int count, int m, int n, int ci) {
        if (count >= m && count <= n) {
            result++;
        } else if (count > n) {
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (!visited[i] && (skip[ci][i] == 0 || visited[skip[ci][i]])) {
                visited[i] = true;
                DFS(visited, skip, count + 1, m, n, i);
                visited[i] = false;
            }
        }
    }

    // User a helper to pass by the list and the level as arguments.
    public int depthSum(List<NestedInteger> nestedList) {
        return helper(nestedList, 1);
    }

    // Naive topological sorting.
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0)
            return false;
        int edges = prerequisites.length;
        if (edges <= 0)
            return true;
        // Incoming edges of one vertex.
        int[] incomings = new int[numCourses];
        int source = -1;
        // Count the incoming of the vertices.
        for (int[] edge : prerequisites) {
            incomings[edge[0]]++;
        }
        int course = 0;
        // Run until all vertices are visited.
        while (course < numCourses) {
            // Find the next vertex with no incomings.
            for (int i = 0; i < numCourses; i++) {
                if (incomings[i] == 0) {
                    source = i;
                    incomings[source] = -1;
                    break;
                }
            }
            // If not found, there is a cycle.
            if (source == -1)
                return false;
            // In found, remove the vertex.
            for (int[] edge : prerequisites) {
                if (edge[1] == source) {
                    incomings[edge[0]]--;
                }
            }
            source = -1;
            course++;
        }
        return course == numCourses;
    }

    private int helper(List<NestedInteger> nestedList, int level) {
        int size = nestedList.size();
        // If the list is empty, return 0;
        if (size <= 0)
            return 0;
        int result = 0;
        // If the current element is a integer, get the results.
        // Otherwise recursively call the helper.
        for(NestedInteger i: nestedList){
            if(i.isInteger()){
                result += i.getInteger() * level;
            }else{
                result += helper(i.getList(), level+1) * level;
            }
        }
        return result;
    }

}
