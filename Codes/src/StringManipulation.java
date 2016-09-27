import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by mowerlin on 25/09/2016.
 */
public class StringManipulation {
    public String reverseString(String s) {
        // Use StringBuilder to improve performance.
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        // The last word should always be in the list.
        wordList.add(endWord);
        // BFS to solve the problem.
        Queue<String> queue = new LinkedList<String>();
        queue.add(beginWord);
        // Level to mark down the current distance to the end word.
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // For all strings in the queue, get it and compare it to the end.
            for (int i = 0; i < size; i++) {
                String currentString = queue.poll();
                // If it's the end, return the level+1.
                if (currentString.equals(endWord)) {
                    return level + 1;
                }
                // Otherwise, change the chars in the string to get closer to the end.
                for (int j = 0; j < currentString.length(); j++) {
                    char[] characters = currentString.toCharArray();
                    // If the new string is not in the list, do not added it into the string.
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        characters[j] = ch;
                        String newString = new String(characters);
                        if (!characters.equals(currentString) && wordList.contains(newString)) {
                            // If the new string is in the list, update the list by removing the string, and put it into the queue.
                            queue.add(newString);
                            wordList.remove(newString);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }
}
